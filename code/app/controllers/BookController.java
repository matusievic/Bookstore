package controllers;

import entities.account.AccountType;
import entities.author.Author;
import entities.book.Book;
import entities.category.Category;
import entities.pagination.Pagination;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.ServiceFactory;
import services.author.AuthorService;
import services.book.BookService;
import services.category.CategoryService;
import services.paginator.Paginator;

import javax.inject.Inject;
import java.util.List;

public class BookController extends Controller {
    @Inject
    private FormFactory formFactory;
    private static final int BOOK_PER_PAGE = 10;
    @Inject
    private Application applicationController;

    public Result update(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        BookService bookService = ServiceFactory.getInstance().getBookService();
        Book book = bookService.get(id);

        if (book == null) {
            return ok(views.html.error.error500.render());
        }

        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();

        book.setName(bookForm.get().getName());
        book.setImageURL(bookForm.get().getImageURL());
        book.setAuthorId(bookForm.get().getAuthorId());
        book.setCategoryId(bookForm.get().getCategoryId());
        book.setPrice(bookForm.get().getPrice());
        book.setPageCount(bookForm.get().getPageCount());
        book.setDescription(bookForm.get().getDescription());
        bookService.updateBook(book);

        return applicationController.catalog(-1, -1, 1);
    }

    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null && accountType.equals(AccountType.ADMIN.toString());
    }

    public Result delete(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        BookService bookService = ServiceFactory.getInstance().getBookService();
        Book book = bookService.get(id);
        bookService.deleteBook(book);

        return applicationController.catalog(-1, -1, 1);
    }

    public Result edit(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<Book> bookForm = formFactory.form(Book.class);
        BookService bookService = ServiceFactory.getInstance().getBookService();
        Book book = bookService.get(id);

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        return ok(views.html.book.edit.render(book, bookForm, authors, categories));

    }

    public Result books(int currentPage) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        BookService bookService = ServiceFactory.getInstance().getBookService();
        List<Book> books = bookService.getBooks();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        int totalPage = books.size() / BOOK_PER_PAGE;
        if (currentPage > totalPage) {
            currentPage = 1;
        }
        books = books.subList((currentPage - 1) * BOOK_PER_PAGE, currentPage * BOOK_PER_PAGE);

        return applicationController.catalog(-1, -1, currentPage);

    }

    public Result create() {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        return ok(views.html.book.create.render(bookForm, authors, categories));
    }

    public Result save() {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        BookService bookService = ServiceFactory.getInstance().getBookService();

        String name = bookForm.get().getName();
        String imageURL = bookForm.get().getImageURL();
        int authorId = bookForm.get().getAuthorId();
        int categoryId = bookForm.get().getCategoryId();
        double price = bookForm.get().getPrice();
        int pageCount = bookForm.get().getPageCount();
        String description = bookForm.get().getDescription();

        bookService.add(name, imageURL, authorId, categoryId, price, pageCount, description);

        return applicationController.catalog(-1, -1, 1);
    }

    public Result get(int id) {
        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        BookService bookService = ServiceFactory.getInstance().getBookService();
        Book book = bookService.get(id);

        return ok(views.html.book.book.render(book, authors, categories));
    }
}
