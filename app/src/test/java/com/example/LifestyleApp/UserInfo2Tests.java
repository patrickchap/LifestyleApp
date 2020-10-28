package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import com.example.LifestyleApp.UserInfo.UserInfo2;
import com.example.LifestyleApp.UserInfo.UserInfo3;

import org.json.JSONException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
public class UserInfo2Tests {

    private UserTestData userTestData;
    private UserInfo2 userInfo2;
    private int numUsers = 10;
//    User user;

    @Before
    public void setup() throws IOException, JSONException, ParseException {

        userTestData = new UserTestData();

        Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

        String gender = userInfo1TestData.get("gender");
        String dob = userInfo1TestData.get("dob");
        String height = userInfo1TestData.get("height");
        String weight = userInfo1TestData.get("weight");

//        user = userTestData.generateUserFromInfo1(height, weight, dob, gender);
        userInfo2 = userTestData.generateUserInfo2(gender, dob, height, weight);

    }

    @Test
    public void initialView_userInfo2ShouldNotBeNull() throws Exception
    {
        assertNotNull( userInfo2 );
    }

    @Test
    public void initialView_continueButtonCorrectText() {

        Button continueButton = (Button) userInfo2.findViewById(R.id.continueButton);

        assertTrue("Continue button contains incorrect text",
                "Continue".equals(continueButton.getText().toString()));

    }

    @Test
    public void inputtingUserData_shouldMatchRandomData() throws IOException, JSONException {

            Map<String, String> userInfo2TestData = userTestData.getUserInfo2TestData();

            String city = userInfo2TestData.get("city");
            String country = userInfo2TestData.get("country");
            String whoSees = userInfo2TestData.get("whoSees");

            TextView cityTextView = (TextView) userInfo2.findViewById(R.id.editTextCity);
            cityTextView.setText(city);
            assertEquals("City text view has incorrect text", city, cityTextView.getText().toString());

            TextView countryTextView = (TextView) userInfo2.findViewById(R.id.editTextCountry);
            countryTextView.setText(country);
            assertEquals("Country text view has incorrect text", country, countryTextView.getText().toString());

//            TextView whoSeesTextView = (TextView) userInfo2.findViewById(R.id.editTextWhoCanSee);
//            whoSeesTextView.setText(whoSees);
//            assertEquals("Who Sees text view has incorrect text", whoSees, whoSeesTextView.getText().toString());

    }
//
//    @Test
//    public void clickingContinueWithUserInfoComplete_shouldContinueToUserInfo3() throws IOException, JSONException {
//
//            Map<String, String> userInfo2TestData = userTestData.getUserInfo2TestData();
//
//            String city = userInfo2TestData.get("city");
//            String country = userInfo2TestData.get("country");
//            String whoSees = userInfo2TestData.get("whoSees");
//
//            TextView cityTextView = (TextView) userInfo2.findViewById(R.id.editTextCity);
//            cityTextView.setText(city);
//
//            TextView countryTextView = (TextView) userInfo2.findViewById(R.id.editTextCountry);
//            countryTextView.setText(country);
//
////            TextView whoSeesTextView = (TextView) userInfo2.findViewById(R.id.editTextWhoCanSee);
////            whoSeesTextView.setText(whoSees);
//
////            user.setCity(city);
////            user.setCountry(country);
////            user.setWhoCanSee(whoSees);
//
//            Intent userInfo3Intent = new Intent(userInfo2, UserInfo3.class);
////            userInfo3Intent.putExtra("user", user);
//
//            userInfo2.findViewById(R.id.continueButton).performClick();
//            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
//
//            assertEquals(userInfo3Intent.getComponent(), actual.getComponent());
//
//    }
//
//    @Test
//    public void clickingContinueWithOutCity_shouldPromptAlert() throws IOException, JSONException {
//
//            Map<String, String> userInfo2TestData = userTestData.getUserInfo2TestData();
//
//            String country = userInfo2TestData.get("country");
//            String whoSees = userInfo2TestData.get("whoSees");
//
//            TextView countryTextView = (TextView) userInfo2.findViewById(R.id.editTextCountry);
//            countryTextView.setText(country);
//
////            TextView whoSeesTextView = (TextView) userInfo2.findViewById(R.id.editTextWhoCanSee);
////            whoSeesTextView.setText(whoSees);
//
////            user.setCountry(country);
////            user.setWhoCanSee(whoSees);
//
//            Intent userInfo3Intent = new Intent(userInfo2, UserInfo3.class);
////            userInfo3Intent.putExtra("user", user);
//
//            userInfo2.findViewById(R.id.continueButton).performClick();
//
//            ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
//            assertEquals("User Info Incomplete", dialog.getTitle());
//
//    }
//
//    @Test
//    public void clickingContinueWithOutCountry_shouldPromptAlert() throws IOException, JSONException {
//
//            Map<String, String> userInfo2TestData = userTestData.getUserInfo2TestData();
//
//            String city = userInfo2TestData.get("city");
//            String whoSees = userInfo2TestData.get("whoSees");
//
//            TextView cityTextView = (TextView) userInfo2.findViewById(R.id.editTextCity);
//            cityTextView.setText(city);
//
////            TextView whoSeesTextView = (TextView) userInfo2.findViewById(R.id.editTextWhoCanSee);
////            whoSeesTextView.setText(whoSees);
//
////            user.setCity(city);
////            user.setWhoCanSee(whoSees);
//
//            Intent userInfo3Intent = new Intent(userInfo2, UserInfo3.class);
////            userInfo3Intent.putExtra("user", user);
//
//            userInfo2.findViewById(R.id.continueButton).performClick();
//
//            ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
//            assertEquals("User Info Incomplete", dialog.getTitle());
//
//    }
//
//    @Test
//    public void clickingContinueWithOutWhoSees_shouldPromptAlert() throws IOException, JSONException {
//
//            Map<String, String> userInfo2TestData = userTestData.getUserInfo2TestData();
//
//            String city = userInfo2TestData.get("city");
//            String country = userInfo2TestData.get("country");
//
//            TextView cityTextView = (TextView) userInfo2.findViewById(R.id.editTextCity);
//            cityTextView.setText(city);
//
//            TextView countryTextView = (TextView) userInfo2.findViewById(R.id.editTextCountry);
//            countryTextView.setText(country);
//
////            user.setCity(city);
////            user.setCountry(country);
//
//            Intent userInfo3Intent = new Intent(userInfo2, UserInfo3.class);
////            userInfo3Intent.putExtra("user", user);
//
//            userInfo2.findViewById(R.id.continueButton).performClick();
//
//            ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
//            assertEquals("User Info Incomplete", dialog.getTitle());
//    }

}