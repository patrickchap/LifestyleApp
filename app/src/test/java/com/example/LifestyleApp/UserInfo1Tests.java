package com.example.LifestyleApp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfo1;
import com.example.LifestyleApp.UserInfo.UserInfo2;
import com.example.LifestyleApp.UserInfo.UserInfoDao;
import com.example.LifestyleApp.UserInfo.UserInfoDatabase;
import com.example.LifestyleApp.UserInfo.UserInfoViewModel;

import org.json.JSONException;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
public class UserInfo1Tests {

    private UserInfo1 userInfo1;
    private int numUsers = 10;
    private UserInfoDatabase userInfoDatabase;
    private UserInfoDao userInfoDao;


    @Before
    public void setup() throws IOException, JSONException {

        userInfo1 = Robolectric.setupActivity(UserInfo1.class);
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userInfoDatabase = Room.inMemoryDatabaseBuilder(context, UserInfoDatabase.class).allowMainThreadQueries().build();
        userInfoDao = userInfoDatabase.mUserInfoDao();

    }

    @After
    public void closeDB() {
        userInfoDatabase.close();
    }

    @Test
    public void initialView_userInfo1ShouldNotBeNull() throws Exception {
        assertNotNull(userInfo1);
    }

    @Test
    public void initialView_continueButtonCorrectText() {
        Button continueButton = (Button) userInfo1.findViewById(R.id.continueButton);
        assertEquals("Continue button contains incorrect text", "Continue", continueButton.getText().toString());
    }

    @Test
    public void initialView_textViewsCorrect() {

        TextView genderTextView = (TextView) userInfo1.findViewById(R.id.genderTextView);
        assertEquals("Gender Text View contains incorrect text", "Gender", genderTextView.getText().toString());

        TextView dob = (TextView) userInfo1.findViewById(R.id.birthdayTextView);
        assertEquals("DOB Text View contains incorrect text", "Birthday", dob.getText().toString());

        TextView weight = (TextView) userInfo1.findViewById(R.id.weightTextView);
        assertEquals("Weight Text View contains incorrect text", "Weight", weight.getText().toString());

        TextView height = (TextView) userInfo1.findViewById(R.id.heightTextView);
        assertEquals("Height Text View contains incorrect text", "Height", height.getText().toString());

    }

    @Test
    public void inputtingUserData_shouldMatchRandomData() throws IOException, JSONException {

        for (int i = 0; i < numUsers; i++){

            UserTestData userTestData = new UserTestData();

            Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

            String gender = userInfo1TestData.get("gender");
            String dob = userInfo1TestData.get("dob");
            String height = userInfo1TestData.get("height");
            String weight = userInfo1TestData.get("weight");

            TextView genderTextView = userInfo1.findViewById(R.id.genderTextView);
            genderTextView.setText(gender);
            assertEquals("Gender Text View does not match user input", gender, genderTextView.getText().toString());

            TextView dobTextView = userInfo1.findViewById(R.id.birthdayTextView);
            dobTextView.setText(dob);
            assertEquals("Birthday Text View does not match user input", dob, dobTextView.getText().toString());

            TextView heightTextView = userInfo1.findViewById(R.id.heightTextView);
            heightTextView.setText(height);
            assertEquals("Height Text View does not match user input", height, heightTextView.getText().toString());

            TextView weightTextView = userInfo1.findViewById(R.id.weightTextView);
            weightTextView.setText(weight);
            assertEquals("Weight Text View does not match user input", weight, weightTextView.getText().toString());

        }

    }



//    @Test
//    public void clickingContinueWithUserInfoComplete_shouldContinueToUserInfo2() throws IOException, JSONException {
//
//        for (int i = 0; i < numUsers; i++) {
//
//            UserTestData userTestData = new UserTestData();
//
//            Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();
//
//            String gender = userInfo1TestData.get("gender");
//            String dob = userInfo1TestData.get("dob");
//            String height = userInfo1TestData.get("height");
//            String weight = userInfo1TestData.get("weight");
//
//            TextView genderTextView = userInfo1.findViewById(R.id.genderTextView);
//            genderTextView.setText(gender);
//
//            TextView dobTextView = userInfo1.findViewById(R.id.birthdayTextView);
//            dobTextView.setText(dob);
//
//            TextView heightTextView = userInfo1.findViewById(R.id.heightTextView);
//            heightTextView.setText(height);
//
//            TextView weightTextView = userInfo1.findViewById(R.id.weightTextView);
//            weightTextView.setText(weight);
//
//            Intent userInfo2Intent = new Intent(userInfo1, UserInfo2.class);
//
////            System.out.println(heightTextView.getText());
//
//            userInfo1.findViewById(R.id.continueButton).performClick();
//            Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
//
//            assertEquals(userInfo2Intent.getComponent(), actual.getComponent());
//
//        }
//    }

