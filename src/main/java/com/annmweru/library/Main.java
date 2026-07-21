package com.annmweru.library;

import com.annmweru.library.exception.BookAlreadyAvailableException;
import com.annmweru.library.exception.BookAlreadyBorrowedException;
import com.annmweru.library.exception.BookAlreadyExistsException;
import com.annmweru.library.exception.BookNotFoundException;
import com.annmweru.library.model.Book;
import com.annmweru.library.service.LibraryService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);    public static void main(String[] args) {
        LibraryService library = new LibraryService();
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
            try{
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                scanner.nextLine();

            }

            switch (choice){
                case 1:{
                    String title = readUserInput("Enter the title: " , "Title cannot be empty.");
                    String author =  readUserInput("Enter the author: " , "Author cannot be empty.");
                    String isbn =readUserInput("Enter the Isbn: " , "Isbn cannot be empty.");

                    Book book = new Book(title ,author,isbn);
                    try{
                        library.addBook(book);
                        System.out.println("Book added successfully: " + book);
                    } catch(BookAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2:{
                    String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                    );
                    try {
                        Book myBook = library.searchBook(isbn);
                        System.out.println(myBook);
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3:
                {
                    String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                    );
                    try { library.deleteBook(isbn);
                        System.out.println("Book deleted successfully.");
                    } catch (BookNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4:
                {
                    String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                    );
                    try{
                       library.borrowBook(isbn);
                        System.out.println("Book borrowed successfully.");
                    } catch (BookAlreadyBorrowedException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5:
                {
                    String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                    );
                    try{
                        library.returnBook(isbn);
                        System.out.println("Book returned successfully.");
                    }catch (BookAlreadyAvailableException e){
                        System.out.println(e.getMessage());
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
    private static  String readUserInput(String prompt, String errorMessage){
        String input = "";
        while(input.isBlank()){
            System.out.print(prompt);
            input = scanner.nextLine();
            if(input.isBlank()){
                System.out.println(errorMessage);
            }
        }
        return input;

    }
}
