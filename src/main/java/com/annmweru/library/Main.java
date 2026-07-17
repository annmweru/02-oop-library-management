package com.annmweru.library;

import com.annmweru.library.model.Book;
import com.annmweru.library.service.LibraryService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
//        Book book2 = new Book("lonely", "J.R.R. jackson", "67890");
        Book book1 = new Book("Atomic Habits", "James Clear", "34567");
        Book book2 = new Book("The Pragmatic Programmer", "Andrew Hunt & David Thomas", "45678");
        LibraryService library = new LibraryService();
        library.addBook(book1);
        library.addBook(book2);
//        boolean isDeleted = library.deleteBook(book1.getIsbn());
//        if (isDeleted){
//            System.out.println("Book deleted successfully.");
//        } else {
//            System.out.println("Book not found.");
//        }
//        Book myBook = library.searchBook("67890");
//
//        if(myBook == null){
//            System.out.println("Book was not found.");
//        } else {
//            System.out.println("Book found: " + myBook);
//        }
        boolean isBorrowed = library.borrowBook(book2.getIsbn());
        if(isBorrowed){
            System.out.println("The book was successfully borrowed.");
        } else {
            System.out.println("Unable to borrow the book.");
        }
        Book myBook = library.searchBook("34567");

        System.out.println( "Book Status: " + myBook.getStatus());

        boolean isReturned = library.returnBook(book2.getIsbn());
        if(isReturned){
            System.out.println("The book is available.");
        } else {
            System.out.println("Not available");
        }
        System.out.println(myBook.getStatus());

        List<Book> allBooks = library.listAllBooks();
        for ( Book books:allBooks){
            System.out.println(books);

    }
        List<Book> allAvailable = library.listAllAvailable();
        for(Book book:allAvailable){
            System.out.println(book);
        }
        List<Book> allBorrowed = library.listAllBorrowed();
        for(Book book:allBorrowed){
            System.out.println(book);
        }



    }

}
