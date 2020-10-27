package com.example.LifestyleApp.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.LifestyleApp.Tables.UserIDTable;

import java.util.List;

@Dao
//give function signatures for executing SQL queries.
public interface UserIDDao {

    //The interface is implemented by Room! We don’t need to define the functions inside the DAO.

    //annotate each function with the appropriate SQL statement.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserIDTable userIDTable);

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * from users")
    LiveData<List<UserIDTable>> getAll();

}
