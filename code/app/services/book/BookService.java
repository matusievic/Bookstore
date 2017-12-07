package services.book;

import entities.book.Book;

import java.util.List;

public interface BookService {
    Book add(String bookName, String imageURL, int authorID, int categoryId, double price, int pageCount, String description);
    Book get(String bookName, int authorID);
    Book get(int bookId);
    List<Book> getBooks();
    int getBooksCount();
    List<Book> getSliderBooks();

    Book updateBook(Book book);
    void deleteBook(Book book);
}
