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
    @ColumnInfo(name = "userInfoPage")
    private String userInfoPage;

    @NonNull
    @ColumnInfo(name = "jsonData")
    private String userInfoJson;

    public UserInfoTable(@NonNull String userInfoPage, @NonNull String userInfoJson){
        this.userInfoPage = userInfoPage;
        this.userInfoJson = userInfoJson;
    }

    public void setUserInfoPage(String userInfoPage){
        this.userInfoPage = userInfoPage;
    }

    public void setUserInfoJson(String userInfoJson){
        this.userInfoJson = userInfoJson;
    }

    public String getUserInfoPage(){ return userInfoPage; }

    public String getUserInfoJson(){
        return userInfoJson;
    }

}
