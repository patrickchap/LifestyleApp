package com.example.LifestyleApp.UserInfo;

public class UserData {

    private String mEmail;

    private int height; //inches
    private float weight;
    private double bmi;
    private String gender;
    private Long DOB;
    private String city;
    private String country;
    private String profilePicture;

    private UserData1 userData1 = new UserData1();
    private UserData2 userData2 = new UserData2();
    private UserData3 userData3 = new UserData3();
    private UserGoals userGoals = new UserGoals();
    private UserActivity userActivity = new UserActivity();

    public UserData() {
//        weight = 0;
//        bmi = 0;
//        gender = "";
//        DOB = Long.valueOf(0);
//        city = "";
//        country = "";
//        profilePicture = "";
    }

    public class UserData1 {
        private int height; //inches
        private float weight;
        private double bmi;
        private String gender;
        private Long DOB;

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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

    public class UserGoals {

        public float goalWeight;
        public boolean goalWeightSet = false;
        public boolean allGoalsSet = false;
        public String goal; // lose, gain, maintain
        public boolean goalSet = false;
        public int perWeekPounds;


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

        public String getGoal() {
            return goal;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }

        public int getPerWeekPounds() {
            return perWeekPounds;
        }

        public void setPerWeekPounds(int perWeekPounds) {
            this.perWeekPounds = perWeekPounds;
        }


    }

    public class UserActivity{

        public String activity; // sedentary  or active
        public boolean activitySet = false;

        public boolean isActivitySet() {
            return activitySet;
        }

        public void setActivitySet(boolean activitySet) {
            this.activitySet = activitySet;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

    }

    public UserData1 getUserData1(){
        return userData1;
    }

    public UserData2 getUserData2(){
        return userData2;
    }

    public UserData3 getUserData3(){
        return userData3;
    }

    public UserGoals getUserGoals(){
        return userGoals;
    }

    public UserActivity getUserActivity(){
        return userActivity;
    }


}
