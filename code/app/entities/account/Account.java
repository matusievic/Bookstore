package entities.account;

import io.ebean.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account extends Model {
    @Id
    private String email;
    private String password;
    private String name;
    private String surname;
    private AccountType type;

    public Account() {
    }


    public static final Finder<Integer, Account> find = new Finder<>(Account.class);

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
