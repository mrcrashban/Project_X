package com.example.project_x.BD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Transactions.class, Categories.class, Accounts.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract MainDao MainDao();
    public abstract CategoriesDao CategoriesDao();
    public abstract AccountsDao AccountsDao();
}
