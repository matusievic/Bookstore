package services.account;

import entities.user.User;

public interface AccountService {
    User authenticate(String login, String password);
}
