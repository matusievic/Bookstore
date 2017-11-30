package services.category.impl;

import dao.DAOFactory;
import dao.account.AccountDAO;
import dao.category.CategoryDAO;
import entities.category.Category;
import services.category.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDAO = DAOFactory.getInstance().getCategoryDAO();

    @Override
    public Category add(String categoryName) {
        return categoryDAO.createCategory(categoryName);
    }

    @Override
    public Category get(String categoryName) {
        return categoryDAO.readCategory(categoryName);
    }
    @Override
    public Category get(int categoryId) {
        return categoryDAO.readCategory(categoryId);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDAO.getCategories();
    }

    @Override
    public int getCategoriesCount() {
        return categoryDAO.getCategories().size();
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDAO.deleteCategory(category);
    }
}
