package entities.order;

import entities.book.Book;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BookOrder extends Model {
    @Id
    private int id;
    private String accountId;
    private String name;
    private String surname;
    private String date;
    private String address;
    private int zip;
    private String phone;
    private String books;
    private OrderStatus status;
    private String answer;
    public static final Finder<Integer, BookOrder> find = new Finder<>(BookOrder.class);

    public BookOrder() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getZip() {
        return zip;
    }
    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getBooks() {
        return books;
    }
    public void setBooks(String books) {
        this.books = books;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookOrder order = (BookOrder) o;

        if (id != order.id) return false;
        if (zip != order.zip) return false;
        if (accountId != null ? !accountId.equals(order.accountId) : order.accountId != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (phone != null ? !phone.equals(order.phone) : order.phone != null) return false;
        if (status != order.status) return false;
        return answer != null ? answer.equals(order.answer) : order.answer == null;
    }
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + zip;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
