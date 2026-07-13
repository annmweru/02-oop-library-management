package com.annmweru.library;

import com.annmweru.library.model.Book;

public class Main {
    public static void main(String[] args) {
        Book books = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
        System.out.println("Book title: " + books.getTitle());
        System.out.println("Book author: " + books.getAuthor());


    }
}
