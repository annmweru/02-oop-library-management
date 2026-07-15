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

    public void setStatus (BookStatus status){
             this.status = status;

    }

    @Override
    public String toString(){
       return  "Book" + " " + "title " + title + '\'' + ", author='" + author + '\'' + ", isbn='" + isbn + '\'' ;
    };

}
