package com.example.library.dao.impl;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.library.dao.AdminDAO;
public class AdminDAOImpl implements AdminDAO {
    private final SharedPreferences prefs;
    public AdminDAOImpl(Context c){ prefs=c.getSharedPreferences("admin_settings",Context.MODE_PRIVATE); }
    public boolean login(String u,String p){
        // Demo credentials; change before real deployment.
        return "admin".equals(u.trim()) && "admin123".equals(p);
    }
}
