package dao.category.impl;

import dao.category.CategoryDAO;
import entities.category.Category;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MySQLCategoryDAO implements CategoryDAO {
    @Override
    public Category createCategory(Category category) {
        Optional<Category> maxIdCategory = Category.find.all().stream().max(Comparator.comparing(Category::getId));
        int maxId = 0;
        if (maxIdCategory.isPresent()) {
            maxId = maxIdCategory.get().getId() + 1;
        }
        category.setId(maxId);
        category.save();
        return category;
    }

    @Override
    public Category readCategory(String categoryName) {
        Optional<Category> result = Category.find.all().stream().filter(c -> c.getName().equals(categoryName)).findFirst();
        return result.orElse(null);
    }

    @Override
    public Category readCategory(int id) {
        return Category.find.byId(id);
    }

    @Override
    public Category updateCategory(Category category) {
        category.update();
        return category;
    }

    @Override
    public void deleteCategory(Category category) {
        category.delete();
    }

    @Override
    public List<Category> getCategories() {
        return Category.find.all();
    }
}
