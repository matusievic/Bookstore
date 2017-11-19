package dao.account;

import entities.account.Account;

public interface AccountDAO {
    boolean isAccountExists(String email);
    boolean isAccountExists(String email, String password);

    Account getAccount(String email, String password);
    Account addAccount(String email, String password, String name, String surname);
}
