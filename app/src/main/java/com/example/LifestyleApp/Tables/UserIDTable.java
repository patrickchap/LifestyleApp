package com.example.LifestyleApp.Tables;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//ENTITY: A class containing data.
@Entity(tableName = "users")
public class UserIDTable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "index")
    private int index;

    @NonNull
    @ColumnInfo(name = "userID")
    private String userID;

    public UserIDTable(@NonNull int index, @NonNull String userID){
        this.index = index;
        this.userID = userID;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public void setUserId(String userID){
        this.userID = userID;
    }

    public int getIndex(){ return index; }

    public String getUserID(){
        return userID;
    }

}
