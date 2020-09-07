package com.example.LifestyleApp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    ImageView mUserProfilePicture;
    float mBMI;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //TODO: create intent and get BMI from the current user
        //TODO: show BMI
        //TODO: Show users profile picture
    }
}
