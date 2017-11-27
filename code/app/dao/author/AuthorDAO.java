package dao.author;

import entities.author.Author;

public interface AuthorDAO {
    Author createAuthor(String name, String surname, String description);
    Author readAuthor(String name, String surname);
    Author updateAuthor(Author author);
    void deleteAuthor(Author author);
}
