package com.company.model.repository;

import com.company.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooksFromLibrary();
    Book getBookFromLibrary(int id);
    int addBookToLibrary(Book book);
    boolean updateBookInLibrary(int id, String title, String author, String description);
    List<Book> searchForBook(String search);
    boolean saveLibrary();
    List<Book> loadLibrary();
}
