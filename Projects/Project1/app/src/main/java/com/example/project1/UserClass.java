package com.example.project1;

public class UserClass {
    private  Integer Id;
    private String name;
    private String email;
    private String pwd;

    public UserClass(Integer id, String name, String email, String pwd) {
        Id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
