package com.annmweru.library;

import com.annmweru.library.database.BookRepository;
import com.annmweru.library.database.DatabaseConnection;
import com.annmweru.library.exception.BookAlreadyAvailableException;
import com.annmweru.library.exception.BookAlreadyBorrowedException;
import com.annmweru.library.exception.BookAlreadyExistsException;
import com.annmweru.library.exception.BookNotFoundException;
import com.annmweru.library.model.Book;
import com.annmweru.library.service.LibraryService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ;
            BookRepository repository = new BookRepository(connection);
            LibraryService library = new LibraryService(repository);
            System.out.println("=============================");
            System.out.println("Library Management System");
            System.out.println("=============================");

            int choice = 0;

            while (choice != 9) {
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
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 9.");
                    scanner.nextLine();

                }

                switch (choice) {

                    case 1: {
                        String title = readUserInput("Enter the title: ", "Title cannot be empty.");
                        String author = readUserInput("Enter the author: ", "Author cannot be empty.");
                        String isbn = readUserInput("Enter the Isbn: ", "Isbn cannot be empty.");
                        String status = "AVAILABLE";

                        Book book = new Book(title, author, isbn);

                        try {
                            boolean inserted = library.addBook(book);

                            if (inserted) {
                                System.out.println("Book added successfully!");
                            }

                        } catch (BookAlreadyExistsException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                    case 2: {
                        String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                        );
                        try {
                            Book book = library.searchBook(isbn);

                            System.out.println("Book found:");
                            System.out.println(book);

                        } catch (BookNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 3: {
                        String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                        );

                        try {
                            boolean deleted = library.deleteBook(isbn);

                            if (deleted) {
                                System.out.println("Book deleted successfully!");
                            }

                        } catch (BookNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                        );
                        try {
                            boolean borrowed = library.borrowBook(isbn);

                            if (borrowed) {
                                System.out.println("Book Borrowed successfully!");
                            }
                        } catch (BookNotFoundException | BookAlreadyBorrowedException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    }
                    case 5: {
                        String isbn = readUserInput("Enter the ISBN: ", "ISBN cannot be empty."
                        );
                        try {
                            boolean returned = library.returnBook(isbn);

                            if (returned) {
                                System.out.println("Book returned successfully!");
                            }

                        } catch (BookNotFoundException | BookAlreadyAvailableException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 6:
                        List<Book> books = library.listAllBooks();

                        for (Book book : books) {
                            System.out.println(book);
                        }
                        break;
                    case 7: {
                        List<Book> availableBooks = library.listAvailableBooks();
                        for (Book book : availableBooks) {
                            System.out.println(book);
                        }
                        break;
                    }
                    case 8: {
                        List<Book> borrowedBooks = library.listAllBorrowed();
                        for (Book book : borrowedBooks) {
                            System.out.println(book);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readUserInput(String prompt, String errorMessage) {
        String input = "";
        while (input.isBlank()) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println(errorMessage);
            }
        }
        return input;

    }
}
