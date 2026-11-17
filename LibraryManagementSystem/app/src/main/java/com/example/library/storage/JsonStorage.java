package com.example.library.storage;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.library.model.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class JsonStorage {
    private final Context context;
    private final Gson gson = new Gson();
    public JsonStorage(Context context){ this.context=context.getApplicationContext(); }

    private <T> List<T> read(String file, Type type) {
        File f = new File(context.getFilesDir(), file);
        if (!f.exists()) return new ArrayList<>();
        try (Reader r = new FileReader(f)) {
            List<T> result = gson.fromJson(r, type);
            return result == null ? new ArrayList<>() : result;
        } catch (Exception e) { return new ArrayList<>(); }
    }
    private boolean write(String file, Object value) {
        File target = new File(context.getFilesDir(), file);
        File temp = new File(context.getFilesDir(), file + ".tmp");
        try (Writer w = new FileWriter(temp)) {
            gson.toJson(value, w);
        } catch (Exception e) { return false; }
        if (target.exists() && !target.delete()) return false;
        return temp.renameTo(target);
    }
    public List<Book> loadBooks(){ return read("books.json", new TypeToken<List<Book>>(){}.getType()); }
    public boolean saveBooks(List<Book> v){ return write("books.json", v); }
    public List<Student> loadStudents(){ return read("students.json", new TypeToken<List<Student>>(){}.getType()); }
    public boolean saveStudents(List<Student> v){ return write("students.json", v); }
    public List<IssueRecord> loadIssues(){ return read("issued_books.json", new TypeToken<List<IssueRecord>>(){}.getType()); }
    public boolean saveIssues(List<IssueRecord> v){ return write("issued_books.json", v); }
}
