package com.letscodethemup.model.repository;

import com.letscodethemup.model.Book;
import java.util.List;

/**
 * BookRepository.java
 * Purpose: Interface that defines the methods to be implemented to fetch records from database
 *
 * @author Megha Rastogi
 * @version 1.0 1/30/2020
 */

public interface BookRepository {

    /**
     * Get all books from the database
     *
     * @return list of Book objects
     */
    List<Book> getAllBooksFromLibrary();

    /**
     * Get book with given id from the database
     *
     * @param id integer
     * @return  Book object
     */
    Book getBookFromLibrary(int id);

    /**
     * Add a given book object to the database
     *
     * @param book object
     * @return integer representing book stored (1) or not
     */
    int addBookToLibrary(Book book);

    /**
     * Update a given book with new details to the database
     *
     * @param id integer
     * @param title string
     * @param author string
     * @param description string
     * @return boolean representing book updated or not
     */
    boolean updateBookInLibrary(int id, String title, String author, String description);

    /**
     * Search for books containing given string
     *
     * @param search string
     * @return list of Book objects
     */
    List<Book> searchForBook(String search);

    /**
     * Save library to disk
     *
     * @return boolean, library saved or not
     */
    boolean saveLibrary();

    /**
     * Load books from disk to database.
     *
     * @return boolean, library loaded or not
     */
    boolean loadLibrary();
}
