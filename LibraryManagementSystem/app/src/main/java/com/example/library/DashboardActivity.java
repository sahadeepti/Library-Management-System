package com.example.library;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.*;
import com.example.library.dao.impl.*;
import com.example.library.ui.UI;
public class DashboardActivity extends Activity {
 public void onCreate(Bundle b){super.onCreate(b);
  LinearLayout l=UI.page(this,"Admin Dashboard");
  BookDAOImpl books=new BookDAOImpl(this); IssueDAOImpl issues=new IssueDAOImpl(this); StudentDAOImpl students=new StudentDAOImpl(this);
  int total=0,available=0; for(com.example.library.model.Book x:books.getAllBooks()){total+=x.getTotalQuantity();available+=books.getAvailableQuantity(x.getBookId());}
  UI.add(l,UI.label(this,"Total book copies: "+total));
  UI.add(l,UI.label(this,"Available copies: "+available));
  UI.add(l,UI.label(this,"Currently issued: "+issues.getActiveIssues().size()));
  UI.add(l,UI.label(this,"Registered students: "+students.getAllStudents().size()));
  add(l,"Book registration / Book list",BooksActivity.class);
  add(l,"Student registration",StudentActivity.class);
  add(l,"Issue / Return books",IssueActivity.class);
  add(l,"Reports and borrowing history",ReportsActivity.class);
  Button logout=UI.button(this,"Logout"); UI.add(l,logout); logout.setOnClickListener(v->{startActivity(new Intent(this,LoginActivity.class));finish();});
  setContentView(l);
 }
 private void add(LinearLayout l,String s,Class<?> c){Button b=UI.button(this,s);UI.add(l,b);b.setOnClickListener(v->startActivity(new Intent(this,c)));}
}
