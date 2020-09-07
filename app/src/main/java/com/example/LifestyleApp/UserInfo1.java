package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.LifestyleApp.R;

import java.util.Date;

public class UserInfo1 extends AppCompatActivity {

    Button mContinueButton;
    TextView mGenderTextView;
    Date mDOB;
    int mWeight;
    //height is in inches
    int mHeight;
    //bmi Formula: 703 x weight (lbs) / [height (in)]2
    float mBMI;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_1);

        mContinueButton = findViewById(R.id.continueButton);
        mContinueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                continueToUserInfo2();
            }
        });

        //TODO: take input from the user and calculate BMI
    }

    private void continueToUserInfo2() {
        Intent intent = new Intent(this, UserInfo2.class);
        //TODO: pass member variables along
        startActivity(intent);
    }
}
