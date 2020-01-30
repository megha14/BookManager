package com.letscodethemup.controller;

import com.letscodethemup.model.Book;
import com.letscodethemup.model.BookRepoImpl;
import java.util.List;
import java.util.Scanner;

/**
 * BookManager.java
 * Purpose: Acts as controller between the CLI and the model class
 *
 * @author Megha Rastogi
 * @version 1.0 1/30/2020
 */
public class BookManager {

    private BookRepoImpl bookRepo;
    private List<Book> books;

    /**
     * Constructor to instantiate this class and initialize required objects .
     */
    BookManager(){
        bookRepo = new BookRepoImpl();
    }

    /**
     * Method to print the main menu
     *
     * @return no return values
     */
    public void printMainMenu(){
        System.out.println("\n==== Book Manager ====\n");
        System.out.println("\t 1) View all books");
        System.out.println("\t 2) Add a book");
        System.out.println("\t 3) Edit a book");
        System.out.println("\t 4) Search for a books");
        System.out.println("\t 5) Save and exit\n");
        System.out.print("Choose [1-5]: ");
    }

    /**
     * Method to print all books fetched from the database
     *
     * @return no return values
     */
    public void printAllBooks(){

        books = bookRepo.getAllBooksFromLibrary();
        System.out.println("\n==== View Books ====\n");
        printBooksHelper(books);
        while (true) {
            System.out.println("\nTo view details enter the book ID, to return press <Enter>.");
            System.out.print("Book ID: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (id.equals("")) {
                break;
            }
            else {
                int ID = Integer.parseInt(id);
                if(checkIdBounds(ID, books)){
                 System.out.println("Only choose from displayed IDs");
                }else
                    printBookDetails(ID);
            }
        }
        start();
    }

    /**
     * Helper Method to check whether given input is greater than smallest ID
     * in the list of books and less than the highest ID in the list of books
     *
     * @param ID Integer representing the input from user
     * @param books List of Book objects
     * @return boolean
     */
    public boolean checkIdBounds(int ID, List<Book> books){
        return ID < books.get(0).getId() || ID > books.get(books.size()-1).getId();
    }

    /**
     * Method to ask ID of the book the user wants to edit
     *
     * @return no return value
     */
    public void editBook() {
        books = bookRepo.getAllBooksFromLibrary();
        System.out.println("\n==== Edit a Book ====\n");
        printBooksHelper(books);
        while (true) {
            System.out.println("\nEnter the book ID of the book you want to edit; to return press <Enter>.");
            System.out.print("Book ID: ");
            Scanner sc = new Scanner(System.in);
            String id = sc.nextLine();
            if (id.equals("")) {
                break;
            } else {
                int ID = Integer.parseInt(id);
                if(checkIdBounds(ID, books)){
                    System.out.println("Only choose from displayed IDs");
                }else
                    updateBookDetails(ID);
            }
        }
        start();
    }

    /**
     * Helper Method to get the details of the book user wants to edit
     * Then call the appropriate method to update the details in database.
     *
     * @param id integer
     * @return no return value
     */
    public void updateBookDetails(int id) {
        Book book = bookRepo.getBookFromLibrary(id);
        System.out.println("\nInput the following information. To leave a field unchanged, hit <Enter>\n");
        Scanner sc = new Scanner(System.in);
        String title = null, author = null, description = null;
        System.out.print("\t "+"Title: "+book.getTitle()+": <Enter> ");
        title = sc.nextLine();
        title = (title.equals("")) ? book.getTitle() : title;
        System.out.print("\t "+"Author: "+book.getAuthor()+": <Enter> ");
        author = sc.nextLine();
        author = (author.equals("")) ? book.getAuthor() : author;
        System.out.print("\t "+"Description: "+book.getDescription()+": <Enter> ");
        description = sc.nextLine();
        description = (description.equals("")) ? book.getDescription() : description;
        boolean edit = bookRepo.updateBookInLibrary(id, title, author, description);
        if(edit)
            System.out.println("Book saved");
        else
            System.out.println("Couldn't update the book. Something went wrong. Please try again!");
    }

