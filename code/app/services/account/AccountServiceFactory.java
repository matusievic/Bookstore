package services.account;


import services.account.impl.AccountServiceImpl;

public final class AccountServiceFactory {
	private static AccountServiceFactory instance = new AccountServiceFactory();
	private AccountService accountService = new AccountServiceImpl();

	private AccountServiceFactory() {}

	public static AccountServiceFactory getInstance() {
		return instance;
	}

	public AccountService getAccountService() {
		return accountService;
	}
}