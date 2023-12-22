package com.example.project_lab_db1;

public class Student {
    private int id,grade;
    private String name,email;


    public Student(int id, int grade, String name, String email) {
        this.id = id;
        this.grade = grade;
        this.name = name;
        this.email = email;
    }

    public Student(String name, String email, int grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
