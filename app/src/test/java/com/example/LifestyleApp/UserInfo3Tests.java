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
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
public class UserInfo3Tests {

    private UserTestData userTestData;
//    private User user;
    private UserInfo3 userInfo3;

    @Before
    public void setup() throws IOException, JSONException, ParseException {

        userTestData = new UserTestData();

        Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

        String gender = userInfo1TestData.get("gender");
        String dob = userInfo1TestData.get("dob");
        String height = userInfo1TestData.get("height");
        String weight = userInfo1TestData.get("weight");

//        user = userTestData.generateUserFromInfo1(height, weight, dob, gender);
        UserInfo2 userInfo2 = userTestData.generateUserInfo2(gender, dob, height, weight);

        Map<String, String> userInfo2TestData = userTestData.getUserInfo2TestData();

        String city = userInfo2TestData.get("city");
        String country = userInfo2TestData.get("country");

//        user = userTestData.generateUserFromInfo2(user, city, country, whoSees);
        userInfo3 = userTestData.generateUserInfo3(userInfo2, city, country);

    }

    @Test
    public void initialView_userInfo3ShouldNotBeNull() throws Exception {
        assertNotNull(userInfo3);
    }

    @Test
    public void initialView_createButtonCorrectText() {

        Button createButton = (Button) userInfo3.findViewById(R.id.createButton);

        assertTrue("Create button contains incorrect text",
                "Create".equals(createButton.getText().toString()));

    }

    @Test
    public void initialView_snapSelfieTextviewCorrectText() {

        TextView snapSelfie = (TextView) userInfo3.findViewById(R.id.snapSelfie);

        assertTrue("Snap selfie text view contains incorrect text",
                "Snap a Selfie!".equals(snapSelfie.getText().toString()));

    }

    @Test
    public void clickingCreate_shouldContinueToHome() {

        Intent homeIntent = new Intent(userInfo3, Home.class);

        userInfo3.findViewById(R.id.createButton).performClick();

        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(homeIntent.getComponent(), actual.getComponent());

    }
}