package com.annmweru.library;

import com.annmweru.library.model.Book;
import com.annmweru.library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService library = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============================");
        System.out.println("Library Management System");
        System.out.println("=============================");

        int choice = 0;

        while(choice !=9){
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. List All Books");
            System.out.println("7. List Available Books");
            System.out.println("8. List Borrowed Books");
            System.out.println("9. Exit");
            System.out.print("Enter your choice:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:{
                    System.out.print("Enter the title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the Isbn: ");
                    String addBookIsbn = scanner.nextLine();

                    Book book = new Book(title ,author,addBookIsbn);
                     boolean added = library.addBook(book);
                     if(added){
                         System.out.println("Book added successfully." + title + "," + author + ", " + addBookIsbn);

                     } else {
                         System.out.println("Book not added. A book with this ISBN already exists.");
                     }
                    break;
                }
                case 2:{
                    System.out.print("Enter the Isbn: ");
                    String searchIsbn = scanner.nextLine();
                    Book myBook = library.searchBook(searchIsbn);
                    if(myBook != null){
                        System.out.println(myBook);

                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                }

                case 3:
                {
                    System.out.print("Enter the Isbn: ");
                    String searchIsbn = scanner.nextLine();
                    boolean isDeleted = library.deleteBook(searchIsbn);
                    if(isDeleted){
                        System.out.println("Book deleted successfully.");

                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                }


                case 4:
                {
                    System.out.print("Enter the Isbn: ");
                    String searchIsbn = scanner.nextLine();
                    boolean isBorrowed = library.borrowBook(searchIsbn);
                    if(isBorrowed){
                        System.out.println("Book borrowed successfully.");

                    } else {
                        System.out.println("Unable to borrow the book.");
                    }
                    break;
                }
                case 5:
                {
                    System.out.print("Enter the Isbn: ");
                    String searchIsbn = scanner.nextLine();
                    boolean isReturned = library.returnBook(searchIsbn);
                    if(isReturned){
                        System.out.println("Book returned successfully.");

                    } else {
                        System.out.println("Unable to return the book.");
                    }
                    break;
                }
                case 6:
                {
                     List<Book> allBooks = library.listAllBooks();
                     if(allBooks.isEmpty()){
                         System.out.println("No books found in the library.");

                     } else {
                         for(Book book:allBooks){
                             System.out.println(book);
                         }
                     }

                    break;
                }

                case 7:
                {
                    List<Book> availableBooks = library.listAllAvailable();
                    if(availableBooks.isEmpty()){
                        System.out.println("No available book found in the library.");

                    } else {
                        for(Book availableBook:availableBooks){
                            System.out.println(availableBook);
                        }
                    }

                    break;
                }
                case 8:
                {
                    List<Book> borrowedBooks = library.listAllBorrowed();
                    if(borrowedBooks.isEmpty()){
                        System.out.println("No borrowed book found in the library.");

                    } else {
                        for(Book borrowedBook:borrowedBooks){
                            System.out.println(borrowedBook);
                        }
                    }

                    break;
                }

                case 9:
                    System.out.println("Thank you for using the Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
