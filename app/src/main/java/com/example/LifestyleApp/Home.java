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

public class Home extends AppCompatActivity {
    ImageView mUserProfilePicture;
    TextView mBMI;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mBMI = findViewById(R.id.bmiTextView);
        mUserProfilePicture = findViewById(R.id.profilePictureIV);

        Intent intent = getIntent();
        String bmi = Double.toString(intent.getDoubleExtra("bmi", 0));
        byte[] byteArray = intent.getByteArrayExtra("profilePicture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mUserProfilePicture.setImageBitmap(bmp);
        mBMI.setText(bmi);

    }
}
