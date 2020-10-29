package com.example.LifestyleApp.UserInfo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.LifestyleApp.Tables.UserInfoTable;

import java.util.List;

@Dao
//give function signatures for executing SQL queries.
public interface UserInfoDao {

    //The interface is implemented by Room! We don’t need to define the functions inside the DAO.

    //annotate each function with the appropriate SQL statement.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserInfoTable userInfoTable);

    @Query("DELETE FROM userInfo_table")
    void deleteAll();

    @Query("SELECT * from userInfo_table")
    LiveData<List<UserInfoTable>> getAll();

    @Query("SELECT * FROM userInfo_table WHERE userName LIKE :search")
    public List<UserInfoTable> findByUserName(String search);

//    @Query("SELECT goal from userInfo_table")
//    LiveData<List<UserInfoTable>> getGoal();
//
//    @Query("SELECT goalSet from userInfo_table")
//    LiveData<List<UserInfoTable>> getGoalSet();

}
