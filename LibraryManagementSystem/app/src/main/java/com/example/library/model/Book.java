package com.example.library.model;

public class Book {
    private String bookId, title, author, category, isbn;
    private int totalQuantity;

    public Book(String bookId, String title, String author, String category, String isbn, int totalQuantity) {
        this.bookId=bookId; this.title=title; this.author=author; this.category=category;
        this.isbn=isbn; this.totalQuantity=totalQuantity;
    }
    public String getBookId(){ return bookId; }
    public String getTitle(){ return title; }
    public String getAuthor(){ return author; }
    public String getCategory(){ return category; }
    public String getIsbn(){ return isbn; }
    public int getTotalQuantity(){ return totalQuantity; }
    public void setTitle(String v){ title=v; }
    public void setAuthor(String v){ author=v; }
    public void setCategory(String v){ category=v; }
    public void setIsbn(String v){ isbn=v; }
    public void setTotalQuantity(int v){ totalQuantity=v; }
}
