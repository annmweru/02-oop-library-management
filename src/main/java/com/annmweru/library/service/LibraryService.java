package com.annmweru.library.service;

import com.annmweru.library.enums.BookStatus;
import com.annmweru.library.exception.BookAlreadyAvailableException;
import com.annmweru.library.exception.BookAlreadyBorrowedException;
import com.annmweru.library.exception.BookAlreadyExistsException;
import com.annmweru.library.exception.BookNotFoundException;
import com.annmweru.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books = new ArrayList<Book>();

    public void addBook(Book newBook) {
        String isbn = newBook.getIsbn();
        if (bookExists(isbn)) {
            throw new BookAlreadyExistsException("A book with ISBN " + isbn + " already exists."
            );
        }
        this.books.add(newBook);
    }
    private boolean bookExists(String isbn) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    };

    public Book searchBook(String isbn){
        for(Book book : this.books){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        throw new BookNotFoundException("No book found with ISBN: " + isbn);
    };
    public void deleteBook(String isbn){
         Book mybook = this.searchBook(isbn);
          books.remove(mybook);
    };
    public  void borrowBook(String isbn){
        Book book = searchBook(isbn);
        if (book.getStatus() == BookStatus.BORROWED) {
            throw new BookAlreadyBorrowedException("The book is already borrowed.");
        };
        book.setStatus(BookStatus.BORROWED);
    }
    public void returnBook(String isbn){
        Book book = this.searchBook(isbn);
        if(book.getStatus() == BookStatus.AVAILABLE){
            throw new BookAlreadyAvailableException( "The book is already available.");
        }
        book.setStatus(BookStatus.AVAILABLE);
    }
    public  List<Book> listAllBooks(){
        return this.books;
    }
    public  List<Book> listAllAvailable(){
        List<Book> availableBooks = new ArrayList<>();
        for(Book allAvailable: this.books){
            if(allAvailable.getStatus() == BookStatus.AVAILABLE){
                availableBooks.add(allAvailable);
            }
        }
        return availableBooks;

    }
    public  List<Book> listAllBorrowed(){
        List<Book> borrowedBooks = new ArrayList<>();
        for(Book allBorrowed: this.books){
            if(allBorrowed.getStatus() == BookStatus.BORROWED){
                borrowedBooks.add(allBorrowed);
            }
        }
        return borrowedBooks;

    }
}