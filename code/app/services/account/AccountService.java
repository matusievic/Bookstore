package services.account;

import entities.account.Account;
import services.exception.ServiceException;

public interface AccountService {
    boolean isAccountExists(String email);
    boolean isAccountExists(String email, String password) throws ServiceException;

    Account authenticate(String email, String password) throws ServiceException;
    Account register(String email, String password, String name, String surname) throws ServiceException;
}
