package com.annmweru.library.database;

import com.annmweru.library.enums.BookStatus;
import com.annmweru.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public  final Connection connection;
    public  BookRepository(Connection connection){
        this.connection = connection;
    }
    public  boolean insertBook(Book book){
        String sql = "INSERT INTO books (title, author, isbn,status) VALUES (?, ?, ?,?)";
        try (PreparedStatement psmt = this.connection.prepareStatement(sql)){
            psmt.setString(1,book.getTitle());
            psmt.setString(2,book.getAuthor());
            psmt.setString(3,book.getIsbn());
            psmt.setString(4,book.getStatus().name());

            int rows = psmt.executeUpdate();
            return rows > 0;

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    } public List<Book> findAllBooks(){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * from books";
        try(PreparedStatement psmt = connection.prepareStatement(sql); ResultSet rs = psmt.executeQuery())
          {
              while(rs.next()){
                  String title = rs.getString("title");
                  String author = rs.getString("author");
                  String isbn = rs.getString("isbn");
                  String status = rs.getString("status");
                  Book book = new Book(title, author, isbn);
                  book.setStatus(BookStatus.valueOf(status));
                  books.add(book);
              }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        };
        return books;

    }
    public Book findBookByIsbn(String isbn) {
        String  sql = "SELECT * FROM books WHERE isbn = ?";
        try(PreparedStatement pmst = connection.prepareStatement(sql))  {
           pmst.setString(1,isbn);
           ResultSet rs = pmst.executeQuery();
           if(rs.next()){
               String title = rs.getString("title");
               String author = rs.getString("author");
               String bookIsbn = rs.getString("isbn");
               String status = rs.getString("status");
               Book book = new Book(title, author, bookIsbn);
               book.setStatus(BookStatus.valueOf(status));
                return book;
           }
           return  null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  boolean borrowBook (String isbn){
        String sql = "UPDATE books SET status = ? WHERE isbn = ? AND status = ?";
        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,"BORROWED");
            psmt.setString(2,isbn);
            psmt.setString(3,"AVAILABLE");
            int row = psmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public  boolean returnBook (String isbn){
        String sql = "UPDATE books SET status = ? WHERE isbn = ? AND status = ?";
        try(PreparedStatement psmt = connection.prepareStatement(sql)){
            psmt.setString(1,"AVAILABLE");
            psmt.setString(2,isbn);
            psmt.setString(3,"BORROWED");
            int row = psmt.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean deleteBook(String isbn){
        String sql = " DELETE FROM books WHERE isbn = ?";
        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1,isbn);
            int row = psmt.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Book> findAvailableBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE status = 'AVAILABLE'";
        try (PreparedStatement psmt = connection.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                String status = rs.getString("status");

                Book book = new Book(title, author, isbn);
                book.setStatus(BookStatus.valueOf(status));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;


    }
    public List<Book> findBorrowedBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE status = 'BORROWED'";
        try (PreparedStatement psmt = connection.prepareStatement(sql);
             ResultSet rs = psmt.executeQuery()) {
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                String status = rs.getString("status");

                Book book = new Book(title, author, isbn);
                book.setStatus(BookStatus.valueOf(status));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;


    }
    }
