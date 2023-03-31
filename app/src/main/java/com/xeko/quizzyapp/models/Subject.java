package com.xeko.quizzyapp.models;

public class Subject {
    private String name;
    private String difficulty;
    private String image;
    private String id;

    public Subject() {}

    public Subject(String name, String difficulty, String image, String id) {
        this.name = name;
        this.difficulty = difficulty;
        this.image = image;
        this.id = id;
    }

    public Subject(Subject subject, String id) {
        this(subject.getName(), subject.getDifficulty(), subject.getImage(), id);
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
