package com.letscodethemup.model;

/**
 * Book.java
 * Purpose: POJO to emulate Book objects
 *
 * @author Megha Rastogi
 * @version 1.0 1/30/2020
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String description;

    /**
     * Gets the book id.
     *
     * @return book id as integer
     */
    public int getId() {
        return id;
    }

    /**
     * Set the book id.
     *
     * @param id integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the book title.
     *
     * @return book title as string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the book title.
     *
     * @param title string
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the book author.
     *
     * @return book author as string
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the book author.
     *
     * @param author string
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the book description.
     *
     * @return book description as string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the book decription.
     *
     * @param description string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