    @Test
    public void clickingContinueWithoutHeight_shouldPromptAlert() throws IOException, JSONException {

        UserTestData userTestData = new UserTestData();

        Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

        String gender = userInfo1TestData.get("gender");
        String dob = userInfo1TestData.get("dob");
        String weight = userInfo1TestData.get("weight");

        TextView genderTextView = userInfo1.findViewById(R.id.genderTextView);
        genderTextView.setText(gender);

        TextView dobTextView = userInfo1.findViewById(R.id.birthdayTextView);
        dobTextView.setText(dob);

        TextView weightTextView = userInfo1.findViewById(R.id.weightTextView);
        weightTextView.setText(weight);

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }

    @Test
    public void clickingContinueWithoutWeight_shouldPromptAlert() throws IOException, JSONException {

        UserTestData userTestData = new UserTestData();

        Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

        String gender = userInfo1TestData.get("gender");
        String dob = userInfo1TestData.get("dob");
        String height = userInfo1TestData.get("height");

//        System.out.println(gender + " " + dob + " " + height);

        TextView genderTextView = userInfo1.findViewById(R.id.genderTextView);
        genderTextView.setText(gender);

        TextView dobTextView = userInfo1.findViewById(R.id.birthdayTextView);
        dobTextView.setText(dob);

        TextView heightTextView = userInfo1.findViewById(R.id.heightTextView);
        heightTextView.setText(height);

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }

    @Test
    public void clickingContinueWithoutDOB_shouldPromptAlert() throws IOException, JSONException {

        UserTestData userTestData = new UserTestData();

        Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

        String gender = userInfo1TestData.get("gender");
        String height = userInfo1TestData.get("height");
        String weight = userInfo1TestData.get("weight");

        TextView genderTextView = userInfo1.findViewById(R.id.genderTextView);
        genderTextView.setText(gender);
        assertEquals("Gender Text View does not match user input", gender, genderTextView.getText().toString());

        TextView heightTextView = userInfo1.findViewById(R.id.heightTextView);
        heightTextView.setText(height);
        assertEquals("Height Text View does not match user input", height, heightTextView.getText().toString());

        TextView weightTextView = userInfo1.findViewById(R.id.weightTextView);
        weightTextView.setText(weight);
        assertEquals("Weight Text View does not match user input", weight, weightTextView.getText().toString());

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }

    @Test
    public void clickingContinueWithoutGender_shouldPromptAlert() throws IOException, JSONException {

        UserTestData userTestData = new UserTestData();

        Map<String, String> userInfo1TestData = userTestData.getUserInfo1TestData();

        String dob = userInfo1TestData.get("dob");
        String height = userInfo1TestData.get("height");
        String weight = userInfo1TestData.get("weight");

        TextView dobTextView = userInfo1.findViewById(R.id.birthdayTextView);
        dobTextView.setText(dob);
        assertEquals("Birthday Text View does not match user input", dob, dobTextView.getText().toString());

        TextView heightTextView = userInfo1.findViewById(R.id.heightTextView);
        heightTextView.setText(height);
        assertEquals("Height Text View does not match user input", height, heightTextView.getText().toString());

        TextView weightTextView = userInfo1.findViewById(R.id.weightTextView);
        weightTextView.setText(weight);
        assertEquals("Weight Text View does not match user input", weight, weightTextView.getText().toString());

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }


}