package com.xeko.quizzyapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
    private String question;
    private String type;
    private String correct;
    private Integer timer;
    private List<String> options;
    private DocumentReference subjectRef;

    public Quiz() {}

    public Quiz(String question, String type, String correct, Integer timer, List<String> options, DocumentReference subjectRef) {
        this.question = question;
        this.type = type;
        this.correct = correct;
        this.timer = timer;
        this.options = options;
        this.subjectRef = subjectRef;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public DocumentReference getSubjectRef() {
        return subjectRef;
    }

    public void setSubjectRef(DocumentReference subjectRef) {
        this.subjectRef = subjectRef;
    }

}
