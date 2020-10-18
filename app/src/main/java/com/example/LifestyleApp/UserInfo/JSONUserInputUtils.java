package com.example.LifestyleApp.UserInfo;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

//Declare methods as static. We don't want to create objects of this class.
public class JSONUserInputUtils {

    public static UserData getUserInfoData(String data, UserData currentUser) throws JSONException {

        //Start parsing JSON data
        JSONObject jsonObject = new JSONObject(data); //Must throw JSONException

        int height = jsonObject.getInt("height");
        if(height != 0){
            currentUser.setHeight(height);
        }
        float weight = (float) jsonObject.getDouble("weight");
        if(weight != 0){
            currentUser.setWeight(weight);
        }
        double bmi = jsonObject.getDouble("bmi");
        if(bmi != 0){
            currentUser.setBmi(bmi);
        }
        String gender = jsonObject.getString("gender");
        if(!gender.equals("")){
            currentUser.setGender(gender);
        }
        Long dob = jsonObject.getLong("dob");
        if(!dob.equals(Long.valueOf(0))){
            currentUser.setDob(dob);
        }
        String profilePicture = jsonObject.getString("profilePicture");
        System.out.println("JSON USER INPUT UTILS: SETTING PROFILE PICTURE");
        if(!profilePicture.equals("")){
            currentUser.setProfilePicture(profilePicture);
        }

        return currentUser;
    }
}