package com.example.LifestyleApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;

import static org.junit.Assert.*;

class testBMI extends AppCompatActivity{

    TextView mBMI;

    @Test
    public void test() {

        Intent intent = getIntent();

        mBMI = findViewById(R.id.bmiTextView);

        String bmi = Double.toString(intent.getDoubleExtra("bmi", 0));

        mBMI.setText(bmi);

    }


}

class testProfilePicture extends AppCompatActivity {

    ImageView mUserProfilePicture;

    @Test
    public void test() {

        Intent intent = getIntent();

        mUserProfilePicture = findViewById(R.id.profilePictureIV);

        byte[] byteArray = intent.getByteArrayExtra("profilePicture");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        mUserProfilePicture.setImageBitmap(bmp);

    }
}
