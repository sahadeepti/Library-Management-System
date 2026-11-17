package com.example.library.dao.impl;
import android.content.Context;
import com.example.library.dao.BookDAO;
import com.example.library.model.*;
import com.example.library.storage.JsonStorage;
import java.util.*;

public class BookDAOImpl implements BookDAO {
    private final JsonStorage storage;
    public BookDAOImpl(Context c){ storage=new JsonStorage(c); }
    public boolean addBook(Book b){
        List<Book> list=getAllBooks();
        if(getBookById(b.getBookId())!=null || b.getTotalQuantity()<1) return false;
        list.add(b); return storage.saveBooks(list);
    }
    public boolean updateBook(Book b){
        List<Book> list=getAllBooks();
        for(int i=0;i<list.size();i++) if(list.get(i).getBookId().equals(b.getBookId())){
            int issued=list.get(i).getTotalQuantity()-getAvailableQuantity(b.getBookId());
            if(b.getTotalQuantity()<issued) return false;
            list.set(i,b); return storage.saveBooks(list);
        }
        return false;
    }
    public boolean deleteBook(String id){
        if(getAvailableQuantity(id) < (getBookById(id)==null?0:getBookById(id).getTotalQuantity())) return false;
        List<Book> list=getAllBooks(); boolean changed=list.removeIf(b->b.getBookId().equals(id));
        return changed && storage.saveBooks(list);
    }
    public Book getBookById(String id){ for(Book b:getAllBooks()) if(b.getBookId().equalsIgnoreCase(id)) return b; return null; }
    public List<Book> getAllBooks(){ return storage.loadBooks(); }
    public List<Book> searchBooks(String q){
        List<Book> out=new ArrayList<>(); String s=q.toLowerCase(Locale.ROOT);
        for(Book b:getAllBooks()) if(b.getBookId().toLowerCase(Locale.ROOT).contains(s)||b.getTitle().toLowerCase(Locale.ROOT).contains(s)||b.getAuthor().toLowerCase(Locale.ROOT).contains(s)) out.add(b);
        return out;
    }
    public int getAvailableQuantity(String id){
        Book b=getBookById(id); if(b==null)return 0;
        int issued=0; for(IssueRecord r:storage.loadIssues()) if(r.getBookId().equals(id)&&"ISSUED".equals(r.getStatus())) issued++;
        return Math.max(0,b.getTotalQuantity()-issued);
    }
}
