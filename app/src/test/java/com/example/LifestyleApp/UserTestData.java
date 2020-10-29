package com.example.LifestyleApp;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.LifestyleApp.UserInfo.UserInfo1;
import com.example.LifestyleApp.UserInfo.UserInfo2;
import com.example.LifestyleApp.UserInfo.UserInfo3;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.robolectric.Shadows.shadowOf;

public class UserTestData {

    public Map<String, String> getUserInfo1TestData() throws IOException, JSONException {

        Map<String, String> userInfo1 = new HashMap<>();

        String gender = "";
        String dob = "";

        URL url = new URL("https://randomuser.me/api/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {

            JSONObject obj = new JSONObject(inputLine);
            JSONArray results = obj.getJSONArray("results");

            gender = results.getJSONObject(0).getString("gender");

            dob = results.getJSONObject(0).getJSONObject("dob").getString("date");
            String dobOnly = dob.split("T")[0];
            String[] dobSplit = dobOnly.split("-");
            String dobMonth = dobSplit[1];
            String dobDay = dobSplit[2];
            String dobYear = dobSplit[0];
            dob = dobMonth + "/" + dobDay + "/" + dobYear;

        }

        in.close();

        userInfo1.put("gender", gender);
        userInfo1.put("dob", dob);
        userInfo1.put("height", getRandomHeight());
        userInfo1.put("weight", getRandomWeight());

        return userInfo1;

    }

    public Map<String, String> getUserInfo2TestData() throws IOException, JSONException {

        Map<String, String> userInfo2 = new HashMap<>();

        String city = "";
        String country = "";

        URL url = new URL("https://randomuser.me/api/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {

            JSONObject obj = new JSONObject(inputLine);
            JSONArray results = obj.getJSONArray("results");

            JSONObject location = results.getJSONObject(0).getJSONObject("location");
            city = location.getString("city");
            country = location.getString("country");

        }

        in.close();

        int randomStringLength = 10;
        String whoSees = getRandomString(randomStringLength);

        userInfo2.put("city", city);
        userInfo2.put("country", country);
        userInfo2.put("whoSees", whoSees);

        System.out.println("CITY IN USERINFO"+city);
        System.out.println("country IN USERINFO"+country);
        System.out.println("whosees IN USERINFO"+whoSees);

        return userInfo2;

    }

//    public User generateUserFromInfo1(String height, String weight, String dob, String gender) throws ParseException {
//
//        User user = new User();
//
//        int ft = Integer.parseInt(height.split(" ")[0]);
//        int in = Integer.parseInt(height.split(" ")[2]);
//        int heightInInches = (ft * 12) + in;
//
//        float fWeight = Float.parseFloat(weight.split(" ")[0]);
//
//        //bmi Formula: 703 x weight (lbs) / [height (in)]2
//        double bmi = ((703 * fWeight) / Math.pow(heightInInches, 2));
//
//        user.setWeight(fWeight);
//        user.setBmi(bmi);
//        user.setHeight(heightInInches);
//        Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
//        user.setDOB(DOB);
//        user.setGender(gender);
//
//        return user;
//
//    }

//    public User generateUserFromInfo2(User user, String city, String country, String whoSees) throws IOException, JSONException, ParseException {
//
//        user.setCity(city);
//        user.setCountry(country);
//        user.setWhoCanSee(whoSees);
//
//        return user;
//
//    }

    public UserInfo2 generateUserInfo2(String gender, String dob, String height, String weight) throws IOException, JSONException, ParseException {

        UserInfo1 userInfo1 = Robolectric.setupActivity(UserInfo1.class);

        TextView genderTextView = userInfo1.findViewById(R.id.genderTextView);
        genderTextView.setText(gender);

        TextView dobTextView = userInfo1.findViewById(R.id.birthdayTextView);
        dobTextView.setText(dob);

        TextView heightTextView = userInfo1.findViewById(R.id.heightTextView);
        heightTextView.setText(height);

        TextView weightTextView = userInfo1.findViewById(R.id.weightTextView);
        weightTextView.setText(weight);

        userInfo1.findViewById(R.id.continueButton).performClick();
        Intent userInfo2Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

//        User user = generateUserFromInfo1(height, weight, dob, gender);

//        userInfo2Intent.putExtra("user", user);
        UserInfo2 userInfo2 = Robolectric.buildActivity(UserInfo2.class, userInfo2Intent).create().get();

        return userInfo2;

    }

    public UserInfo3 generateUserInfo3(UserInfo2 userInfo2, String city, String country){

        EditText cityEditText = userInfo2.findViewById(R.id.editTextCity);
        cityEditText.setText(city);

        EditText countryEditText = userInfo2.findViewById(R.id.editTextCountry);
        countryEditText.setText(country);


        userInfo2.findViewById(R.id.continueButton).performClick();

        Intent userInfo3Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        UserInfo3 userInfo3 = Robolectric.buildActivity(UserInfo3.class, userInfo3Intent).create().get();

        return userInfo3;

}

    private String getRandomHeight(){

        String height = "";

        Random rand = new Random();

        //height format: 9 ft 0 in
        int min = 0;

        int maxFeet = 9;
        int feet = rand.nextInt((maxFeet - min) + 1);

        int maxInches = 11;
        int inches = rand.nextInt((maxInches - min) + 1);

        height = feet + " ft " + inches + " in";

        return height;

    }

    private String getRandomWeight(){

        String weight = "";

        //format: 900.0 lbs
        Random rand = new Random();

        double max = 900.0;
        double min = 0.0;

        double randomDouble = min + (max - min) * rand.nextDouble();

        double lbs = Double.parseDouble(String.format("%.1f", randomDouble));

        weight = lbs + " lbs";

        return weight;

    }

    private String getRandomString(int stringLength){

        String randomString = "";

        int minAscii = 32;
        int maxAscii = 127;

        for (int letterIndex = 0; letterIndex < stringLength; letterIndex++) {

            Random rand = new Random();
            int randInt = rand.nextInt((maxAscii - minAscii) + 1);
            String randChar = String.valueOf((char) (randInt));
            randomString += randChar;

        }

        return randomString;

    }
}


