package com.xeko.quizzyapp.models;

import java.util.Date;

public class Result {
    private String subject;
    private String earned;
    private Date date;
    private String email;

    public Result(String subject, String earned, Date date) {
        this.subject = subject;
        this.earned = earned;
        this.date = date;
    }
    public Result(String subject, String earned, Date date, String email) {
        this(subject, earned, date);
        this.email = email;
    }
    public Result() {}

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
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
