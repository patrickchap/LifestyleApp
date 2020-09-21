package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

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
                "Country".equals(textView2.getText().toString()));

    }

    @Test
    public void textView3CorrectText() {

        TextView textView3 = (TextView) userInfo2.findViewById(R.id.textView3);

        assertTrue("Text view 2 has incorrect text",
                "Who can see this?".equals(textView3.getText().toString()));

    }

    @Test
    public void userInfoClickDirectionsCorrectText() {

        TextView userInfoClickDirections = (TextView) userInfo2.findViewById(R.id.userInfoClickDirections);

        assertTrue("User info click directions has incorrect text",
                "Click each box to update".equals(userInfoClickDirections.getText().toString()));

    }

    @Test
    public void userInfo2CityCorrectText() {

        TextView textView = (TextView) userInfo2.findViewById(R.id.textView);

        assertTrue("User info click directions has incorrect text",
                "City".equals(textView.getText().toString()));

    }

    @Test
    public void userInputCorrect() {

        EditText country = userInfo2.findViewById(R.id.editTextCountry);

        country.setText("United States");

        assertTrue("Country Text View does not match user input",
                "United States".equals(country.getText().toString()));

        EditText whoCanSee = userInfo2.findViewById(R.id.editTextWhoCanSee);

        whoCanSee.setText("Me");

        assertTrue("Who can see Text View does not match user input",
                "Me".equals(whoCanSee.getText().toString()));

    }

    @Test
    public void clickingContinueWithUserInfoComplete_shouldContinueToUserInfo3() {
        TextView cityView = userInfo2.findViewById(R.id.editTextCity);
        TextView countryView = userInfo2.findViewById(R.id.editTextCountry);
        TextView whoSeesView = userInfo2.findViewById(R.id.editTextWhoCanSee);

        cityView.setText("Salt Lake City");
        countryView.setText("United States");
        whoSeesView.setText("Me");

        Intent userInfo3Intent = new Intent(userInfo2, UserInfo3.class);

        userInfo2.findViewById(R.id.continueButton).performClick();
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(userInfo3Intent.getComponent(), actual.getComponent());

    }

    @Test
    public void clickingContinueWithOutCity_shouldPromptAlert() {
        TextView countryView = userInfo2.findViewById(R.id.editTextCountry);
        TextView whoSeesView = userInfo2.findViewById(R.id.editTextWhoCanSee);

        countryView.setText("United States");
        whoSeesView.setText("Me");

        Intent userInfo3Intent = new Intent(userInfo2, UserInfo3.class);

        userInfo2.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());

    }

    @Test
    public void clickingContinueWithOutCountry_shouldPromptAlert() {
        TextView cityView = userInfo2.findViewById(R.id.editTextCity);
        TextView whoSeesView = userInfo2.findViewById(R.id.editTextWhoCanSee);

        cityView.setText("Salt Lake City");
        whoSeesView.setText("Me");

        userInfo2.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());

    }

    @Test
    public void clickingContinueWithOutWhoSees_shouldPromptAlert() {
        TextView cityView = userInfo2.findViewById(R.id.editTextCity);
        TextView countryView = userInfo2.findViewById(R.id.editTextCountry);

        cityView.setText("Salt Lake City");
        countryView.setText("United States");

        userInfo2.findViewById(R.id.continueButton).performClick();

        ShadowAlertDialog dialog = shadowOf(RuntimeEnvironment.application).getLatestAlertDialog();
        assertEquals("User Info Incomplete", dialog.getTitle());

    }

}