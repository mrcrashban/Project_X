package com.example.project_x.BD;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CategoriesDao {
    @Query("SELECT * FROM Categories")
    Flowable<List<Categories>> getCategories();

    @Query("SELECT category_name FROM Categories")
    List<String> getCategoriesName();
}
