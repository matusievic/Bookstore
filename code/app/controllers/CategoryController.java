package controllers;

import entities.account.AccountType;
import entities.category.Category;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.ServiceFactory;
import services.category.CategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryController extends Controller {
    @Inject
    private FormFactory formFactory;

    public Result update(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        Category category = categoryService.get(id);

        if (category == null) {
            return ok(views.html.error.error500.render());
        }

        Form<Category> categoryForm = formFactory.form(Category.class).bindFromRequest();

        category.setName(categoryForm.get().getName());
        categoryService.updateCategory(category);

        List<Category> categories = categoryService.getCategories();

        return ok(views.html.category.categories.render(categories));
    }

    private boolean isAccountHasAccess() {
        String accountType = session("accountType");
        return accountType != null && accountType.equals(AccountType.ADMIN.toString());
    }

    public Result delete(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        Category category = categoryService.get(id);
        categoryService.deleteCategory(category);

        List<Category> categories = categoryService.getCategories();

        return ok(views.html.category.categories.render(categories));
    }

    public Result edit(int id) {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<Category> categoryForm = formFactory.form(Category.class);
        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        Category category = categoryService.get(id);

        return ok(views.html.category.edit.render(category, categoryForm));

    }

    public Result categories() {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        List<Category> categories = categoryService.getCategories();

        return ok(views.html.category.categories.render(categories));

    }

    public Result create() {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<Category> categoryForm = formFactory.form(Category.class).bindFromRequest();
        return ok(views.html.category.create.render(categoryForm));
    }

    public Result save() {
        String previousPage = request().getHeaders().get("referer").orElse("/");
        if (!isAccountHasAccess()) { return redirect(previousPage); }

        Form<Category> categoryForm = formFactory.form(Category.class).bindFromRequest();
        CategoryService categoryService = ServiceFactory.getInstance().getCategoryService();
        categoryService.add(categoryForm.get().getName());

        List<Category> categories = categoryService.getCategories();

        return ok(views.html.category.categories.render(categories));
    }
}
