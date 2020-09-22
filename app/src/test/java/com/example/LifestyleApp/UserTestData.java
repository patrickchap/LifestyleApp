package com.example.LifestyleApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    public String getRandomPhoto() throws IOException, JSONException {

        String photo = "";

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
            photo = results.getJSONObject(0).getJSONObject("picture").getString("medium");

        }

        in.close();

        return photo;

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


