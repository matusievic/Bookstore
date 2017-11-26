package controllers;

import entities.account.Account;
import entities.account.AccountType;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.account.AccountService;
import services.account.AccountServiceFactory;
import services.exception.ServiceException;
import services.validator.AccountValidator;
import views.html.index;
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
        String email = loginForm.get().email;
        String password = loginForm.get().password;

        Account account = null;
        try {
            account = AccountServiceFactory.getInstance().getAccountService().authenticate(email, password);
        } catch (ServiceException e) {
            return internalServerError(views.html.error.error500.render());
        }

        session().clear();
        session("email", account.getEmail());
        session("password", account.getPassword());
        session("accountType", account.getType().toString());

        return ok(index.render());
    }


    public Result register() {
        Form<SignupForm> signupForm = formFactory.form(SignupForm.class).bindFromRequest();
        if (signupForm.hasErrors()) {
            return badRequest(signup.render(signupForm));
        }
        AccountService accountService = AccountServiceFactory.getInstance().getAccountService();
        Account account = null;
        try {
            String email = signupForm.get().email;
            String password = signupForm.get().firstPassword;
            String name = signupForm.get().name;
            String surname = signupForm.get().surname;
            account = accountService.register(email, password, name, surname);
        } catch (ServiceException e) {
            return badRequest(views.html.error.error500.render());
        }

        session().clear();
        session("email", signupForm.get().email);
        session("accountType", AccountType.CUSTOMER.toString());
        return ok(index.render());
    }

    public Result logOut() {
        session().clear();
        return ok(index.render());
    }


    public Result changeEmail() {
        return TODO;
    }

    public Result changePassword() {
        return TODO;
    }

    public Result deactivate() {
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

    public static class EmailForm {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class PasswordForm {
        private String firstPassword;
        private String secondPassword;

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
