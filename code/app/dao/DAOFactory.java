package dao;

import dao.account.AccountDAO;
import dao.account.impl.MySQLAccountDAO;
import dao.author.AuthorDAO;
import dao.author.impl.MySQLAuthorDAO;
import dao.book.BookDAO;
import dao.book.impl.MySQLBookDAO;
import dao.category.CategoryDAO;
import dao.category.impl.MySQLCategoryDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final AccountDAO accountDAO = new MySQLAccountDAO();
    private final CategoryDAO categoryDAO = new MySQLCategoryDAO();
    private final AuthorDAO authorDAO = new MySQLAuthorDAO();
    private final BookDAO bookDAO = new MySQLBookDAO();

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
}
