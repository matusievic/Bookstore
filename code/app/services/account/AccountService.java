package services.account;

import entities.account.Account;
import services.exception.ServiceException;

public interface AccountService {
    boolean isAccountExists(String email, String password) throws ServiceException;
    Account authenticate(String email, String password);
}
