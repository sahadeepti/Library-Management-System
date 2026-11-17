package com.example.library.dao.impl;
import android.content.Context;
import com.example.library.dao.*;
import com.example.library.model.*;
import com.example.library.storage.JsonStorage;
import java.util.*;
public class IssueDAOImpl implements IssueDAO {
    private final JsonStorage storage; private final BookDAO books; private final StudentDAO students;
    public IssueDAOImpl(Context c){ storage=new JsonStorage(c); books=new BookDAOImpl(c); students=new StudentDAOImpl(c); }
    public boolean issueBook(String sid,String bid,String issue,String due){
        if(students.getStudentById(sid)==null || books.getBookById(bid)==null || books.getAvailableQuantity(bid)<1)return false;
        List<IssueRecord> list=getAllIssues();
        list.add(new IssueRecord("I"+System.currentTimeMillis(),sid,bid,issue,due));
        return storage.saveIssues(list);
    }
    public boolean returnBook(String id,String date){
        List<IssueRecord> list=getAllIssues();
        for(IssueRecord r:list) if(r.getIssueId().equals(id)&&"ISSUED".equals(r.getStatus())){
            r.markReturned(date); return storage.saveIssues(list);
        }
        return false;
    }
    public List<IssueRecord> getAllIssues(){ return storage.loadIssues(); }
    public List<IssueRecord> getActiveIssues(){ List<IssueRecord> out=new ArrayList<>(); for(IssueRecord r:getAllIssues())if("ISSUED".equals(r.getStatus()))out.add(r); return out; }
}
