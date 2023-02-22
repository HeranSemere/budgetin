package com.example.budgetin.entity;

public class Item {

    private int id;
    private String username;
    private String itemCategory;
    private float amount;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Item(int id, String username, String itemCategory, float amount, String note) {
        this.id = id;
        this.username = username;
        this.itemCategory = itemCategory;
        this.amount = amount;
        this.note = note;
    }
}
