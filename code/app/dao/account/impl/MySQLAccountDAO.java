package dao.account.impl;

import dao.account.AccountDAO;
import entities.account.Account;
import entities.account.AccountType;

import java.util.List;
import java.util.stream.Collectors;

public class MySQLAccountDAO implements AccountDAO {
    @Override
    public boolean isAccountExists(String email) {
        List<Account> accounts= Account.find.all();
        for (Account account : accounts) {
            if (account.getEmail().equals(email)) { return true; }
        }
        return false;
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
    public Account getAccount(String email) {
        return Account.find.byId(email);
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = Account.find.all().stream().filter(a -> a.getType() != AccountType.ADMIN).collect(Collectors.toList());
        return accounts;
    }

    @Override
    public Account updateAccount(Account account) {
        account.update();
        return account;
    }

    @Override
    public Account addAccount(String email, String password, String name, String surname, AccountType type) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setName(name);
        account.setSurname(surname);
        account.setType(type);
        account.save();
        return account;
    }

    @Override
    public void deleteAccount(Account account) {
        account.delete();
    }
}
