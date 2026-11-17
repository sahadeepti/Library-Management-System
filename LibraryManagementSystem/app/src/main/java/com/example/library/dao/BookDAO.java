package com.example.library.dao;
import com.example.library.model.Book;
import java.util.List;
public interface BookDAO {
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(String bookId);
    Book getBookById(String bookId);
    List<Book> getAllBooks();
    List<Book> searchBooks(String keyword);
    int getAvailableQuantity(String bookId);
}
