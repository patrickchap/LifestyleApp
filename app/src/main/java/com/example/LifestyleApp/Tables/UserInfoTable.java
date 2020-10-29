package com.example.LifestyleApp.Tables;

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
    @ColumnInfo(name = "userID")
    private String userID;

    @ColumnInfo(name = "height")
    private int height;

    @ColumnInfo(name = "weight")
    private float weight;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "dob")
    private Long DOB;

    @ColumnInfo(name = "bmi")
    private double bmi;

    @ColumnInfo(name = "profilePicture")
    private String profilePicture;

    @ColumnInfo(name = "goal")
    private String goal;

    @ColumnInfo(name = "goalSet")
    private boolean goalSet;

    @ColumnInfo(name = "calories")
    private float calories;

    @ColumnInfo(name = "goalWeight")
    private float goalWeight;

    @ColumnInfo(name = "goalWeightSet")
    private boolean goalWeightSet;

    @ColumnInfo(name = "activity")
    private String activity; //sedentary or active

    @ColumnInfo(name = "activitySet")
    private boolean activitySet;

    @ColumnInfo(name = "perWeekPounds")
    private int perWeekPounds;

    @ColumnInfo(name = "bmr")
    private float bmr;

    @ColumnInfo(name = "bmrSet")
    private boolean bmrSet;

    @ColumnInfo(name = "caloriesSet")
    private boolean caloriesSet;

    @ColumnInfo(name = "allGoalsSet")
    private boolean allGoalsSet;

    @ColumnInfo(name = "password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ColumnInfo(name = "userName")
    private String userName;

    @ColumnInfo(name = "steps")
    private int steps;

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
//    public float calories;
    //
    //    //additional info
    //    public float goalWeight;
    //    public boolean goalWeightSet = false;
    //    public String activity; // sedentary  or active
    //    public boolean activitySet = false;
    //    public String goal; // lose, gain, maintain
    //    public boolean goalSet = false;
    //    public int perWeekPounds;
    //    public float BMR;
    //    public boolean BMRSet = false;
    //    public boolean caloriesSet = false;
    //    public boolean allGoalsSet = false;

    public UserInfoTable(@NonNull String userID){
        this.userID = userID;
        height = 0;
        weight = 0;
        gender = "";
        DOB = Long.valueOf(0);
        bmi = 0;
        profilePicture = "";
        goal = "";
        goalSet = false;
        calories = 0;
        goalWeight = 0;
        goalWeightSet = false;
        activity = "";
        activitySet = false;
        perWeekPounds = 0;
        bmr = 0;
        bmrSet = false;
        caloriesSet = false;
        allGoalsSet = false;
        userName = "";
        password = "";
        steps = 0;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setDOB(Long dob){
        this.DOB = dob;
    }

    public void setBmi(double bmi){
        this.bmi = bmi;
    }

    public void setProfilePicture(String profilePicture){
        this.profilePicture = profilePicture;
    }

    public void setGoal(String goal){ this.goal = goal; }

    public void setGoalSet(boolean goalSet){ this.goalSet = goalSet; }

    public void setCalories(float calories){ this.calories = calories; }

    public void setGoalWeight(float goalWeight){ this.goalWeight = goalWeight; }

    public void setGoalWeightSet(boolean goalWeightSet){ this.goalWeightSet = goalWeightSet; }

    public void setActivity(String activity){ this.activity = activity; }

    public void setActivitySet(boolean activitySet){ this.activitySet = activitySet; }

    public void setPerWeekPounds(int perWeekPounds){ this.perWeekPounds = perWeekPounds; }

    public void setBmr(float bmr){ this.bmr = bmr; }

    public void setBmrSet(boolean bmrSet){ this.bmrSet = bmrSet; }

    public void setCaloriesSet(boolean caloriesSet){ this.caloriesSet = caloriesSet; }

    public void setAllGoalsSet(boolean allGoalsSet){ this.allGoalsSet = allGoalsSet; }

    public String getUserID(){return userID;}

    public int getHeight(){ return height; }

    public float getWeight(){
        return weight;
    }

    public String getGender(){ return gender; }

    public Long getDOB(){ return DOB; }

    public double getBmi(){ return bmi; }

    public String getProfilePicture(){ return profilePicture; }

    public String getGoal(){ return goal; }

    public boolean getGoalSet(){ return goalSet; }

    public float getCalories(){ return calories; }

    public float getGoalWeight(){ return goalWeight; }

    public boolean getGoalWeightSet(){ return goalWeightSet; }

    public String getActivity(){ return activity; }

    public boolean getActivitySet(){ return activitySet; }

    public int getPerWeekPounds(){ return perWeekPounds; }

    public float getBmr(){ return bmr; }

    public boolean getBmrSet(){ return bmrSet; }

    public boolean getCaloriesSet(){ return caloriesSet; }

    public boolean getAllGoalsSet(){ return allGoalsSet; }


}
