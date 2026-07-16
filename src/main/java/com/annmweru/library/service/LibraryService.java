package com.annmweru.library.service;

import com.annmweru.library.enums.BookStatus;
import com.annmweru.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books = new ArrayList<Book>();

    public void addBook(Book newBook) {
        String isbn = newBook.getIsbn();
        if (bookExists(isbn)) {
            System.out.println("The book already exists");
            return;
        }
        this.books.add(newBook);
        System.out.println("The book added successfully");

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
}