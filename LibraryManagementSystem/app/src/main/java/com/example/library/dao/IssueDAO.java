package com.example.library.dao;
import com.example.library.model.IssueRecord;
import java.util.List;
public interface IssueDAO {
    boolean issueBook(String studentId, String bookId, String issueDate, String dueDate);
    boolean returnBook(String issueId, String returnDate);
    List<IssueRecord> getAllIssues();
    List<IssueRecord> getActiveIssues();
}
