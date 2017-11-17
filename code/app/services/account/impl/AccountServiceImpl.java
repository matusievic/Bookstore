package services.account.impl;

import dao.AccountDAOFactory;
import dao.account.AccountDAO;
import entities.account.Account;
import play.Logger;
import services.account.AccountService;
import services.encryption.EncryptionServiceFactory;
import services.encryption.Encryptor;
import services.exception.EncryptorException;
import services.exception.ServiceException;

public class AccountServiceImpl implements AccountService {
    @Override
    public boolean isAccountExists(String email) {
        AccountDAO accountDAO = AccountDAOFactory.getInstance().getAccountDAO();
        return accountDAO.isAccountExists(email);
    }

    @Override
    public boolean isAccountExists(String email, String password) throws ServiceException {
        String encryptedPassword = encryptPassword(password);

        AccountDAO accountDAO = AccountDAOFactory.getInstance().getAccountDAO();
        return accountDAO.isAccountExists(email, encryptedPassword);
    }

    @Override
    public Account authenticate(String email, String password) {
        return null;
    }


    private String encryptPassword(String password) throws ServiceException {
        String encryptedPassword = null;
        Encryptor encryptor = EncryptionServiceFactory.getInstance().getEncryptor();
        try {
            encryptedPassword = encryptor.encrypt(password);
        } catch (EncryptorException e) {
            Logger.error("Cannot get an encrypted password", e);
            throw new ServiceException(e);
        }
        return encryptedPassword;
    }
}
