package services.author;

import entities.author.Author;
import entities.category.Category;

import java.util.List;

public interface AuthorService {
    Author add(String name, String surname, String description);
    Author get(int id);
    Author get(String name, String surname);
    List<Author> getAuthors();
    int getAuthorsCount();

    Author updateAuthor(Author author);
    void deleteAuthor(Author author);
}
