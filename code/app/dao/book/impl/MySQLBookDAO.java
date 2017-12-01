package dao.book.impl;

import dao.book.BookDAO;
import entities.book.Book;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MySQLBookDAO implements BookDAO {
    @Override
    public Book createBook(Book book) {
        Optional<Book> maxIdBook = Book.find.all().stream().max(Comparator.comparing(Book::getId));
        int maxId = 0;
        if (maxIdBook.isPresent()) {
            maxId = maxIdBook.get().getId() + 1;
        }
        book.setId(maxId);
        book.save();
        return book;
    }

    @Override
    public Book readBook(String name, int authorId) {
        Optional<Book> result = Book.find.all().stream().filter(b -> b.getName().equals(name) && b.getAuthorId() == authorId).findFirst();
        return result.orElse(null);
    }

    @Override
    public Book readBook(int id) {
        return Book.find.byId(id);
    }

    @Override
    public Book updateBook(Book book) {
        book.update();
        return book;
    }

    @Override
    public void deleteBook(Book book) {
        book.delete();
    }

    @Override
    public List<Book> getBooks() {
        return Book.find.all();
    }
}
