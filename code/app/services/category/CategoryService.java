package services.category;

import entities.category.Category;

import java.util.List;

public interface CategoryService {
    Category add(String categoryName);
    Category get(String categoryName);
    Category get(int categoryId);
    List<Category> getCategories();
    int getCategoriesCount();

    Category updateCategory(Category category);
    void deleteCategory(Category category);
}
