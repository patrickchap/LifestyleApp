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
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "userInfoData")
    private String userInfoJson;

//    @NonNull
//    @ColumnInfo(name = "height")
//    private int userHeight;
//
//    @NonNull
//    @ColumnInfo(name = "weight")
//    private float userWeight;
//
//    @NonNull
//    @ColumnInfo(name = "bmi")
//    private double userBMI;
//
//    @NonNull
//    @ColumnInfo(name = "gender")
//    private String userGender;
//
//    @NonNull
//    @ColumnInfo(name = "dob")
//    private Long userDOB;

    public UserInfoTable(@NonNull String email, @NonNull String userInfoJson){
        this.email = email;
        this.userInfoJson = userInfoJson;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setUserInfoJson(String userInfoJson){
        this.userInfoJson = userInfoJson;
    }

    public String getEmail(){ return email; }

    public String getUserInfoJson(){
        return userInfoJson;
    }

//    public UserInfo1Table(@NonNull String email,
//                          @NonNull int userHeight,
//                          @NonNull float userWeight,
//                          @NonNull double userBMI,
//                          @NonNull String userGender,
//                          @NonNull Long userDOB){
//        this.email = email;
//        this.userHeight = userHeight;
//        this.userWeight = userWeight;
//        this.userBMI = userBMI;
//        this.userGender = userGender;
//        this.userDOB = userDOB;
//    }
//
//    public void setEmail(String email){
//        this.email = email;
//    }
//
//    public void setUserHeight(int userHeight){
//        this.userHeight = userHeight;
//    }
//
//    public void setUserWeight(float userWeight){
//        this.userWeight = userWeight;
//    }
//
//    public void setUserBMI(double userBMI){
//        this.userBMI = userBMI;
//    }
//
//    public void setUserGender(String userGender){
//        this.userGender = userGender;
//    }
//
//    public void setUserDOB(Long userDOB){
//        this.userDOB = userDOB;
//    }
//
//    public String getEmail(){ return email; }
//
//    public int getUserHeight(){
//        return userHeight;
//    }
//
//    public float getUserWeight(){
//        return userWeight;
//    }
//
//    public double getUserBMI(){
//        return userBMI;
//    }
//
//    public String getUserGender(){
//        return userGender;
//    }
//
//    public Long getUserDOB(){
//        return userDOB;
//    }

}
