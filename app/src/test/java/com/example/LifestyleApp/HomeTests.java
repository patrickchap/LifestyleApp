package com.example.LifestyleApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import static org.robolectric.Shadows.shadowOf;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P) // Value of Build.VERSION_CODES.P is 28
public class HomeTests {

    private UserInfo3 userInfo3;
    private Home home;

    @Before
    public void setup() {

        userInfo3 = Robolectric.setupActivity(UserInfo3.class);
        userInfo3.findViewById(R.id.createButton).performClick();
        Intent homeIntent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        home =  Robolectric.buildActivity(Home.class, homeIntent).create().get();

    }

    @Test
    public void homeShouldNotBeNull() throws Exception {
        assertNotNull(home);
    }

    @Test
    public void masterListItemsCorrect() throws InstantiationException, IllegalAccessException {

        CustomMasterList customMasterList = new CustomMasterList();
        String bmi = Double.toString(home.getIntent().getDoubleExtra("bmi", 0));

        customMasterList.addItem("BMI", bmi);
        customMasterList.addItem("Weather", "Weather");
        customMasterList.addItem("Hikes near me", "Hikes");

        assertTrue("BMI missing from custom master list",
                "BMI".equals(customMasterList.getItemList().get(0).toString()));
        assertTrue("Weather missing from custom master list",
                "Weather".equals(customMasterList.getItemList().get(1).toString()));
        assertTrue("Hikes missing from custom master list",
                "Hikes near me".equals(customMasterList.getItemList().get(2).toString()));

    }

    @Test
    public void profilePictureNotNull() {

        assertNotNull(home.mUserProfilePicture);

    }

//    @Test
//    public void userInfo3PictureNotNull() {
//
//        assertNotNull(userInfo3.findViewById(R.id.userImage));
//
//    }

//    @Test
//    public void profilePictureMatchesUserImage() {
//
//        ImageView userInfo3ImageView = userInfo3.findViewById(R.id.userImage);
//
//        Bitmap bmp = ((BitmapDrawable)userInfo3ImageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

//        ImageView userInfo3ImageView = userInfo3.findViewById(R.id.userImage);
//
//        Bitmap userInfo3ImageBitmap = ((BitmapDrawable)userInfo3ImageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        userInfo3ImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        //byte[] userInfo3ImagebyteArray = stream.toByteArray();
//
//        ImageView homeImageView = home.findViewById(R.id.profilePictureIV);
//        Intent homeIntent = home.getIntent();
//
//        //get profile picture
//        byte[] homeImageByteArray = homeIntent.getByteArrayExtra("profilePicture");
//
//        Bitmap homeImageByteArrayDecoded = BitmapFactory.decodeByteArray(homeImageByteArray, 0, homeImageByteArray.length);
//
//        Bitmap homeImageBitmap = BitmapFactory.decodeByteArray(homeImageByteArray, 0, homeImageByteArray.length);


        //                Drawable drawable = mProfilePictureImageView.getDrawable();
        //intent.putExtra("profilePicture", byteArray);

       // ImageView userInfo3ImageView = userInfo3.findViewById(R.id.userImage);
//        userInfo3ImageView.invalidate();
//        BitmapDrawable userInfo3Drawable = (BitmapDrawable) userInfo3ImageView.getDrawable();
//        Bitmap userInfo3ImageBitmap = userInfo3Drawable.getBitmap();

//        homeImageView.invalidate();
//        BitmapDrawable homeImageDrawable = (BitmapDrawable) homeImageView.getDrawable();
//        Bitmap homeImageBitmap = homeImageDrawable.getBitmap();

//        assertEquals(shadowOf(homeImageView.getDrawable()).getCreatedFromResId(),
//                shadowOf(userInfo3ImageView.getDrawable()).getCreatedFromResId());

//        assertEquals(userInfo3ImagebyteArray,
//                homeImageByteArray);

//    }

}