package dao.author.impl;

import dao.author.AuthorDAO;
import entities.author.Author;

import java.util.Comparator;
import java.util.Optional;

public class MySQLAuthorDAO implements AuthorDAO {
    @Override
    public Author createAuthor(String name, String surname, String description) {
        Optional<Author> maxIdAuthor = Author.find.all().stream().max(Comparator.comparing(Author::getId));

        int maxId = 0;
        if (maxIdAuthor.isPresent()) {
            maxId = maxIdAuthor.get().getId();
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
        if (result.isPresent()) {
            return result.get();
        }
        return null;
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
}
