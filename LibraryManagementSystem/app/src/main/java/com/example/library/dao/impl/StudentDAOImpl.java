package com.example.library.dao.impl;
import android.content.Context;
import com.example.library.dao.StudentDAO;
import com.example.library.model.Student;
import com.example.library.storage.JsonStorage;
import java.util.*;
public class StudentDAOImpl implements StudentDAO {
    private final JsonStorage storage;
    public StudentDAOImpl(Context c){ storage=new JsonStorage(c); }
    public boolean addStudent(Student s){
        List<Student> list=getAllStudents();
        if(getStudentById(s.getStudentId())!=null)return false;
        list.add(s); return storage.saveStudents(list);
    }
    public Student getStudentById(String id){ for(Student s:getAllStudents()) if(s.getStudentId().equalsIgnoreCase(id))return s; return null; }
    public List<Student> getAllStudents(){ return storage.loadStudents(); }
}
