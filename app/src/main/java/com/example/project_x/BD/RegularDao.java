package com.example.project_x.BD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RegularDao {
    @Query("SELECT * FROM RegularTransactions")
    Flowable<List<RegularTransactions>> getRegularTransactions();

    @Delete
    void delete(RegularTransactions r);
}
