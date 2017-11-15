package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import javax.inject.Inject;

public class Application extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result catalog() {
        return TODO;
    }

    public Result help() {
        return TODO;
    }

    public Result about() {
        return TODO;
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
