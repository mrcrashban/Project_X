package com.example.project_x.BD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categories
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String category_name;

    public Categories(String category_name){
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
