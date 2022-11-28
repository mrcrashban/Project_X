package com.example.project_x.BD;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface AccountsDao {
    @Query("SELECT * FROM Accounts")
    Flowable<List<Accounts>> getAccounts();
}
