package entities.book;

import io.ebean.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book extends Model {
    @Id
    private int id;
    private String name;
    private int authorId;
    private int categoryId;
    private double price;
    private String description;

    public static final Finder<Integer, Book> find = new Finder<>(Book.class);

    public Book() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
