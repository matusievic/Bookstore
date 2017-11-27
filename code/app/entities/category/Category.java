package entities.category;

import io.ebean.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category extends Model {
    @Id
    private int id;
    private String name;

    public static final Finder<Integer, Category> find = new Finder<>(Category.class);

    public Category() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
