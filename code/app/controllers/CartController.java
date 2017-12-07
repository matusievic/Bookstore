package controllers;

import entities.account.Account;
import entities.account.AccountType;
import entities.author.Author;
import entities.book.Book;
import entities.category.Category;
import entities.order.BookOrder;
import entities.pagination.Pagination;
import play.data.Form;
import play.data.FormFactory;
import play.libs.F.Tuple;
import play.mvc.Controller;
import play.mvc.Result;
import services.ServiceFactory;
import services.account.AccountService;
import services.author.AuthorService;
import services.book.BookService;
import services.category.CategoryService;
import services.order.OrderService;
import services.paginator.Paginator;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

public class CartController extends Controller {
    @Inject
    private FormFactory formFactory;
    private static final int BOOK_PER_PAGE = 10;
    private final BookService bookService = ServiceFactory.getInstance().getBookService();
    private final CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
    private final AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
    private final AccountService accountService = ServiceFactory.getInstance().getAccountService();
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Inject
    private Application applicationController;

    /**
     * Display cart to user
     */
    public Result display(int currentPage) {
        String cartLength = session("cartLength");
        if (cartLength == null) { return ok(views.html.cart.display.render(new HashMap<Object, Book>(), null, null, -1, -1)); }
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
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        String cartLength = session("cartLength");
        if (cartLength == null || cartLength.isEmpty()) {
            session("cartLength", "0");
            cartLength = "0";
        }

        if (bookService.get(bookId) == null) { return redirect(previousPage); }

        session("cartItem" + cartLength, String.valueOf(bookId));
        int cartLengthNumber = Integer.parseInt(cartLength);
        session("cartLength", String.valueOf(++cartLengthNumber));

        return redirect(previousPage);
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
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        String cartLength = session("cartLength");
        if (cartLength == null) { return redirect(previousPage); }
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
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<BookOrder> orderForm = formFactory.form(BookOrder.class);
        String email = session("email");
        if (email == null || email.isEmpty()) { return redirect(previousPage); }
        Account account = accountService.getAccountInfo(email);
        return ok(views.html.cart.order.render(orderForm, account));
    }

    /**
     * Submits an order to Administrator
     */
    public Result submit() {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<BookOrder> orderForm = formFactory.form(BookOrder.class).bindFromRequest();
        String userId = session("email");
        String userName = orderForm.get().getName();
        String userSurname = orderForm.get().getSurname();
        String currentDate = new SimpleDateFormat("dd/MM/yyy").format(new Date());
        String address = orderForm.get().getAddress();
        String phone = orderForm.get().getPhone();
        int zip = orderForm.get().getZip();

        StringBuilder booksBuilder = new StringBuilder();
        String cartLength = session("cartLength");
        if (cartLength == null) { return ok(views.html.cart.display.render(new HashMap<Object, Book>(), null, null, -1, -1)); }
        int cartLengthNumber = Integer.parseInt(cartLength);
        for (int i = 0; i < cartLengthNumber; i++) {
            String currentCartItem = session("cartItem" + i);
            if (currentCartItem == null || currentCartItem.isEmpty()) { continue; }
            booksBuilder.append(currentCartItem);
            booksBuilder.append(';');
        }
        String books = booksBuilder.toString();

        orderService.add(userId, userName, userSurname, currentDate, address, phone, zip, books);
        clear();

        return applicationController.account();
    }


    //utility methods
    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null && accountType.equals(AccountType.CUSTOMER.toString());
    }
}
