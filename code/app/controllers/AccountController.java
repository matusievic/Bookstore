package controllers;

import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.account.AccountServiceFactory;
import services.exception.ServiceException;
import services.validator.AccountValidator;
import views.html.login;
import views.html.signup;

import javax.inject.Inject;

public class AccountController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result authenticate() {
    	Form<LoginForm> loginForm = formFactory.form(LoginForm.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        }


    	return TODO ;
    }


    public Result register() {
        Form<SignupForm> signupForm = formFactory.form(SignupForm.class).bindFromRequest();
        if (signupForm.hasErrors()) {
            return badRequest(signup.render(signupForm));
        }

        return TODO;
    }

    public static class LoginForm {
        public String email;
        public String password;

        public String validate() {
            if (!AccountValidator.validateEmail(email)) {
                return "You've entered an incorrect email address";
            }
            if (!AccountValidator.validatePassword(password)) {
                return "You've entered an incorrect password";
            }
            try {
                if (!AccountServiceFactory.getInstance().getAccountService().isAccountExists(email, password)) {
                    return "You've entered an incorrect login/password combination";
                }
            } catch (ServiceException e) {
                Logger.error("Cannot check if an account exists", e);
                return "There's some temporarily errors on the server.\n Please try to log in later.";
            }

            return null;
        }

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


        public String validate() {
            if (!AccountValidator.validateName(name)) {
                return "You've entered an incorrect name";
            }
            if (!AccountValidator.validateSurname(surname)) {
                return "You've entered an incorrect surname";
            }
            if (!AccountValidator.validateEmail(email)) {
                return "You've entered an incorrect email address";
            }
            if (!AccountValidator.validatePassword(firstPassword)) {
                return "You've entered an incorrect password";
            }
            if (!firstPassword.equals(secondPassword)) {
                return "Passwords you entered doesn't match";
            }

            if (AccountServiceFactory.getInstance().getAccountService().isAccountExists(email)) {
                return "Please provide an another email address.\nEmail address: " + email + " is connected with an another account";
            }

            return null;
        }


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
