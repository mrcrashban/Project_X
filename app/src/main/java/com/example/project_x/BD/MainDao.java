package com.example.project_x.BD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MainDao {
    @Query("SELECT * FROM Transactions")
    Flowable<List<Transactions>> getAll();

    @Insert
    void insert(Transactions t);

    @Insert
    void insert_cat(Categories c);

    @Insert
    void insert_acc(Accounts a);

    @Delete
    void delete(Transactions t);

    @Update
    void update(Transactions t);
}
