package controllers;

import entities.account.Account;
import entities.account.AccountType;
import entities.author.Author;
import entities.book.Book;
import entities.category.Category;
import entities.order.BookOrder;
import entities.pagination.Pagination;
import play.mvc.*;
import play.data.*;

import services.account.AccountService;
import services.ServiceFactory;
import services.author.AuthorService;
import services.book.BookService;
import services.category.CategoryService;
import services.order.OrderService;
import services.paginator.Paginator;
import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Application extends Controller {
    @Inject
    private FormFactory formFactory;
    private static final int BOOK_PER_PAGE = 10;
    private static final AccountService accountService = ServiceFactory.getInstance().getAccountService();
    private static final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private static final BookService bookService = ServiceFactory.getInstance().getBookService();
    private static final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

    public Result index() {
        List<Book> books = bookService.getSliderBooks();
        List<Author> authors = authorService.getAuthors();
        return ok(index.render(books, authors));
    }

    public Result catalog(int authorId, int categoryId, int currentPage) {
        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        BookService bookService = ServiceFactory.getInstance().getBookService();
        Predicate<Book> f;
        if (authorId == -1 && categoryId == -1) {
            f = b -> true;
        } else if (authorId != -1) {
            f = b -> b.getAuthorId() == authorId;
        } else {
            f = b -> b.getCategoryId() == categoryId;
        }
        List<Book> books = bookService.getBooks().stream().filter(f).collect(Collectors.toList());

        Pagination<Book> pagination = new Pagination<>();
        pagination.setData(books);
        pagination.setCurrentPage(currentPage);
        pagination.setItemPerPage(BOOK_PER_PAGE);

        books = Paginator.paginate(pagination);

        return ok(views.html.book.books.render(books, categories, authors, currentPage, pagination.getPageCount(), authorId, categoryId));
    }

    public Result help() {
        return ok(views.html.help.render());
    }

    public Result orders() {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        String accountType = session("accountType");
        if (accountType == null || !accountType.equals(AccountType.ADMIN.toString())) {
            return redirect(previousPage);
        }

        List<Account> accounts = accountService.getAccounts();
        List<BookOrder> orders = orderService.get();
        return ok(views.html.admin.orders.render(orders, accounts));
    }

    public Result accounts() {
        List<Account> accounts = accountService.getAccounts();
        return ok(views.html.admin.accounts.render(accounts));
    }

    public Result about() {
        return TODO;
    }

    public Result account() {
        if (!session().containsKey("email")) {
            return index();
        }

        AccountService accountService = ServiceFactory.getInstance().getAccountService();
        String email = session("email");
        String password = session("password");
        Account currentAccount = accountService.getAccountInfo(email, password);
        List<BookOrder> orders = AccountController.getOrders();

        Form<AccountController.NameForm> nameForm = formFactory.form(AccountController.NameForm.class);
        Form<AccountController.PasswordForm> passwordForm = formFactory.form(AccountController.PasswordForm.class);

        return ok(account.render(currentAccount, nameForm, passwordForm, orders));
    }


    public Result logIn() {
        Form<AccountController.LoginForm> form = formFactory.form(AccountController.LoginForm.class);
        return ok(login.render(form));
    }

    public Result signUp() {
        Form<AccountController.SignupForm> form = formFactory.form(AccountController.SignupForm.class);
        return ok(signup.render(form));
    }
}
