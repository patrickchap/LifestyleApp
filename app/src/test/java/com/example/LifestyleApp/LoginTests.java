package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import com.example.LifestyleApp.UserInfo.UserInfo1;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)

public class LoginTests {

    private Login login;

    @Before
    public void setup() {

        login = Robolectric.setupActivity(Login.class);

    }

    @Test
    public void loginShouldNotBeNull() throws Exception {
        assertNotNull(login);
    }

    @Test
    public void submitButtonCorrectText() {

        Button submitButton = (Button) login.findViewById(R.id.submitButton);

        assertTrue("Button contains incorrect text",
                "Submit".equals(submitButton.getText().toString()));

    }

    @Test
    public void signupButtonCorrectText() {

        Button signupButton = (Button) login.findViewById(R.id.signUpButton);

        assertTrue("Signup button contains incorrect text",
                "Sign Up".equals(signupButton.getText().toString()));
    }

    @Test
    public void userInputCorrect() {

        EditText inputEmail = (EditText) login.findViewById(R.id.editTextTextEmailAddress);
        EditText inputPassword = (EditText) login.findViewById(R.id.editTextTextPassword);

        inputEmail.setText("testEmail");
        inputPassword.setText("testPassword");

        assertEquals("Email should match user input", "testEmail",
                inputEmail.getText().toString());
        assertEquals("Password should match user input", "testPassword",
                inputPassword.getText().toString());
    }

    @Test
    public void clickingSignUp_shouldContinueToUserInfo() {
        login.findViewById(R.id.signUpButton).performClick();

        Intent expectedIntent = new Intent(login, UserInfo1.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

}