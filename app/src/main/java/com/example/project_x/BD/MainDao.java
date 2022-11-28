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
    @Query("SELECT account_name as account, category_name AS category, type, sum, date, comment FROM Transactions, Categories, Accounts WHERE Categories.id == Transactions.category")
    Flowable<List<Trans_Acc_Cat>> getAll();

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
