package dao.account.impl;

import dao.account.AccountDAO;
import entities.account.Account;
import io.ebean.Ebean;

import java.util.List;

public class MySQLAccountDAO implements AccountDAO {
    @Override
    public boolean isAccountExists(String email) {
        Account account = Ebean.find(Account.class).select("account").where().eq("email", email).findUnique();
        return account != null;
    }

    @Override
    public boolean isAccountExists(String email, String password) {
        List<Account> accounts = Account.find.all();
        for (Account account : accounts) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) { return true; }
        }

        return false;
    }

    @Override
    public Account getAccount(String email, String password) {
        List<Account> accounts = Account.find.all();
        for (Account account : accounts) {
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;
    }

    @Override
    public Account addAccount(String email, String password, String name, String surname) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setName(name);
        account.setSurname(surname);
        account.save();
        return account;
    }
}
