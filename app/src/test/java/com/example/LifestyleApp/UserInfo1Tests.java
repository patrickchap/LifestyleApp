package com.example.LifestyleApp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import Dialogs.GenderSpinnerDialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

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

        assertEquals("Continue button contains incorrect text", "Continue", continueButton.getText().toString());

    }

    @Test
    public void genderTextViewCorrectText() {

        TextView genderTextView = (TextView) userInfo1.findViewById(R.id.genderTextView);

        assertEquals("Gender Text View contains incorrect text", "Gender", genderTextView.getText().toString());
    }

    @Test
    public void dobTextViewCorrectText() {

        TextView dob = (TextView) userInfo1.findViewById(R.id.birthdayTextView);

        assertEquals("DOB Text View contains incorrect text", "Birthday", dob.getText().toString());
    }

    @Test
    public void weightTextViewCorrectText() {

        TextView weight = (TextView) userInfo1.findViewById(R.id.weightTextView);

        assertEquals("Weight Text View contains incorrect text", "Weight", weight.getText().toString());
    }

    @Test
    public void heightTextViewCorrectText() {

        TextView height = (TextView) userInfo1.findViewById(R.id.heightTextView);

        assertEquals("Weight Text View contains incorrect text", "Height", height.getText().toString());
    }

    @Test
    public void userInputCorrect() {

        TextView gender = userInfo1.findViewById(R.id.genderTextView);

        gender.setText("Male");
        assertEquals("Gender Text View does not match user input", "Male", gender.getText().toString());

        TextView birthday = userInfo1.findViewById(R.id.birthdayTextView);

        birthday.setText("9/19/2020");

        assertEquals("Birthday Text View does not match user input", "9/19/2020", birthday.getText().toString());

        TextView height = userInfo1.findViewById(R.id.heightTextView);

        height.setText("9 ft 0 in");

        assertEquals("Height Text View does not match user input", "9 ft 0 in", height.getText().toString());

        TextView weight = userInfo1.findViewById(R.id.weightTextView);

        weight.setText("900.0 lbs");

        assertEquals("Weight Text View does not match user input", "900.0 lbs", weight.getText().toString());

    }

    @Test
    public void clickingContinueWithUserInfoComplete_shouldContinueToUserInfo2() {

        TextView height = userInfo1.findViewById(R.id.heightTextView);
        height.setText("9 ft 0 in");

        TextView weight = userInfo1.findViewById(R.id.weightTextView);
        weight.setText("900.0 lbs");

        TextView gender = userInfo1.findViewById(R.id.genderTextView);
        gender.setText("Male");

        TextView birthday = userInfo1.findViewById(R.id.birthdayTextView);
        birthday.setText("9/21/2020");

        Intent userInfo2Intent = new Intent(userInfo1, UserInfo2.class);

        userInfo1.findViewById(R.id.continueButton).performClick();
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(userInfo2Intent.getComponent(), actual.getComponent());
    }

    @Test
    public void clickingContinueWithoutHeight_shouldPromptAlert() {

        TextView weight = userInfo1.findViewById(R.id.weightTextView);
        weight.setText("900.0 lbs");

        TextView gender = userInfo1.findViewById(R.id.genderTextView);
        gender.setText("Male");

        TextView birthday = userInfo1.findViewById(R.id.birthdayTextView);
        birthday.setText("9/21/2020");

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }

    @Test
    public void clickingContinueWithoutWeight_shouldPromptAlert() {

        TextView height = userInfo1.findViewById(R.id.heightTextView);
        height.setText("9 ft 0 in");

        TextView gender = userInfo1.findViewById(R.id.genderTextView);
        gender.setText("Male");

        TextView birthday = userInfo1.findViewById(R.id.birthdayTextView);
        birthday.setText("9/21/2020");

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }

    @Test
    public void clickingContinueWithoutDOB_shouldPromptAlert() {

        TextView height = userInfo1.findViewById(R.id.heightTextView);
        height.setText("9 ft 0 in");

        TextView weight = userInfo1.findViewById(R.id.weightTextView);
        weight.setText("900.0 lbs");

        TextView gender = userInfo1.findViewById(R.id.genderTextView);
        gender.setText("Male");

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }

    @Test
    public void clickingContinueWithoutGender_shouldPromptAlert() {

        TextView height = userInfo1.findViewById(R.id.heightTextView);
        height.setText("9 ft 0 in");

        TextView weight = userInfo1.findViewById(R.id.weightTextView);
        weight.setText("900.0 lbs");

        TextView birthday = userInfo1.findViewById(R.id.birthdayTextView);
        birthday.setText("9/21/2020");

        userInfo1.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());
    }


}