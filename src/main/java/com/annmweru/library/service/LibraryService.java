package com.annmweru.library.service;

import com.annmweru.library.database.BookRepository;
import com.annmweru.library.enums.BookStatus;
import com.annmweru.library.exception.BookAlreadyAvailableException;
import com.annmweru.library.exception.BookAlreadyBorrowedException;
import com.annmweru.library.exception.BookAlreadyExistsException;
import com.annmweru.library.exception.BookNotFoundException;
import com.annmweru.library.model.Book;
import java.util.List;

public class LibraryService {
    private final BookRepository repository;

    public LibraryService(BookRepository repository) {
        this.repository = repository;
    }
    public boolean addBook(Book newBook) {
        String isbn = newBook.getIsbn();

        Book existingBook = repository.findBookByIsbn(isbn);

        if (existingBook != null) {
            throw new BookAlreadyExistsException(
                    "A book with ISBN " + isbn + " already exists."
            );
        }
        return repository.insertBook(newBook);

    }
    public Book searchBook(String isbn) {

        Book book = repository.findBookByIsbn(isbn);

        if (book == null) {
            throw new BookNotFoundException(
                    "No book found with ISBN: " + isbn
            );
        }

        return book;
    }

    public boolean deleteBook(String isbn) {

        Book book = repository.findBookByIsbn(isbn);

        if (book == null) {
            throw new BookNotFoundException(
                    "No book found with ISBN " + isbn
            );
        }

        return repository.deleteBook(isbn);
    }

    public boolean borrowBook(String isbn) {
        Book book = repository.findBookByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException(
                    "No book found with ISBN " + isbn
            );
        }
        if (book.getStatus() == BookStatus.BORROWED) {
            throw new BookAlreadyBorrowedException(
                    "The book is already borrowed."
            );
        }
        return repository.borrowBook(isbn);
    }

    public boolean returnBook(String isbn) {
        Book book = repository.findBookByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException(
                    "No book found with ISBN " + isbn
            );
        }
        if (book.getStatus() == BookStatus.AVAILABLE) {
            throw new BookAlreadyAvailableException("The book is already available.");
        }
        return repository.returnBook(isbn);

    }

    public List<Book> listAllBooks() {
        return repository.findAllBooks();
    }

    public List<Book> listAvailableBooks() {
        return repository.findAvailableBooks();
    }

    public List<Book> listAllBorrowed() {
        return repository.findBorrowedBooks();

    }
}