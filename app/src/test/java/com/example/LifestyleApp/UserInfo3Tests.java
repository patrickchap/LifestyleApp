package com.example.LifestyleApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;
import com.example.LifestyleApp.UserInfo.UserInfo3;
import org.json.JSONException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
public class UserInfo3Tests {

    private UserInfo3 userInfo3;
    private int numUsers = 1;

    @Before
    public void setup() {

        userInfo3 = Robolectric.setupActivity(UserInfo3.class);

    }

    @Test
    public void initialView_loginShouldNotBeNull() throws Exception
    {
        assertNotNull( userInfo3 );
    }

    @Test
    public void initalView_createButtonCorrectText() {

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
    public void clickingContinue_shouldContinueToHome() {

        Intent homeIntent = new Intent(userInfo3, Home.class);

        userInfo3.findViewById(R.id.createButton).performClick();
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(homeIntent.getComponent(), actual.getComponent());

    }

//    @Test
//    public void addingPhotoToIntent_testWithRandomPhoto() throws IOException, JSONException {
//
//       for (int i = 0; i < numUsers; i++) {
//
//            Bitmap randomPhotoBitmap = convertToBitmap(new UserTestData().getRandomPhoto());
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            randomPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] randomPhotoByteArray = stream.toByteArray();
//            Intent userInfo3Intent = userInfo3.getIntent();
//            userInfo3Intent.putExtra("profilePicture", randomPhotoByteArray);
//
//            byte[] byteArrayFromIntent = userInfo3Intent.getByteArrayExtra("profilePicture");
//
//            assertEquals(randomPhotoByteArray, byteArrayFromIntent);
//
//      }
//
//    }
//
//    Bitmap convertToBitmap(String photoFilePath) throws IOException {
//
//        HttpURLConnection connection = (HttpURLConnection) new URL(photoFilePath).openConnection();
//        connection.setDoInput(true);
//        connection.connect();
//        InputStream input = connection.getInputStream();
//
//        return BitmapFactory.decodeStream(input);
//
//    }

}