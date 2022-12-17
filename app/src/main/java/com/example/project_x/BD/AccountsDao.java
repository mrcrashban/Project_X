package com.example.project_x.BD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface AccountsDao {
    @Query("SELECT * FROM Accounts")
    Flowable<List<Accounts>> getAccounts();

    @Query("SELECT * FROM Accounts where id = Accounts.id")
    Accounts getAccount();

    @Query("SELECT account_name FROM Accounts")
    List<String> getAccountsName();

    @Delete
    void delete(Accounts a);
}
