# BookManager

Java console based application to manage books in the database. Using command line users can view all books, 
view single book, add books, edit books, search for books and save a backup of the library.

## Getting Started

Download the whole code or just the [BookManager.jar](https://github.com/megha14/BookManager/tree/master/out/artifacts/BookManager_jar).

### Prerequisites

* JDK 1.8+ installed
* MySql installed and running

### MySQL setup

1. Create database with name books - `CREATE DATABASE books;`
2. Change to database books - `USE books;`
3. Create table book. - 

  ```
  CREATE TABLE book (
  id int NOT NULL AUTO_INCREMENT,
  title varchar(255) NOT NULL,
  author varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  PRIMARY KEY (id)
  );
 ```
4. Insert some values into the table - `INSERT INTO book (title, author, description) VALUES ('Moby Dick','Herman Melville','About a big whale');`
5. Create backup of the database - `mysqldump -u dummy -pdummy books > /Users/Megha/temp/backup.sql;`
   Change the username, password and backup location based on your system.

### Changes in Code

1. Open DatabaseUtils class in com.letscodethemup.utils.
2. Update user, pass, host, port with your MySQL's username, password, host, and port.
3. Update backupPath with the location of your backup file.

### Steps to Run the jar file

1. Open the terminal
2. Move to the location of jar file
3. type in `java -jar BookManager.jar`


## Built With

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Language in which code is written
* [MySQL 8.0.19](https://dev.mysql.com/downloads/mysql/) - database used to store books
* [IntelliJIdea](https://www.jetbrains.com/idea/download/#section=mac) - IDE used for development 
