package com.example.LifestyleApp.Weather;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao

//give function signatures for executing SQL queries.
public interface WeatherDao {

    //The interface is implemented by Room! We don’t need to define the functions inside the DAO.

    //annotate each function with the appropriate SQL statement.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherTable weatherTable);

    @Query("DELETE FROM weather_table")
    void deleteAll();

    @Query("SELECT * from weather_table ORDER BY weatherdata ASC")
    LiveData<List<WeatherTable>> getAll();
}
