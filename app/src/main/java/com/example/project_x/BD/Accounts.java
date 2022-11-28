package com.example.project_x.BD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Accounts {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String account_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Accounts(String account_name){
        this.account_name = account_name;
    }
}
