package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.LifestyleApp.UserInfo.UserInfo1;
import com.example.LifestyleApp.UserInfo.UserInfo3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)

public class HomeTests {

    private UserInfo1 userInfo1;
    private UserInfo2 userInfo2;
    private UserInfo3 userInfo3;
    private Home home;

    private double userInfo1BMI;
    private ImageView mProfilePictureImageView;

    @Before
    public void setup() {

        userInfo1 = Robolectric.setupActivity(UserInfo1.class);

        TextView gender = userInfo1.findViewById(R.id.genderTextView);
        gender.setText("Male");

        TextView birthday = userInfo1.findViewById(R.id.birthdayTextView);
        birthday.setText("9/19/2020");

        TextView height = userInfo1.findViewById(R.id.heightTextView);
        height.setText("9 ft 0 in");

        TextView weight = userInfo1.findViewById(R.id.weightTextView);
        weight.setText("900.0 lbs");

        int ft = Integer.parseInt(height.getText().toString().split(" ")[0]);
        int in = Integer.parseInt(height.getText().toString().split(" ")[2]);
        int heightInInches = (ft * 12) + in;

        float fWeight = Float.parseFloat(weight.getText().toString().split(" ")[0]);

        //bmi Formula: 703 x weight (lbs) / [height (in)]2
        userInfo1BMI = ((703 * fWeight) / Math.pow(heightInInches,2));

        userInfo1.findViewById(R.id.continueButton).performClick();

        Intent userInfo2Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        userInfo2 =  Robolectric.buildActivity(UserInfo2.class, userInfo2Intent).create().get();

        TextView cityView = userInfo2.findViewById(R.id.editTextCity);
        TextView countryView = userInfo2.findViewById(R.id.editTextCountry);
        TextView whoSeesView = userInfo2.findViewById(R.id.editTextWhoCanSee);

        cityView.setText("Salt Lake City");
        countryView.setText("United States");
        whoSeesView.setText("Me");

        userInfo2.findViewById(R.id.continueButton).performClick();

        Intent userInfo3Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        userInfo3 =  Robolectric.buildActivity(UserInfo3.class, userInfo3Intent).create().get();

        userInfo3.findViewById(R.id.createButton).performClick();
        Intent homeIntent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        home =  Robolectric.buildActivity(Home.class, homeIntent).create().get();

    }

    @Test
    public void homeShouldNotBeNull() throws Exception {
        assertNotNull(home);
    }

    @Test
    public void masterListItemsCorrect() throws InstantiationException, IllegalAccessException {

        CustomMasterList customMasterList = new CustomMasterList();
        String bmi = Double.toString(home.getIntent().getDoubleExtra("bmi", 0));

        customMasterList.addItem("BMI", bmi);
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
    public void homeBMIMatchesUserInfo1() {

        double homeBMI = home.getIntent().getDoubleExtra("bmi", 0);

        assertTrue(userInfo1BMI == homeBMI);

    }

}