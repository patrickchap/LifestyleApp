package com.example.LifestyleApp.UserInfo;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

//Declare methods as static. We don't want to create objects of this class.
public class JSONUserInputUtils {

    public static UserData getUserInfoData(String data) throws JSONException {

        UserData userData = new UserData();

        //Start parsing JSON data
        JSONObject jsonObject = new JSONObject(data); //Must throw JSONException

        UserData.UserData1 userData1 = userData.getUserData1();

        int height = jsonObject.getInt("height");
        if(height != 0){
            userData1.setHeight(height);
        }
        float weight = (float) jsonObject.getDouble("weight");
        if(weight != 0){
            userData1.setWeight(weight);
        }
        double bmi = jsonObject.getDouble("bmi");
        if(bmi != 0){
            userData1.setBmi(bmi);
        }
        String gender = jsonObject.getString("gender");
        if(!gender.equals("")){
            userData1.setGender(gender);
        }
        Long dob = jsonObject.getLong("dob");
        if(!dob.equals(Long.valueOf(0))){
            userData1.setDob(dob);
        }

        UserData.UserData3 userData3 = userData.getUserData3();

        String profilePicture = jsonObject.getString("profilePicture");
        if(!profilePicture.equals("")){
            userData3.setProfilePicture(profilePicture);
        }

        return userData;
    }
}