package com.annmweru.library;

import com.annmweru.library.model.Book;
import com.annmweru.library.service.LibraryService;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
        Book book2 = new Book("lonely", "J.R.R. jackson", "67890");
        LibraryService library = new LibraryService();
        library.addBook(book1);
        library.addBook(book2);
        boolean isDeleted = library.deleteBook(book1.getIsbn());
        if (isDeleted){
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
        Book myBook = library.searchBook("12345");

        if(myBook == null){
            System.out.println("Book was not found.");
        } else {
            System.out.println("Book found: " + myBook);
        }
        boolean isBorrowed = library.borrowBook(book1.getIsbn());
        if(isBorrowed){
            System.out.println("The book was successfully borrowed.");
        } else {
            System.out.println("Unable to borrow the book.");
        }
//        Book myBook = library.searchBook("12345");

        System.out.println( "Book Status: " + myBook.getStatus());



    }
}
