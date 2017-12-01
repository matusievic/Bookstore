package dao.book;

import entities.book.Book;

import java.util.List;

public interface BookDAO {
    Book createBook(Book book);
    Book readBook(String name, int authorId);
    Book readBook(int id);
    Book updateBook(Book book);
    void deleteBook(Book book);

    List<Book> getBooks();
}
