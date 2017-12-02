package controllers;

import entities.account.AccountType;
import entities.author.Author;
import entities.book.Book;
import entities.category.Category;
import entities.pagination.Pagination;
import play.data.FormFactory;
import play.libs.F.Tuple;
import play.mvc.Controller;
import play.mvc.Result;
import services.ServiceFactory;
import services.author.AuthorService;
import services.book.BookService;
import services.category.CategoryService;
import services.paginator.Paginator;

import javax.inject.Inject;
import java.util.*;

public class CartController extends Controller {
    @Inject
    private FormFactory formFactory;
    private static final int BOOK_PER_PAGE = 10;
    private final BookService bookService = ServiceFactory.getInstance().getBookService();
    private final CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

    /**
     * Display cart to user
     */
    public Result display(int currentPage) {
        String cartLength = session("cartLength");
        if (cartLength == null) { return ok(views.html.cart.display.render(null, null, null, -1, -1)); }
        int cartLengthNumber = Integer.parseInt(cartLength);

        Map<Integer, Book> books = new LinkedHashMap<>();
        for (int i = 0; i < cartLengthNumber; i++) {
            String currentCartItem = session("cartItem" + i);
            if (currentCartItem == null || currentCartItem.isEmpty()) { continue; }
            int currentBookId = Integer.parseInt(currentCartItem);
            books.put(i, bookService.get(currentBookId));
        }

        List<Author> authors = authorService.getAuthors();
        List<Category> categories = categoryService.getCategories();

        //prepare List for pagination
        List<Tuple<Integer, Book>> paginationData = new ArrayList<>();
        for (Map.Entry<Integer, Book> e : books.entrySet()) {
            paginationData.add(new Tuple<>(e.getKey(), e.getValue()));
        }

        //prepare pagination
        Pagination<Tuple<Integer, Book>> pagination = new Pagination<>();
        pagination.setData(paginationData);
        pagination.setCurrentPage(currentPage);
        pagination.setItemPerPage(BOOK_PER_PAGE);

        paginationData = Paginator.paginate(pagination);
        Map<Object, Book> dataForDisplaying = new HashMap<>();
        for (Tuple<Integer, Book> t : paginationData) {
            dataForDisplaying.put(t._1,t._2);
        }

        return ok(views.html.cart.display.render(dataForDisplaying, categories, authors, pagination.getCurrentPage(), pagination.getPageCount()));
    }

    /**
     * Add a book to the cart
     */
    public Result add(int bookId) {
        if (!isAccountHasAccess()) { return ok(views.html.index.render()); }

        String cartLength = session("cartLength");
        if (cartLength == null || cartLength.isEmpty()) {
            session("cartLength", "0");
            cartLength = "0";
        }

        if (bookService.get(bookId) == null) { return ok(views.html.index.render()); }

        session("cartItem" + cartLength, String.valueOf(bookId));

        int cartLengthNumber = Integer.parseInt(cartLength);
        session("cartLength", String.valueOf(++cartLengthNumber));

        return redirect(request().getHeaders().get("referer").orElse("/"));
    }

    /**
     * Delete a book from the cart
     */
    public Result delete(int itemId) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        String cartLength = session("cartLength");
        if (cartLength == null || cartLength.isEmpty()) {
            return redirect(previousPage);
        }
        if (session("cartItem" + itemId) != null) {
            session().remove("cartItem" + itemId);
        }

        return redirect(previousPage);
    }

    /**
     * Clear the cart
     */
    public Result clear() {
        String cartLength = session("cartLength");
        if (cartLength == null) { return ok(views.html.index.render()); }
        int cartLengthNumber = Integer.parseInt(cartLength);

        for (int i = 0; i < cartLengthNumber; i++) {
            session().remove("cartItem" + i);
        }

        return display(1);
    }

    /**
     * Forms an order from the cart
     */
    public Result order() {
        return TODO;
    }

    /**
     * Submit an order to Administrator
     * @return
     */
    public Result submit() {
        return TODO;
    }


    //utility methods
    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null && accountType.equals(AccountType.CUSTOMER.toString());
    }
}
