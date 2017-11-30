package dao.category;

import entities.category.Category;

import java.util.List;

public interface CategoryDAO {
    Category createCategory(String categoryName);
    Category readCategory(String categoryName);
    Category readCategory(int id);
    Category updateCategory(Category category);
    void deleteCategory(Category category);

    List<Category> getCategories();
}
