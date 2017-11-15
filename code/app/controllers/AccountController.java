package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import javax.inject.Inject;

public class AccountController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result authenticate() {
    	Form<LoginForm> loginForm = formFactory.form(LoginForm.class).bindFromRequest();

    	return TODO ;
    }


    public Result register() {
        Form<SignupForm> signupForm = formFactory.form(SignupForm.class).bindFromRequest();

        return TODO;
    }

    public static class LoginForm {
        public String email;
        public String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class SignupForm {
        public String name;
        public String surname;
        public String email;
        public String firstPassword;
        public String secondPassword;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstPassword() {
            return firstPassword;
        }

        public void setFirstPassword(String firstPassword) {
            this.firstPassword = firstPassword;
        }

        public String getSecondPassword() {
            return secondPassword;
        }

        public void setSecondPassword(String secondPassword) {
            this.secondPassword = secondPassword;
        }
    }
}
