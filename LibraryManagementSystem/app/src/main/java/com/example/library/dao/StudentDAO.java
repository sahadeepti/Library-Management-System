package com.example.library.dao;
import com.example.library.model.Student;
import java.util.List;
public interface StudentDAO {
    boolean addStudent(Student student);
    Student getStudentById(String studentId);
    List<Student> getAllStudents();
}
