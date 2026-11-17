package com.example.library;
import android.app.Activity;import android.os.Bundle;import android.widget.*;
import com.example.library.dao.impl.*;import com.example.library.model.*;import com.example.library.ui.UI;
public class ReportsActivity extends Activity {
 public void onCreate(Bundle b){super.onCreate(b);BookDAOImpl books=new BookDAOImpl(this);StudentDAOImpl students=new StudentDAOImpl(this);IssueDAOImpl issues=new IssueDAOImpl(this);
  LinearLayout l=UI.page(this,"Library Reports");
  int total=0,available=0;for(Book x:books.getAllBooks()){total+=x.getTotalQuantity();available+=books.getAvailableQuantity(x.getBookId());}
  UI.add(l,UI.label(this,"Total registered titles: "+books.getAllBooks().size()));
  UI.add(l,UI.label(this,"Total physical copies: "+total));
  UI.add(l,UI.label(this,"Available copies: "+available));
  UI.add(l,UI.label(this,"Issued copies: "+issues.getActiveIssues().size()));
  UI.add(l,UI.label(this,"Registered students: "+students.getAllStudents().size()));
  UI.add(l,UI.label(this,"Complete issue / return history"));
  for(IssueRecord r:issues.getAllIssues()){
   Book bk=books.getBookById(r.getBookId());Student st=students.getStudentById(r.getStudentId());
   UI.add(l,UI.label(this,r.getIssueId()+" | "+(bk==null?r.getBookId():bk.getTitle())+"\nStudent: "+(st==null?r.getStudentId():st.getName())+"\nIssue: "+r.getIssueDate()+" | Due: "+r.getDueDate()+" | Return: "+(r.getReturnDate().isEmpty()?"Not returned":r.getReturnDate())+" | "+r.getStatus()));
  }
  ScrollView sc=new ScrollView(this);sc.addView(l);setContentView(sc);
 }
}
