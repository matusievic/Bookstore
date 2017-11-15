package dao.account;

import entities.user.User;

public interface AccountDAO {
    User getUser(String login, String password);

}
