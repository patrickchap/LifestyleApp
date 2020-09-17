package com.example.LifestyleApp;

import android.os.Build;
import android.widget.Button;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P) // Value of Build.VERSION_CODES.P is 28
public class LoginTests {

    private Login login;

    @Before
    public void setup() {

        login = Robolectric.setupActivity(Login.class);

    }

    @Test
    public void loginShouldNotBeNull() throws Exception
    {
        assertNotNull( login );
    }

    @Test
    public void submitButtonCorrectText() {

        Button submitButton = (Button) login.findViewById(R.id.submitButton);

        assertTrue("Button contains incorrect text",
                "Submit".equals(submitButton.getText().toString()));

    }

    @Test
    public void signupButtonText() {

        Button signupButton = (Button) login.findViewById(R.id.signUpButton);

        assertTrue("Signup button contains incorrect text",
                "Sign Up".equals(signupButton.getText().toString()));
    }

}