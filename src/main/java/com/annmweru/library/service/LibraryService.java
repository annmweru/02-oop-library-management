package com.annmweru.library.service;

import com.annmweru.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    List<Book> books = new ArrayList<Book>();
    public  void addBook(Book newBook){
        this.books.add(newBook);

    }


}
