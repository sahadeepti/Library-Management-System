package com.example.library;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import com.example.library.dao.impl.BookDAOImpl;
import com.example.library.model.Book;
import com.example.library.ui.UI;
public class BooksActivity extends Activity {
 private BookDAOImpl dao; private LinearLayout list;
 public void onCreate(Bundle b){super.onCreate(b);dao=new BookDAOImpl(this);
  LinearLayout root=UI.page(this,"Books");
  EditText id=UI.input(this,"Book ID"), title=UI.input(this,"Book title"), author=UI.input(this,"Author"), category=UI.input(this,"Category"), isbn=UI.input(this,"ISBN"), qty=UI.input(this,"Total quantity");
  qty.setInputType(2);
  UI.add(root,UI.label(this,"Register a new book")); UI.add(root,id);UI.add(root,title);UI.add(root,author);UI.add(root,category);UI.add(root,isbn);UI.add(root,qty);
  Button save=UI.button(this,"Save book");UI.add(root,save);
  save.setOnClickListener(v->{try{
   boolean ok=dao.addBook(new Book(id.getText().toString().trim(),title.getText().toString().trim(),author.getText().toString().trim(),category.getText().toString().trim(),isbn.getText().toString().trim(),Integer.parseInt(qty.getText().toString())));
   UI.msg(this,ok?"Book saved":"Could not save. Check fields and duplicate Book ID.");if(ok){id.setText("");title.setText("");author.setText("");category.setText("");isbn.setText("");qty.setText("");refresh();}
  }catch(Exception e){UI.msg(this,"Enter valid book details and quantity.");}});
  EditText search=UI.input(this,"Search by ID, title or author");UI.add(root,search);
  Button find=UI.button(this,"Search");UI.add(root,find);find.setOnClickListener(v->refresh(search.getText().toString()));
  list=new LinearLayout(this);list.setOrientation(1);UI.add(root,UI.label(this,"Registered books"));UI.add(root,list);refresh();
  ScrollView sc=new ScrollView(this);sc.addView(root);setContentView(sc);
 }
 private void refresh(){refresh("");}
 private void refresh(String q){list.removeAllViews();for(Book b:dao.searchBooks(q)){
  TextView t=UI.label(this,b.getBookId()+" — "+b.getTitle()+"\nAuthor: "+b.getAuthor()+" | Category: "+b.getCategory()+"\nTotal: "+b.getTotalQuantity()+" | Available: "+dao.getAvailableQuantity(b.getBookId()));
  list.addView(t); ViewDivider.add(this,list);
 }}
}
