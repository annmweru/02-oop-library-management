package com.annmweru.library.service;

import com.annmweru.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
     private List<Book> books = new ArrayList<Book>();
    public  void addBook(Book newBook){
        String isbn = newBook.getIsbn();
        if(bookExists(isbn)){
            System.out.println("The book already exists");
            return;
        }
        this.books.add(newBook);
        System.out.println("The book Added successfully");

    }
       private boolean  bookExists(String isbn) {
            for (Book book :this.books) {
          if (book.getIsbn().equals(isbn)){
              return  true;
          }
            }
           return false;
       };

    }