package com.example.library;
import android.app.Activity;import android.view.View;import android.widget.LinearLayout;
public class ViewDivider { public static void add(Activity a,LinearLayout l){View v=new View(a);v.setBackgroundColor(0xFFDDDDDD);l.addView(v,new LinearLayout.LayoutParams(-1,1));} }
