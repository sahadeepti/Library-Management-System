package com.example.library.model;

public class Student {
    private String studentId, name, course, phone;
    public Student(String studentId, String name, String course, String phone) {
        this.studentId=studentId; this.name=name; this.course=course; this.phone=phone;
    }
    public String getStudentId(){ return studentId; }
    public String getName(){ return name; }
    public String getCourse(){ return course; }
    public String getPhone(){ return phone; }
}
