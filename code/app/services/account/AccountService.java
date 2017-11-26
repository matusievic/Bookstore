package services.account;

import entities.account.Account;
import entities.account.AccountType;
import services.exception.ServiceException;

public interface AccountService {
    boolean isAccountExists(String email);
    boolean isAccountExists(String email, String password) throws ServiceException;

    Account authenticate(String email, String password) throws ServiceException;
    Account register(String email, String password, String name, String surname, AccountType type) throws ServiceException;

    Account getAccountInfo(String email, String password);

    Account changeName(String email, String password, String newName, String newSurname);
    Account changePassword(String email, String oldPassword, String newPassword) throws ServiceException;

    void deactivate(String email, String password);
}
