package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

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
    	return ok(login.render());
    }

    public Result signUp() {
        return ok(signup.render());
    }

    public static class LoginForm {
    	public String email;
    	public String password;
    }
}
