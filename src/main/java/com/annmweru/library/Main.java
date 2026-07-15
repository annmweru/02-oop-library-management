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
        Book myBook = library.searchBook("67890");
        if(myBook == null){
            System.out.println("Book was not found.");
        } else {
            System.out.println("Book found: " + myBook);
        }
    }
}
