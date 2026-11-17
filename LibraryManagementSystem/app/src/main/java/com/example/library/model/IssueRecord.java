package com.example.library.model;

public class IssueRecord {
    private String issueId, studentId, bookId, issueDate, dueDate, returnDate, status;
    public IssueRecord(String issueId, String studentId, String bookId, String issueDate, String dueDate) {
        this.issueId=issueId; this.studentId=studentId; this.bookId=bookId;
        this.issueDate=issueDate; this.dueDate=dueDate; this.returnDate="";
        this.status="ISSUED";
    }
    public String getIssueId(){ return issueId; }
    public String getStudentId(){ return studentId; }
    public String getBookId(){ return bookId; }
    public String getIssueDate(){ return issueDate; }
    public String getDueDate(){ return dueDate; }
    public String getReturnDate(){ return returnDate; }
    public String getStatus(){ return status; }
    public void markReturned(String date){ returnDate=date; status="RETURNED"; }
}
