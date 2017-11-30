package dao;

import dao.account.AccountDAO;
import dao.account.impl.MySQLAccountDAO;
import dao.author.AuthorDAO;
import dao.author.impl.MySQLAuthorDAO;
import dao.category.CategoryDAO;
import dao.category.impl.MySQLCategoryDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private AccountDAO accountDAO = new MySQLAccountDAO();
    private CategoryDAO categoryDAO = new MySQLCategoryDAO();
    private AuthorDAO authorDAO = new MySQLAuthorDAO();

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
}
