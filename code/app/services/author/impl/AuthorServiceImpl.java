package services.author.impl;

import dao.DAOFactory;
import dao.author.AuthorDAO;
import entities.author.Author;
import services.author.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
    @Override
    public Author get(String name, String surname) {
        return authorDAO.readAuthor(name, surname);
    }

    @Override
    public List<Author> getAuthors() {
        return authorDAO.getAuthors();
    }

    @Override
    public int getAuthorsCount() {
        List<Author> authors = authorDAO.getAuthors();
        if (authors == null) {
            return 0;
        }
        return authors.size();
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorDAO.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorDAO.deleteAuthor(author);
    }
}
