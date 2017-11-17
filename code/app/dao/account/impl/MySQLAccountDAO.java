package dao.account.impl;

import dao.account.AccountDAO;
import entities.account.Account;

import java.util.List;

public class MySQLAccountDAO implements AccountDAO {
    @Override
    public boolean isAccountExists(String email, String password) {
        List<Account> accounts = Account.find.all();
        for (Account account : accounts) {
            if (account.getEmail().equals(email)) { return true; }
        }

        return false;
    }

    @Override
    public Account getAccount(String email, String password) {
        return null;
    }
}
