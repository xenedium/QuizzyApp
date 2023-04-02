package com.xeko.quizzyapp.models;

import android.content.Intent;
import android.view.View;

import com.xeko.quizzyapp.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class GlobalState {
    private static GlobalState instance;
    private int currentQuestion;
    private int totalQuestions;
    private final List<Intent> intents;
    private String subjectId;
    private String subjectName;
    private int score;

    private GlobalState() {
        currentQuestion = 0;
        totalQuestions = 0;
        intents = new ArrayList<>();
    }

    public static GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }
        return instance;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public GlobalState setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
        return this;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public GlobalState setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        return this;
    }

    public void addIntent(Intent intent) {
        intents.add(intent);
    }

    public Intent getIntent(int index) {
        return intents.get(index);
    }

    public void clearIntents() {
        intents.clear();
    }

    public void nextQuestion(View view) {
        currentQuestion++;
        if (currentQuestion <= totalQuestions) {
            view.getContext().startActivity(intents.get(currentQuestion));
        }
    }

    public void reset() {
        currentQuestion = 0;
        totalQuestions = 0;
        subjectId = null;
        subjectName = null;
        score = 0;
        intents.clear();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        score++;
    }
}
