package controllers;

import entities.account.Account;
import entities.author.Author;
import entities.book.Book;
import entities.category.Category;
import play.mvc.*;
import play.data.*;

import services.account.AccountService;
import services.ServiceFactory;
import services.author.AuthorService;
import services.book.BookService;
import services.category.CategoryService;
import views.html.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {
    @Inject
    private FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }

    public Result catalog() {
        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        BookService bookService = ServiceFactory.getInstance().getBookService();
        List<Book> books = bookService.getBooks();

        return ok(views.html.book.books.render(books, categories, authors));
    }

    public Result help() {
        return TODO;
    }

    public Result orders() {
        return TODO;
    }

    public Result users() {
        return TODO;
    }

    public Result about() {
        return TODO;
    }

    public Result account() {
        if (!session().containsKey("email")) {
            return ok(index.render());
        }

        AccountService accountService = ServiceFactory.getInstance().getAccountService();
        String email = session("email");
        String password = session("password");
        Account currentAccount = accountService.getAccountInfo(email, password);
        
        Form<AccountController.NameForm> nameForm = formFactory.form(AccountController.NameForm.class);
        Form<AccountController.PasswordForm> passwordForm = formFactory.form(AccountController.PasswordForm.class);

        return ok(account.render(currentAccount, nameForm, passwordForm));
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
