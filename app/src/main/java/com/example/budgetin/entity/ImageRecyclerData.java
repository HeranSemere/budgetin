package com.example.budgetin.entity;

public class ImageRecyclerData {

    private String label;
    private String amount;
    private int imgid;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public ImageRecyclerData(String label, int imgid, String amount) {
        this.label = label;
        this.imgid = imgid;
        this.amount = amount;
    }
}
