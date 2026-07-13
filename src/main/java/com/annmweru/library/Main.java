package com.annmweru.library;

import com.annmweru.library.model.Book;
import com.annmweru.library.service.LibraryService;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
        Book book2 = new Book("lonely", "J.R.R. jackson", "12345");

        LibraryService library = new LibraryService();
        library.addBook(book1);
        library.addBook(book2);



    }
}
