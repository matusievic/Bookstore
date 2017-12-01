package services;

import services.account.AccountService;
import services.account.impl.AccountServiceImpl;
import services.author.AuthorService;
import services.author.impl.AuthorServiceImpl;
import services.book.BookService;
import services.book.impl.BookServiceImpl;
import services.category.CategoryService;
import services.category.impl.CategoryServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	private final AccountService accountService = new AccountServiceImpl();
	private final CategoryService categoryService = new CategoryServiceImpl();
	private final AuthorService authorService = new AuthorServiceImpl();
	private final BookService bookService = new BookServiceImpl();

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
	public AuthorService getAuthorService() {
		return authorService;
	}
	public BookService getBookService() {
		return bookService;
	}
}