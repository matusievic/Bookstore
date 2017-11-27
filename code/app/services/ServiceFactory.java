package services;

import services.account.AccountService;
import services.account.impl.AccountServiceImpl;
import services.category.CategoryService;
import services.category.impl.CategoryServiceImpl;

public final class ServiceFactory {
	private static ServiceFactory instance = new ServiceFactory();
	private AccountService accountService = new AccountServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();

	private ServiceFactory() {}
	public static ServiceFactory getInstance() {
		return instance;
	}

	public AccountService getAccountService() {
		return accountService;
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
}