package dao.category.impl;

import dao.category.CategoryDAO;
import entities.category.Category;

import java.util.Comparator;
import java.util.Optional;

public class MySQLCategoryDAO implements CategoryDAO {
    @Override
    public Category createCategory(String categoryName) {
        Optional<Category> maxIdCategory = Category.find.all().stream().max(Comparator.comparing(Category::getId));
        int maxId = 0;
        if (maxIdCategory.isPresent()) {
            maxId = maxIdCategory.get().getId();
        }
        Category category = new Category();
        category.setId(maxId);
        category.setName(categoryName);
        category.save();
        return category;
    }

    @Override
    public Category readCategory(String categoryName) {
        Optional<Category> result = Category.find.all().stream().filter(c -> c.getName().equals(categoryName)).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        category.update();
        return category;
    }

    @Override
    public void deleteCategory(String categoryName) {
        Optional<Category> categoryToRemove = Category.find.all().stream().filter(c -> c.getName().equals(categoryName)).findFirst();
        if (categoryToRemove.isPresent()) {
            categoryToRemove.get().delete();
        }
    }
}
