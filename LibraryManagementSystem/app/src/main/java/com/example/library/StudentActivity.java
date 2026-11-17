package com.example.library;
import android.app.Activity;import android.os.Bundle;import android.widget.*;
import com.example.library.dao.impl.StudentDAOImpl;import com.example.library.model.Student;import com.example.library.ui.UI;
public class StudentActivity extends Activity {
 public void onCreate(Bundle b){super.onCreate(b);StudentDAOImpl dao=new StudentDAOImpl(this);
  LinearLayout l=UI.page(this,"Student Registration");
  EditText id=UI.input(this,"Student ID"),name=UI.input(this,"Student name"),course=UI.input(this,"Course / Class"),phone=UI.input(this,"Phone (optional)");
  UI.add(l,id);UI.add(l,name);UI.add(l,course);UI.add(l,phone);
  Button save=UI.button(this,"Register student");UI.add(l,save);
  save.setOnClickListener(v->{if(id.getText().length()==0||name.getText().length()==0||course.getText().length()==0){UI.msg(this,"Enter student ID, name and course.");return;}
   boolean ok=dao.addStudent(new Student(id.getText().toString().trim(),name.getText().toString().trim(),course.getText().toString().trim(),phone.getText().toString().trim()));
   UI.msg(this,ok?"Student registered":"Student ID already exists.");});
  UI.add(l,UI.label(this,"Registered students"));
  for(Student s:dao.getAllStudents())UI.add(l,UI.label(this,s.getStudentId()+" — "+s.getName()+" ("+s.getCourse()+")"));
  ScrollView sc=new ScrollView(this);sc.addView(l);setContentView(sc);
 }
}
