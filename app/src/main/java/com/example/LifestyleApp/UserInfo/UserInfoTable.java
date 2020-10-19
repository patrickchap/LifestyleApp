package com.example.LifestyleApp.UserInfo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//ENTITY: A class containing data.
@Entity(tableName = "userInfo_table")

//This is the data that gets stored in your database.
public class UserInfoTable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userInfoLevel")
    private String userInfoLevel;

    @NonNull
    @ColumnInfo(name = "jsonData")
    private String userInfoJson;

    public UserInfoTable(@NonNull String userInfoLevel, @NonNull String userInfoJson){
        this.userInfoLevel = userInfoLevel;
        this.userInfoJson = userInfoJson;
    }

    public void setUserInfoLevel(String userInfoLevel){
        this.userInfoLevel = userInfoLevel;
    }

    public void setUserInfoJson(String userInfoJson){
        this.userInfoJson = userInfoJson;
    }

    public String getUserInfoLevel(){ return userInfoLevel; }

    public String getUserInfoJson(){
        return userInfoJson;
    }

}
