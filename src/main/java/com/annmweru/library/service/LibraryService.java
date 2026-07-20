package com.annmweru.library.service;

import com.annmweru.library.enums.BookStatus;
import com.annmweru.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books = new ArrayList<Book>();

    public boolean addBook(Book newBook) {
        String isbn = newBook.getIsbn();
        if (bookExists(isbn)) {
            return false;
        }
        this.books.add(newBook);
            return true;
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
        return null;
    };
    public boolean deleteBook(String isbn){
         Book mybook = this.searchBook(isbn);
        if (mybook == null){
            return false;
        }
             return books.remove(mybook);
    };
    public  boolean borrowBook(String isbn){
        Book mybook = this.searchBook(isbn);
        if (mybook == null){
            return false;
        }
        if (mybook.getStatus() == BookStatus.BORROWED){
            return false;
        }
        mybook.setStatus(BookStatus.BORROWED);
        return true;
    }
    public boolean returnBook(String isbn){
        Book book = this.searchBook(isbn);
        if(book == null){
            return  false;
        }
        if(book.getStatus() == BookStatus.AVAILABLE){
            return false;
        }
        book.setStatus(BookStatus.AVAILABLE);
        return true;
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