    /**
     * Method to get the book details from user and add it to database
     *
     * @return no return value
     */
    public void addBookDetails(){
        System.out.println("\n==== Add a Book ====\n");
        System.out.println("Please enter the following information:");
        Book book = new Book();
        Scanner sc = new Scanner(System.in);
        System.out.print("\t "+"Title: ");
        String t = sc.nextLine();
        book.setTitle(t);
        System.out.print("\t "+"Author: ");
        String a = sc.nextLine();
        book.setAuthor(a);
        System.out.print("\t "+"Description: ");
        String d = sc.nextLine();
        book.setDescription(d);
        int add = bookRepo.addBookToLibrary(book);
        if(add != 0){
            System.out.println("\nBook ["+add+"] Saved");
        }else{
            System.out.println("\nCouldn't add the book. Something went wrong. Please try again!");
        }
        start();
    }

    /**
     * Method to print the details of a particular book
     *
     * @param id book to be printed
     * @return no return value
     */
    public void printBookDetails(int id){
        Book book = bookRepo.getBookFromLibrary(id);
        System.out.println("\n\t "+"ID: "+book.getId());
        System.out.println("\t "+"Title: "+book.getTitle());
        System.out.println("\t "+"Author: "+book.getAuthor());
        System.out.println("\t "+"Description: "+book.getDescription());
    }

    /**
     * Method to get the search string from the user and return the
     * books matching that string.
     *
     * @return no return value
     */
    public void searchBooks(){
        System.out.println("\n==== Search ====\n");
        System.out.println("Type in one or more keywords to search for\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("\t "+"Search: ");
        String search = sc.nextLine();
        books = bookRepo.searchForBook(search);
        System.out.println("The following books matched your query. Enter the book ID to see more details, or <Enter> to return.\n");
        printBooksHelper(books);
        while(true){
            System.out.println();
            System.out.print("Book ID: ");
            String choice = sc.nextLine();
            if(choice.equals("")) {
                break;
            }else {
                int ID = Integer.parseInt(choice);
                if(checkIdBounds(ID, books)){
                    System.out.println("Only choose from displayed IDs");
                }else
                    printBookDetails(ID);
            }

        }
        start();
    }

    /**
     * Helper Method to print all books from the list
     *
     * @param books arraylist containing all Book objects
     * @return no return value
     */
    public void printBooksHelper(List<Book> books){
        for (Book book : books) {
            System.out.println("\t "+"["+book.getId()+"] "+book.getTitle());
        }
    }

    /**
     * Method to save database to file
     *
     * @return no return value
     */
    public void saveToDisk(){
        boolean save = bookRepo.saveLibrary();
        if(save){
            System.out.println("Library saved.\n");
        }else{
            System.out.println("Can't save library to file. Please try again later.");
            start();
        }
    }

    /**
     * Method to display main menu to user and
     * switch to correct method based on user's choice
     *
     * @return no return value
     */
    public void start() {
        // write your code here

        printMainMenu();
        Scanner sc = new Scanner(System.in);
        Integer choice = 0;
        choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    printAllBooks();
                    break;
                case 2:
                    addBookDetails();
                    break;
                case 3:
                    editBook();
                    break;
                case 4:
                    searchBooks();
                    break;
                case 5:
                    saveToDisk();
                    break;
            }
    }

    /**
     * Method to load the database from the disk.
     *
     * @return no return value
     */
    public void loadBooksFromDisk(){
        bookRepo = new BookRepoImpl();
        boolean loaded = bookRepo.loadLibrary();
        if(loaded){
            books = bookRepo.getAllBooksFromLibrary();
            System.out.println("Loaded "+books.size()+" books from the library.\n");
            start();
        }else{
            System.out.println("Problem in loading books from database, try again later.\n");
        }
    }

    /**
     * The main method begins execution of the Book Manager Application.
     *
     * @param args not used
     */
    public static void main(String args[]){
        BookManager bookManager = new BookManager();
        bookManager.loadBooksFromDisk();

    }
}
