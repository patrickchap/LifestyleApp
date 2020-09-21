package com.example.LifestyleApp;

import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import com.example.LifestyleApp.UserInfo.UserInfo1;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
// Value of Build.VERSION_CODES.P is 28
public class UserInfo1Tests {

    private UserInfo1 userInfo1;

    @Before
    public void setup() {

        userInfo1 = Robolectric.setupActivity(UserInfo1.class);

    }

    @Test
    public void loginShouldNotBeNull() throws Exception {
        assertNotNull(userInfo1);
    }

    @Test
    public void continueButtonCorrectText() {

        Button continueButton = (Button) userInfo1.findViewById(R.id.continueButton);

        assertTrue("Continue button contains incorrect text",
                "Continue".equals(continueButton.getText().toString()));

    }

    @Test
    public void genderTextViewCorrectText() {

        TextView genderTextView = (TextView) userInfo1.findViewById(R.id.genderTextView);

        assertTrue("Gender Text View contains incorrect text",
                "Gender".equals(genderTextView.getText().toString()));
    }

    @Test
    public void dobTextViewCorrectText() {

        TextView dob = (TextView) userInfo1.findViewById(R.id.birthdayTextView);

        assertTrue("DOB Text View contains incorrect text",
                "Birthday".equals(dob.getText().toString()));
    }

    @Test
    public void weightTextViewCorrectText() {

        TextView weight = (TextView) userInfo1.findViewById(R.id.weightTextView);

        assertTrue("Weight Text View contains incorrect text",
                "Weight".equals(weight.getText().toString()));
    }

    @Test
    public void heightTextViewCorrectText() {

        TextView height = (TextView) userInfo1.findViewById(R.id.heightTextView);

        assertTrue("Weight Text View contains incorrect text",
                "Height".equals(height.getText().toString()));
    }

    @Test
    public void userInputCorrect() {

        TextView gender = userInfo1.findViewById(R.id.genderTextView);

        gender.setText("Male");
        assertTrue("Gender Text View does not match user input",
                "Male".equals(gender.getText().toString()));

        TextView birthday = userInfo1.findViewById(R.id.birthdayTextView);

        birthday.setText("9/19/2020");

        assertTrue("Birthday Text View does not match user input",
                "9/19/2020".equals(birthday.getText().toString()));

        TextView height = userInfo1.findViewById(R.id.heightTextView);

        height.setText("9 ft 0 in");

        assertTrue("Height Text View does not match user input",
                "9 ft 0 in".equals(height.getText().toString()));

        TextView weight = userInfo1.findViewById(R.id.weightTextView);

        weight.setText("900.0 lbs");

        assertTrue("Weight Text View does not match user input",
                "900.0 lbs".equals(weight.getText().toString()));

    }

}