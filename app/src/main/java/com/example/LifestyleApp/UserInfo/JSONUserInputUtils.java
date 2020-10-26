package com.example.LifestyleApp.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUserInputUtils {

    public static UserData getUserInfoData(String userID, String data, UserData oldData) throws JSONException {

        UserData userData = new UserData(userID);
        JSONObject jsonObject = new JSONObject(data);

        if (oldData == null) {
            return addData(userData, jsonObject);
        }
        UserData.UserData1 userData1 = userData.getUserData1();
        int height = jsonObject.getInt("height");
        if (height != 0) {
            userData1.setHeight(height);
        } else {
            userData1.setHeight(oldData.getUserData1().getHeight());
        }
        float weight = (float) jsonObject.getDouble("weight");
        if (weight != 0) {
            userData1.setWeight(weight);
        } else {
            userData1.setWeight(oldData.getUserData1().getWeight());
        }
        double bmi = jsonObject.getDouble("bmi");
        if (bmi != 0) {
            userData1.setBmi(bmi);
        } else {
            userData1.setBmi(oldData.getUserData1().getBmi());
        }
        String gender = jsonObject.getString("gender");
        if (!gender.equals("")) {
            userData1.setGender(gender);
        } else {
            userData1.setGender(oldData.getUserData1().getGender());
        }
        Long dob = jsonObject.getLong("dob");
        if (!dob.equals(Long.valueOf(0))) {
            userData1.setDob(dob);
        } else {
            userData1.setDob(oldData.getUserData1().getDob());
        }

        UserData.UserData3 userData3 = userData.getUserData3();
        String profilePicture = jsonObject.getString("profilePicture");
        if (!profilePicture.equals("")) {
            userData3.setProfilePicture(profilePicture);
        } else {
            userData3.setProfilePicture(oldData.getUserData3().getProfilePicture());
        }
        return userData;
    }

    private static UserData addData(UserData userData, JSONObject jsonObject) throws JSONException {
        UserData.UserData1 userData1 = userData.getUserData1();
        int height = jsonObject.getInt("height");
        userData1.setHeight(height);
        float weight = (float) jsonObject.getDouble("weight");
        userData1.setWeight(weight);
        double bmi = jsonObject.getDouble("bmi");
        userData1.setBmi(bmi);
        String gender = jsonObject.getString("gender");
        userData1.setGender(gender);
        Long dob = jsonObject.getLong("dob");
        userData1.setDob(dob);

        UserData.UserData3 userData3 = userData.getUserData3();
        String profilePicture = jsonObject.getString("profilePicture");
        userData3.setProfilePicture(profilePicture);

        return userData;
    }
}