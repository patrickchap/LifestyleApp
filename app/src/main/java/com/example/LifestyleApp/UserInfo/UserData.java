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

    public UserData(String email) {
//
        mEmail = email;
//        weight = 0;
//        bmi = 0;
//        gender = "";
//        DOB = Long.valueOf(0);
//        city = "";
//        country = "";
//        profilePicture = "";
    }

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

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getProfilePicture() {
        return profilePicture;
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

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String city) {
        this.country = country;
    }

    public void setProfilePicture(String profilePicture) {
        System.out.println("USER DATA: SETTING PROFILE PICTURE");
        this.profilePicture = profilePicture;
    }

}
