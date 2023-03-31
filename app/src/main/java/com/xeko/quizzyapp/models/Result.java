package com.xeko.quizzyapp.models;

import com.google.firebase.firestore.DocumentReference;

import java.util.Date;

public class Result {
    private DocumentReference subjectRef;
    private String earned;
    private Date date;
    private String email;

    public Result(DocumentReference subjectRef, String earned, Date date) {
        this.subjectRef = subjectRef;
        this.earned = earned;
        this.date = date;
    }
    public Result(DocumentReference subjectRef, String earned, Date date, String email) {
        this(subjectRef, earned, date);
        this.email = email;
    }
    public Result() {}

    public DocumentReference getSubject() {
        return subjectRef;
    }
    public void setSubject(DocumentReference subjectRef) {
        this.subjectRef = subjectRef;
    }
    public String getEarned() {
        return earned;
    }
    public void setEarned(String earned) {
        this.earned = earned;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
