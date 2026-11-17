package com.example.library;
import android.app.Activity;import android.os.Bundle;import android.widget.*;import java.text.SimpleDateFormat;import java.util.*;
import com.example.library.dao.impl.*;import com.example.library.model.*;import com.example.library.ui.UI;
public class IssueActivity extends Activity {
 public void onCreate(Bundle b){super.onCreate(b);IssueDAOImpl dao=new IssueDAOImpl(this);BookDAOImpl books=new BookDAOImpl(this);StudentDAOImpl students=new StudentDAOImpl(this);
  LinearLayout l=UI.page(this,"Issue / Return Books");
  UI.add(l,UI.label(this,"Issue a book"));
  EditText sid=UI.input(this,"Student ID"),bid=UI.input(this,"Book ID"),days=UI.input(this,"Loan period in days (e.g. 14)");days.setText("14");days.setInputType(2);
  UI.add(l,sid);UI.add(l,bid);UI.add(l,days);
  Button issue=UI.button(this,"Issue book");UI.add(l,issue);
  issue.setOnClickListener(v->{try{
   int d=Integer.parseInt(days.getText().toString());if(d<1)throw new Exception();
   Calendar c=Calendar.getInstance();String start=fmt(c.getTime());c.add(Calendar.DAY_OF_YEAR,d);String due=fmt(c.getTime());
   boolean ok=dao.issueBook(sid.getText().toString().trim(),bid.getText().toString().trim(),start,due);
   UI.msg(this,ok?"Book issued. Due date: "+due:"Issue failed: check student, book and availability.");if(ok){sid.setText("");bid.setText("");}
  }catch(Exception e){UI.msg(this,"Enter a valid loan period.");}});
  UI.add(l,UI.label(this,"Currently issued books — enter Issue ID to return"));
  EditText issueId=UI.input(this,"Issue ID");UI.add(l,issueId);
  Button ret=UI.button(this,"Mark as returned");UI.add(l,ret);
  ret.setOnClickListener(v->{boolean ok=dao.returnBook(issueId.getText().toString().trim(),fmt(new Date()));UI.msg(this,ok?"Book returned":"Issue ID not found or already returned.");});
  for(IssueRecord r:dao.getActiveIssues()){
   Book bk=books.getBookById(r.getBookId());Student st=students.getStudentById(r.getStudentId());
   UI.add(l,UI.label(this,r.getIssueId()+" | "+(bk==null?r.getBookId():bk.getTitle())+"\nStudent: "+(st==null?r.getStudentId():st.getName())+" | Issued: "+r.getIssueDate()+" | Due: "+r.getDueDate()));
  }
  ScrollView sc=new ScrollView(this);sc.addView(l);setContentView(sc);
 }
 private String fmt(Date d){return new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(d);}
}
