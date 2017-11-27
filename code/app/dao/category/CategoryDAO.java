package dao.category;

import entities.category.Category;

public interface CategoryDAO {
    Category createCategory(String categoryName);
    Category readCategory(String categoryName);
    Category updateCategory(Category category);
    void deleteCategory(String categoryName);
}
