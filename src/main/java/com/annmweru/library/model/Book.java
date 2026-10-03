package com.annmweru.library.model;

import com.annmweru.library.enums.BookStatus;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private BookStatus status = BookStatus.AVAILABLE;

     public Book (String title,String author,String isbn){
         this.title = title;
         this.author = author;
         this.isbn = isbn;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getIsbn(){ return this.isbn;}
    public BookStatus getStatus(){ return status;}
    public String toCsv() {
        return title + "," + author + "," + isbn + "," + status;
    };
    public static Book fromCsv(String line) {
        String[] parts = line.split(",");

        String title = parts[0];
        String author = parts[1];
        String isbn = parts[2];
        BookStatus status = BookStatus.valueOf(parts[3]);

        Book book = new Book(title, author, isbn);
        book.setStatus(status);

        return book;
    };


        public void setStatus (BookStatus status){
             this.status = status;

    }

    @Override
    public String toString(){
        return
                "Title  : " + title + "\n" +
                "Author : " + author + "\n" +
                "ISBN   : " + isbn + "\n" +
                "Status : " + status + "\n";

    }
}
