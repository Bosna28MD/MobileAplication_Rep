package com.example.finalproject_android1;

public class UserData {
    String email,username,date;

    public UserData(String email, String username, String date) {
        this.email = email;
        this.username = username;
        this.date = date;
    }

    public UserData(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
