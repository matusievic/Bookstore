package dao.author;

import entities.author.Author;

import java.util.List;

public interface AuthorDAO {
    Author createAuthor(String name, String surname, String description);
    Author readAuthor(String name, String surname);
    Author readAuthor(int id);
    Author updateAuthor(Author author);
    void deleteAuthor(Author author);

    List<Author> getAuthors();
}
