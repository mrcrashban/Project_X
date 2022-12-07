package com.example.project_x.BD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Transactions implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String account;

    @ColumnInfo
    private String category;

    @ColumnInfo
    private String type;

    @ColumnInfo
    private String sum;

    @ColumnInfo
    private String date;

    @ColumnInfo
    private String comment;

    public Transactions(String account, String category, String type, String sum, String date, String comment) {
        this.account = account;
        this.category = category;
        this.type = type;
        this.sum = sum;
        this.date = date;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) { this.account = account; }

    public String getCategory() { return category; }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
