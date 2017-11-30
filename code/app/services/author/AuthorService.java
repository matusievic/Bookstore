package services.author;

import entities.author.Author;
import entities.category.Category;

import java.util.List;

public interface AuthorService {
    Author get(String name, String surname);
    List<Author> getAuthors();
    int getAuthorsCount();

    Author updateAuthor(Author author);
    void deleteAuthor(Author author);
}
