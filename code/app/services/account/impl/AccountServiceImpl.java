package services.account.impl;

import dao.DAOFactory;
import dao.account.AccountDAO;
import entities.account.Account;
import entities.account.AccountType;
import play.Logger;
import services.account.AccountService;
import services.encryption.EncryptionServiceFactory;
import services.encryption.Encryptor;
import services.exception.EncryptorException;
import services.exception.ServiceException;

public class AccountServiceImpl implements AccountService {
    @Override
    public boolean isAccountExists(String email) {
        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
        return accountDAO.isAccountExists(email);
    }

    @Override
    public boolean isAccountExists(String email, String password) throws ServiceException {
        String encryptedPassword = encryptPassword(password);

        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
        return accountDAO.isAccountExists(email, encryptedPassword);
    }

    @Override
    public Account authenticate(String email, String password) throws ServiceException {
        String encryptedPassword = encryptPassword(password);
        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
        return accountDAO.getAccount(email, encryptedPassword);
    }

    @Override
    public Account register(String email, String password, String name, String surname, AccountType type) throws ServiceException {
        String encryptedPassword = encryptPassword(password);
        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();

        return accountDAO.addAccount(email, encryptedPassword, name, surname, type);
    }


    @Override
    public Account getAccountInfo(String email, String password) {
        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
        return accountDAO.getAccount(email, password);
    }

    @Override
    public Account changeName(String email, String password, String newName, String newSurname) {
        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
        Account account = accountDAO.getAccount(email, password);
        account.setName(newName);
        account.setSurname(newSurname);
        return accountDAO.updateAccount(account);
    }

    @Override
    public Account changePassword(String email, String oldPassword, String newPassword) throws ServiceException {
        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
        Account account = accountDAO.getAccount(email, oldPassword);
        String newEncryptedPassword = encryptPassword(newPassword);
        account.setPassword(newEncryptedPassword);
        return accountDAO.updateAccount(account);
    }


    @Override
    public void deactivate(String email, String password) {
        AccountDAO accountDAO = DAOFactory.getInstance().getAccountDAO();
        Account account = accountDAO.getAccount(email, password);
        if (account != null) {
            account.setEmail(account.getEmail() + "_deactivated");
            account.setPassword("");
            account.setName(account.getName() + "_deactivated");
            account.setSurname(account.getSurname() + "_deactivated");
        }
        accountDAO.updateAccount(account);
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
