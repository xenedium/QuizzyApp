package com.xeko.quizzyapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentReference;

public class Quiz implements Parcelable {
    private String question;
    private String type;
    private String correct;
    private Integer timer;
    private String[] options;
    private DocumentReference subjectRef;

    public Quiz() {}

    public Quiz(String question, String type, String correct, Integer timer, String[] options, DocumentReference subjectRef) {
        this.question = question;
        this.type = type;
        this.correct = correct;
        this.timer = timer;
        this.options = options;
        this.subjectRef = subjectRef;
    }

    protected Quiz(Parcel in) {
        question = in.readString();
        type = in.readString();
        correct = in.readString();
        if (in.readByte() == 0) {
            timer = null;
        } else {
            timer = in.readInt();
        }
        options = in.createStringArray();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

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

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public DocumentReference getSubjectRef() {
        return subjectRef;
    }

    public void setSubjectRef(DocumentReference subjectRef) {
        this.subjectRef = subjectRef;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(question);
        parcel.writeString(correct);
        parcel.writeInt(timer);
        parcel.writeStringArray(options);

    }
}
