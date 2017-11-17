package dao;

import dao.account.AccountDAO;
import dao.account.impl.MySQLAccountDAO;

public final class AccountDAOFactory {
    private static final AccountDAOFactory instance = new AccountDAOFactory();
    private AccountDAO accountDAO = new MySQLAccountDAO();

    private AccountDAOFactory() {}

    public static AccountDAOFactory getInstance() {
        return instance;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }
}
