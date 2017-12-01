package controllers;

import entities.account.AccountType;
import entities.author.Author;
import entities.book.Book;
import entities.category.Category;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.ServiceFactory;
import services.author.AuthorService;
import services.book.BookService;
import services.category.CategoryService;
import views.html.index;

import javax.inject.Inject;
import java.util.List;

public class BookController extends Controller {
    @Inject
    private FormFactory formFactory;

    public Result update(int id) {
        if (!isAccountHasAccess()) { return ok(index.render()); }

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

        List<Book> books = bookService.getBooks();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        return ok(views.html.book.books.render(books, categories, authors));
    }

    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null && accountType.equals(AccountType.ADMIN.toString());
    }

    public Result delete(int id) {
        if (!isAccountHasAccess()) { return ok(index.render()); }

        BookService bookService = ServiceFactory.getInstance().getBookService();
        Book book = bookService.get(id);
        bookService.deleteBook(book);

        List<Book> books = bookService.getBooks();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        return ok(views.html.book.books.render(books, categories, authors));
    }

    public Result edit(int id) {
        if (!isAccountHasAccess()) { return ok(index.render()); }

        Form<Book> bookForm = formFactory.form(Book.class);
        BookService bookService = ServiceFactory.getInstance().getBookService();
        Book book = bookService.get(id);

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        return ok(views.html.book.edit.render(book, bookForm, authors, categories));

    }

    public Result books() {
        if (!isAccountHasAccess()) { return ok(index.render()); }

        BookService bookService = ServiceFactory.getInstance().getBookService();
        List<Book> books = bookService.getBooks();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        return ok(views.html.book.books.render(books, categories, authors));

    }

    public Result create() {
        if (!isAccountHasAccess()) { return ok(index.render()); }

        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        return ok(views.html.book.create.render(bookForm, authors, categories));
    }

    public Result save() {
        if (!isAccountHasAccess()) { return ok(index.render()); }

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

        List<Book> books = bookService.getBooks();

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        return ok(views.html.book.books.render(books, categories, authors));
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
