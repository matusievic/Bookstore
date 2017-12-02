package dao;

import dao.account.AccountDAO;
import dao.account.impl.MySQLAccountDAO;
import dao.author.AuthorDAO;
import dao.author.impl.MySQLAuthorDAO;
import dao.book.BookDAO;
import dao.book.impl.MySQLBookDAO;
import dao.category.CategoryDAO;
import dao.category.impl.MySQLCategoryDAO;
import dao.order.OrderDAO;
import dao.order.impl.MySQLOrderDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final AccountDAO accountDAO = new MySQLAccountDAO();
    private final CategoryDAO categoryDAO = new MySQLCategoryDAO();
    private final AuthorDAO authorDAO = new MySQLAuthorDAO();
    private final BookDAO bookDAO = new MySQLBookDAO();
    private final OrderDAO orderDAO = new MySQLOrderDAO();

    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return instance;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }
    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }
    public BookDAO getBookDAO() {
        return bookDAO;
    }
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}
