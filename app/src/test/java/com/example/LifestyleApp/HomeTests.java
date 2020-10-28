package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;

import com.example.LifestyleApp.MasterList.CustomMasterList;
import com.example.LifestyleApp.MasterList.MasterList;
import com.example.LifestyleApp.UserInfo.UserInfo3;

import org.json.JSONException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)

public class HomeTests {

    private UserTestData userTestData;
//    private User user;
    private double userInfo1BMI;
    private Home home;

    @Before
    public void setup() throws IOException, JSONException, ParseException {

        userTestData = new UserTestData();

        Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

        String gender = userInfo1TestData.get("gender");
        String dob = userInfo1TestData.get("dob");
        String height = userInfo1TestData.get("height");
        String weight = userInfo1TestData.get("weight");

        int ft = Integer.parseInt(height.split(" ")[0]);
        int in = Integer.parseInt(height.split(" ")[2]);
        int heightInInches = (ft * 12) + in;

        float fWeight = Float.parseFloat(weight.split(" ")[0]);

        //bmi Formula: 703 x weight (lbs) / [height (in)]2
        userInfo1BMI = ((703 * fWeight) / Math.pow(heightInInches, 2));

//        user = userTestData.generateUserFromInfo1(height, weight, dob, gender);
        com.example.LifestyleApp.UserInfo.UserInfo2 userInfo2 = userTestData.generateUserInfo2(gender, dob, height, weight);

        Map<String, String> userInfo2TestData = userTestData.getUserInfo2TestData();

        String city = userInfo2TestData.get("city");
        String country = userInfo2TestData.get("country");
        String whoSees = userInfo2TestData.get("whoSees");

//        user = userTestData.generateUserFromInfo2(user, city, country, whoSees);
//        UserInfo3 userInfo3 = userTestData.generateUserInfo3(user, userInfo2, city, country, whoSees);

//        userInfo3.findViewById(R.id.createButton).performClick();
        Intent homeIntent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
//        homeIntent.putExtra("user", user);
        home = Robolectric.buildActivity(Home.class, homeIntent).create().get();

    }

    @Test
    public void homeShouldNotBeNull() throws Exception {
        assertNotNull(home);
    }

    @Test
    public void masterListItemsCorrect() throws InstantiationException, IllegalAccessException {

        CustomMasterList customMasterList = new CustomMasterList();
//        String bmi = user.getBmi() + "";

//        customMasterList.addItem("BMI", bmi);
        customMasterList.addItem("Weather", "Weather");
        customMasterList.addItem("Hikes near me", "Hikes");

        assertTrue("BMI missing from custom master list",
                "BMI".equals(customMasterList.getItemList().get(0).toString()));
        assertTrue("Weather missing from custom master list",
                "Weather".equals(customMasterList.getItemList().get(1).toString()));
        assertTrue("Hikes missing from custom master list",
                "Hikes near me".equals(customMasterList.getItemList().get(2).toString()));

    }

    @Test
    public void initialView_moduleButtonCorrectText() {

        Button moduleButton = (Button) home.findViewById(R.id.moduleButton);

        assertTrue("Module button contains incorrect text",
                "Modules".equals(moduleButton.getText().toString()));

    }

    @Test
    public void clickingModule_shouldContinueToMasterList() {

        Intent masterListIntent = new Intent(home, MasterList.class);

        home.findViewById(R.id.moduleButton).performClick();

        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(masterListIntent.getComponent(), actual.getComponent());

    }
}