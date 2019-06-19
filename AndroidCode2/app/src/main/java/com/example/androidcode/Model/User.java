package com.example.androidcode.Model;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private double Exp;

    public double getExp() {
        return Exp;
    }

    public void setExp(double exp) {
        Exp = exp;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
