package com.example.budgetin.entity;

import java.util.Date;

public class User {

    private String username;
    private String user_date;
    private float total_income;
    private float total_expense;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_date() {
        return user_date;
    }

    public void setUser_date(String user_date) {
        this.user_date = user_date;
    }

    public float getTotal_income() {
        return total_income;
    }

    public void setTotal_income(float total_income) {
        this.total_income = total_income;
    }

    public float getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(float total_expense) {
        this.total_expense = total_expense;
    }

    public User(String username, String user_date, float total_income, float total_expense) {
        this.username = username;
        this.user_date = user_date;
        this.total_income = total_income;
        this.total_expense = total_expense;
    }
}
