package controllers;

import entities.account.AccountType;
import entities.author.Author;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import services.ServiceFactory;
import services.author.AuthorService;
import views.html.index;

import javax.inject.Inject;
import java.util.List;

import static play.mvc.Controller.session;
import static play.mvc.Results.ok;

public class AuthorController {
    @Inject
    FormFactory formFactory;

    public Result update(int id) {
        String accountType = session("accountType");
        if (accountType == null || !accountType.equals(AccountType.ADMIN.toString())) {
            return ok(index.render());
        }

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        Author author = authorService.get(id);

        if (author == null) {
            return ok(views.html.error.error500.render());
        }

        Form<Author> authorForm = formFactory.form(Author.class).bindFromRequest();

        author.setName(authorForm.get().getName());
        author.setSurname(authorForm.get().getSurname());
        author.setDescription(authorForm.get().getDescription());
        authorService.updateAuthor(author);
        return ok(views.html.author.author.render(author, authorForm));
    }

    public Result delete(int id) {
        String accountType = session("accountType");
        if (accountType == null || !accountType.equals(AccountType.ADMIN.toString())) {
            return ok(index.render());
        }

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        Author author = authorService.get(id);
        authorService.deleteAuthor(author);

        List<Author> authors = authorService.getAuthors();

        return ok(views.html.author.authors.render(authors));
    }

    public Result edit(int id) {
        String accountType = session("accountType");
        if (accountType == null || !accountType.equals(AccountType.ADMIN.toString())) {
            return ok(index.render());
        }

        Form<Author> authorForm = formFactory.form(Author.class);
        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        Author author = authorService.get(id);

        return ok(views.html.author.author.render(author, authorForm));

    }

    public Result authors() {
        String accountType = session("accountType");
        if (accountType == null || !accountType.equals(AccountType.ADMIN.toString())) {
            return ok(index.render());
        }

        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        List<Author> authors = authorService.getAuthors();

        return ok(views.html.author.authors.render(authors));

    }

    public Result create() {
        String accountType = session("accountType");
        if (accountType == null || !accountType.equals(AccountType.ADMIN.toString())) {
            return ok(index.render());
        }

        Form<Author> authorForm = formFactory.form(Author.class).bindFromRequest();
        return ok(views.html.author.create.render(authorForm));
    }

    public Result save() {
        String accountType = session("accountType");
        if (accountType == null || !accountType.equals(AccountType.ADMIN.toString())) {
            return ok(index.render());
        }

        Form<Author> authorForm = formFactory.form(Author.class).bindFromRequest();
        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();

        String name = authorForm.get().getName();
        String surname = authorForm.get().getSurname();
        String description = authorForm.get().getDescription();

        authorService.add(name, surname, description);

        List<Author> authors = authorService.getAuthors();

        return ok(views.html.author.authors.render(authors));
    }
}
