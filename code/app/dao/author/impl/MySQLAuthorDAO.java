package dao.author.impl;

import dao.author.AuthorDAO;
import entities.author.Author;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MySQLAuthorDAO implements AuthorDAO {
    @Override
    public Author createAuthor(String name, String surname, String description) {
        Optional<Author> maxIdAuthor = Author.find.all().stream().max(Comparator.comparing(Author::getId));

        int maxId = 0;
        if (maxIdAuthor.isPresent()) {
            maxId = maxIdAuthor.get().getId() + 1;
        }

        Author author = new Author();
        author.setId(maxId);
        author.setName(name);
        author.setSurname(surname);
        author.setDescription(description);
        author.save();

        return author;
    }

    @Override
    public Author readAuthor(String name, String surname) {
        Optional<Author> result = Author.find.all().stream().filter(a -> a.getName().equals(name) && a.getSurname().equals(surname)).findFirst();
        return result.orElse(null);
    }

    @Override
    public Author readAuthor(int id) {
        return Author.find.byId(id);
    }

    @Override
    public Author updateAuthor(Author author) {
        author.update();
        return author;
    }

    @Override
    public void deleteAuthor(Author author) {
        author.delete();
    }

    @Override
    public List<Author> getAuthors() {
        return Author.find.all();
    }
}
