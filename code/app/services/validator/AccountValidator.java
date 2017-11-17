package services.validator;

public final class AccountValidator {
    private static String nameRegex = "[A-Z][a-z]+";
    private static String surnameRegex = "[A-Z][a-z]+";
    private static String emailRegex = "\\w+@\\w+.[a-z]+";
    private static String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    public static boolean validateName(String name) {
        if (name == null || name.isEmpty()) { return false; }
        return name.matches(nameRegex);
    }

    public static boolean validateSurname(String surname) {
        if (surname == null || surname.isEmpty()) { return false; }
        return surname.matches(surnameRegex);
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) { return false; }
        return email.matches(emailRegex);
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) { return false; }
        return password.matches(passwordRegex);
    }

}
