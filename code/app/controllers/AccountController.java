package controllers;

import entities.account.Account;
import entities.account.AccountType;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.account.AccountService;
import services.ServiceFactory;
import services.encryption.EncryptionServiceFactory;
import services.encryption.Encryptor;
import services.exception.EncryptorException;
import services.exception.ServiceException;
import services.validator.AccountValidator;
import views.html.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class AccountController extends Controller {
    @Inject
    private FormFactory formFactory;

    public Result authenticate() {
        Form<LoginForm> loginForm = formFactory.form(LoginForm.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        }
        String email = loginForm.get().email;
        String password = loginForm.get().password;

        Account account = null;
        try {
            account = ServiceFactory.getInstance().getAccountService().authenticate(email, password);
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
        AccountService accountService = ServiceFactory.getInstance().getAccountService();
        Account account = null;
        try {
            String email = signupForm.get().email;
            String password = signupForm.get().firstPassword;
            String name = signupForm.get().name;
            String surname = signupForm.get().surname;
            account = accountService.register(email, password, name, surname, AccountType.CUSTOMER);
        } catch (ServiceException e) {
            return badRequest(views.html.error.error500.render());
        }

        session().clear();
        session("email", account.getEmail());
        session("password", account.getPassword());
        session("accountType", account.getType().toString());
        return ok(index.render());
    }

    public Result logOut() {
        session().clear();
        return ok(index.render());
    }

    public Result changeName() {
        Form<NameForm> nameForm = formFactory.form(NameForm.class).bindFromRequest();
        Form<PasswordForm> passwordForm = formFactory.form(PasswordForm.class);

        AccountService accountService = ServiceFactory.getInstance().getAccountService();
        String email = session("email");
        String password = session("password");
        Account currentAccount = accountService.getAccountInfo(email, password);

        if (nameForm.hasErrors()) {
            return badRequest(account.render(currentAccount, nameForm, passwordForm));
        }

        String newName = nameForm.get().name;
        String newSurname = nameForm.get().surname;
        currentAccount  = accountService.changeName(email, password, newName, newSurname);

        return ok(account.render(currentAccount, nameForm, passwordForm));
    }

    public Result changePassword() {
        Form<NameForm> nameForm = formFactory.form(NameForm.class);
        Form<PasswordForm> passwordForm = formFactory.form(PasswordForm.class).bindFromRequest();

        AccountService accountService = ServiceFactory.getInstance().getAccountService();
        String email = session("email");
        String password = session("password");
        Account currentAccount = accountService.getAccountInfo(email, password);

        if (passwordForm.hasErrors()) {
            return badRequest(account.render(currentAccount, nameForm, passwordForm));
        }

        String newPassword = passwordForm.get().firstPassword;
        try {
            accountService.changePassword(email, password, newPassword);
        } catch (ServiceException e) {
            return internalServerError(views.html.error.error500.render());
        }

        session("password", currentAccount.getPassword());

        return ok(account.render(currentAccount, nameForm, passwordForm));
    }

    public Result deactivate() {
        if (session("email") == null) {
            ok(index.render());
        }

        String email = session("email");
        String password = session("password");
        AccountService accountService = ServiceFactory.getInstance().getAccountService();
        accountService.deactivate(email, password);

        session().clear();
        return ok(index.render());
    }

    public Result remove(String id) {
        if (session("accountType").equals(AccountType.ADMIN.toString())) {
            return ok(index.render());
        }

        AccountService accountService = ServiceFactory.getInstance().getAccountService();
        accountService.delete(id);

        List<Account> accounts = Account.find.all().stream().filter(a -> a.getType() != AccountType.ADMIN).collect(Collectors.toList());
        return ok(views.html.admin.accounts.render(accounts));
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
                if (!ServiceFactory.getInstance().getAccountService().isAccountExists(email, password)) {
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

            if (ServiceFactory.getInstance().getAccountService().isAccountExists(email)) {
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

    public static class PasswordForm {
        private String firstPassword;
        private String secondPassword;
        private String currentPassword;

        public String validate() {
            if (!AccountValidator.validatePassword(firstPassword)) {
                return "You've entered an incorrect password";
            }

            if (!firstPassword.equals(secondPassword)) {
                return "Passwords you entered doesn't match";
            }
            Encryptor encryptor = EncryptionServiceFactory.getInstance().getEncryptor();
            String encryptedPassword = null;
            try {
                encryptedPassword = encryptor.encrypt(currentPassword);
            } catch (EncryptorException e) {
                return "An server error has occured.\nPlease try again later";
            }

            if (!encryptedPassword.equals(session("password"))) {
                return "Please enter a correct current password";
            }

            return null;
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

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }
    }

    public static class NameForm {
        private String name;
        private String surname;

        public String validate() {
            if (!AccountValidator.validateName(name)) {
                return "You've entered an incorrect name";
            }

            if (!AccountValidator.validateSurname(surname)) {
                return "You've entered an incorrect surname";
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
    }
}
