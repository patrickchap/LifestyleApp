package com.example.LifestyleApp.UserInfo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    //user info
    public String gender;
    public Date DOB;
    public int height; //inches
    public float weight;
    public String country;
    public String city;
    public String whoCanSee;
    public  double bmi;
    public byte[] profilePicture;

    //additional info
    public float goalWeight;
    public boolean goalWeightSet = false;
    public String activity; // sedentary  or active
    public boolean activitySet = false;
    public String goal; // lose, gain, maintain
    public boolean goalSet = false;
    public int perWeekPounds;
    public float BMR;
    public boolean BMRSet = false;

    public User() {

    }

    public float getBMR() {
        return BMR;
    }

    public void setBMR(float BMR) {
        this.BMR = BMR;
    }

    public boolean isBMRSet() {
        return BMRSet;
    }

    public void setBMRSet(boolean BMRSet) {
        this.BMRSet = BMRSet;
    }


    public boolean isGoalWeightSet() {
        return goalWeightSet;
    }

    public void setGoalWeightSet(boolean goalWeightSet) {
        this.goalWeightSet = goalWeightSet;
    }

    public boolean isActivitySet() {
        return activitySet;
    }

    public void setActivitySet(boolean activitySet) {
        this.activitySet = activitySet;
    }

    public boolean isGoalSet() {
        return goalSet;
    }

    public void setGoalSet(boolean goalSet) {
        this.goalSet = goalSet;
    }



    public int getPerWeekPounds() {
        return perWeekPounds;
    }

    public void setPerWeekPounds(int perWeekPounds) {
        this.perWeekPounds = perWeekPounds;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWhoCanSee() {
        return whoCanSee;
    }

    public void setWhoCanSee(String whoCanSee) {
        this.whoCanSee = whoCanSee;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public float getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(float goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    protected User(Parcel in){
        gender = in.readString();
        height = in.readInt();
        weight = in.readFloat();
        country = in.readString();
        city = in.readString();
        whoCanSee = in.readString();
        bmi = in.readDouble();
        profilePicture = in.createByteArray();
        goalWeight = in.readFloat();
        activity = in.readString();
        goal = in.readString();
    }


//    public static final Creator<User> CREATOR = new Creator<User>() {
//        @Override
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        @Override
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(gender);
//        dest.writeInt(height);
//        dest.writeFloat(weight);
//        dest.writeString(country);
//        dest.writeString(city);
//        dest.writeString(whoCanSee);
//        dest.writeDouble(bmi);
//        dest.writeByteArray(profilePicture);
//        dest.writeFloat(goalWeight);
//        dest.writeString(activity);
//        dest.writeString(goal);
//    }
}
