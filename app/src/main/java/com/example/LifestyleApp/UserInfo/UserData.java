package com.example.LifestyleApp.UserInfo;

import javax.security.auth.callback.PasswordCallback;

public class UserData {

    //new
    private UserData0 userData0;
    private UserData1 userData1;
    private UserData2 userData2;
    private UserData3 userData3;
    private UserGoals userGoals;
    private UserDataSteps userDataSteps;
    private String userID;

    public UserData(String userID) {
        this.userID = userID;
        userData1 = null;
        userData2 = null;
        userData3 = null;
        userGoals = null;
        //new
        userData0 = null;
    }

    //new
    public class UserData0 {
        private String password;
        private String userName;

        public UserData0() {
        }

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
    }

    public class UserDataSteps{
        private int steps;

        public UserDataSteps(){

        }

        public int getSteps() {
            return steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }
    }



    public class UserData1 {
        private int height; //inches
        private float weight;
        private double bmi;
        private String gender;
        private Long DOB;

        public UserData1(){}

        public int getHeight() {
            return height;
        }

        public float getWeight() {
            return weight;
        }

        public String getGender() {
            return gender;
        }

        public double getBmi() {
            return bmi;
        }

        public Long getDob() {
            return DOB;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public void setBmi(double bmi) {
            this.bmi = bmi;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setDob(Long dob) {
            this.DOB = dob;
        }

    }

    public class UserData2 {
        private String city;
        private String country;

        public String getCity() {
            return city;
        }

        public String getCountry() {
            return country;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCountry(String city) {
            this.country = country;
        }

    }

    public class UserData3 {

    private String profilePicture;

    public UserData3(){}

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

    public class UserGoals {

        private float goalWeight = 0;
        private boolean goalWeightSet = false;
        private boolean allGoalsSet = false;
        private String goal = ""; // lose, gain, maintain
        private boolean goalSet = false;
        private int perWeekPounds = 0;
        private String activity = ""; // sedentary  or active
        private boolean activitySet = false;
        private float bmr = 0;
        private boolean bmrSet = false;
        private float calories = 0;

        public boolean isAllGoalsSet() {
            return allGoalsSet;
        }

        public void setAllGoalsSet(boolean allGoalsSet) {
            this.allGoalsSet = allGoalsSet;
        }

        public boolean isGoalWeightSet() {
            return goalWeightSet;
        }

        public void setGoalWeightSet(boolean goalWeightSet) {
            this.goalWeightSet = goalWeightSet;
        }
        public boolean isGoalSet() {
            return goalSet;
        }

        public void setGoalSet(boolean goalSet) {
            this.goalSet = goalSet;
        }

        public float getGoalWeight() {
            return goalWeight;
        }

        public void setGoalWeight(float goalWeight) {
            this.goalWeight = goalWeight;
        }

        public void setPerWeekPounds(int perWeekPounds) {
            this.perWeekPounds = perWeekPounds;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public void setActivitySet(boolean activitySet) {
            this.activitySet = activitySet;
        }

        public void setBmr(float bmr) {
            this.bmr = bmr;
        }

        public void setBmrSet(boolean bmrSet) {
            this.bmrSet = bmrSet;
        }

        public void setCalories(float calories) {
            this.calories = calories;
        }

        public String getGoal() {
            return goal;
        }

        public int getPerWeekPounds() {
            return perWeekPounds;
        }

        public String getActivity() {
            return activity;
        }

        public boolean getActivitySet() {
            return activitySet;
        }

        public float getBmr() {
            return bmr;
        }

        public boolean getBmrSet() {
            return bmrSet;
        }

        public float getCalories() {
            return calories;
        }

    }

    public UserData1 getUserData1(){
        if (userData1 == null){
            userData1 = new UserData1();
        }
        return userData1;
    }

    public UserData2 getUserData2(){
        if (userData2 == null){
            userData2 = new UserData2();
        }
        return userData2;
    }

    public UserData3 getUserData3(){
        if (userData3 == null){
            userData3 = new UserData3();
        }
        return userData3;
    }

    public UserGoals getUserGoals(){
        if (userGoals == null){
            userGoals = new UserGoals();
        }
        return userGoals;
    }

    public UserData0 getUserData0(){
        if(userData0 == null){
            userData0 = new UserData0();
        }
        return userData0;
    }

    public UserDataSteps getUserDataSteps(){
        if(userDataSteps == null){
            userDataSteps = new UserDataSteps();
        }
        return userDataSteps;
    }

}
