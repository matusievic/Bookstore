package dao.account;

import entities.account.Account;
import entities.account.AccountType;

import java.util.List;

public interface AccountDAO {
    boolean isAccountExists(String email);
    boolean isAccountExists(String email, String password);

    Account getAccount(String email, String password);
    Account getAccount(String email);
    List<Account> getAccounts();
    Account updateAccount(Account account);
    Account addAccount(String email, String password, String name, String surname, AccountType type);
    void deleteAccount(Account account);
}
