package com.example.LifestyleApp;

import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
public class UserInfo2Tests {

    private UserInfo2 userInfo2;

    @Before
    public void setup() {

        userInfo2 = Robolectric.setupActivity(UserInfo2.class);

    }

    @Test
    public void loginShouldNotBeNull() throws Exception
    {
        assertNotNull( userInfo2 );
    }

    @Test
    public void continueButtonCorrectText() {

        Button continueButton = (Button) userInfo2.findViewById(R.id.continueButton);

        assertTrue("Continue button contains incorrect text",
                "Continue".equals(continueButton.getText().toString()));

    }

    @Test
    public void imageViewCorrectDescription() {

        ImageView imageView = (ImageView) userInfo2.findViewById(R.id.appImageView2);

        assertTrue("Image view has incorrect content description",
                "Lifestyle app logo".equals(imageView.getContentDescription().toString()));

    }

    @Test
    public void textView2CorrectText() {

        TextView textView2 = (TextView) userInfo2.findViewById(R.id.textView2);

        assertTrue("Text view 2 has incorrect text",
                "User info 2 location".equals(textView2.getContentDescription().toString()));

    }

    @Test
    public void textView3CorrectText() {

        TextView textView3 = (TextView) userInfo2.findViewById(R.id.textView3);

        assertTrue("Text view 2 has incorrect text",
                "User info 3 who can see".equals(textView3.getContentDescription().toString()));

    }

    @Test
    public void userInfoClickDirectionsCorrectText() {

        TextView userInfoClickDirections = (TextView) userInfo2.findViewById(R.id.userInfoClickDirections);

        assertTrue("User info click directions has incorrect text",
                "User info directions".equals(userInfoClickDirections.getContentDescription().toString()));

    }

    @Test
    public void userInfo2CityCorrectText() {

        TextView textView = (TextView) userInfo2.findViewById(R.id.textView);

        assertTrue("User info click directions has incorrect text",
                "User info 2 city".equals(textView.getContentDescription().toString()));

    }

}