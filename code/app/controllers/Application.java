package controllers;

import entities.account.Account;
import play.mvc.*;
import play.data.*;

import services.account.AccountService;
import services.account.AccountServiceFactory;
import views.html.*;

import javax.inject.Inject;

public class Application extends Controller {
    @Inject
    FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }

    public Result catalog() {
        return TODO;
    }

    public Result help() {
        return TODO;
    }

    public Result orders() {
        return TODO;
    }

    public Result users() {
        return TODO;
    }

    public Result about() {
        return TODO;
    }

    public Result account() {
        if (!session().containsKey("email")) {
            return ok(index.render());
        }
        AccountService accountService = AccountServiceFactory.getInstance().getAccountService();
        String email = session("email");
        String password = session("password");
        Account currentAccount = accountService.getAccountInfo(email, password);
        return ok(account.render(currentAccount));
    }


    public Result logIn() {
        Form<AccountController.LoginForm> form = formFactory.form(AccountController.LoginForm.class);
    	return ok(login.render(form));
    }

    public Result signUp() {
        Form<AccountController.SignupForm> form = formFactory.form(AccountController.SignupForm.class);
        return ok(signup.render(form));
    }
}
