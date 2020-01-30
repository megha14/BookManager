package com.company.model;

import com.company.model.connection.DatabaseConnection;
import com.company.model.repository.BookRepository;
import com.company.utils.DatabaseUtils;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepoImpl implements BookRepository {

    DatabaseUtils databaseUtils;
    DatabaseConnection databaseConnection;

    public BookRepoImpl() {
        databaseUtils = new DatabaseUtils();
        databaseConnection = new DatabaseConnection();
    }

    @Override
    public List<Book> getAllBooksFromLibrary() {
        Connection connection = databaseConnection.getConnection();
        List<Book> allBooks = new ArrayList<Book>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));

                allBooks.add(book);
            }
            return allBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getBookFromLibrary(int id) {
        Connection connection = databaseConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE id = " + id);
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(id);
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));

                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addBookToLibrary(Book book) {
        Connection connection = databaseConnection.getConnection();
        int id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book VALUES (NULL, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDescription());
            int i = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id=resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean updateBookInLibrary(int id, String title, String author, String description) {
        Connection connection = databaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET title=?, author=?, description=? WHERE id=?");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, id);
            int i = preparedStatement.executeUpdate();

            if (i == 1) {

                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> searchForBook(String search) {
        Connection connection = databaseConnection.getConnection();
        List<Book> allBooks = new ArrayList<Book>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE (title like '%"+search+"%')");

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));

                allBooks.add(book);
            }
            return allBooks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveLibrary() {

        String command = "mysqldump -u"+ databaseUtils.getUser()+ " -p"+databaseUtils.getPass()+" " +databaseUtils.getDatabaseName()+" -r " +databaseUtils.getBackupPath();
        System.out.println(command);
        Process process;
        try {
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(command);

            int processComplete = process.waitFor();
            System.out.println(processComplete);
            if (processComplete == 0) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> loadLibrary() {
        String[] command = new String[]{"mysql", "--user=" + databaseUtils.getUser(), "--password=" + databaseUtils.getPass(), databaseUtils.getDatabaseName(),"-e", " source "+databaseUtils.getBackupPath()};

        System.out.println(command);
        Process process;
        try {
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(command);
            int processComplete = process.waitFor();
            System.out.println(processComplete);
            if (processComplete == 0) {
                return getAllBooksFromLibrary();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
