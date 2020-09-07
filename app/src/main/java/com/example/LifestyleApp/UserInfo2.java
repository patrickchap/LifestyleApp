package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.LifestyleApp.R;

public class UserInfo2 extends AppCompatActivity {
    Button mContinueButton;
    TextView mLocationTextView;
    TextView mWhoCanSeeTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_2);

        //TODO: create intent and get member variables from UserInfo1
        //TODO: get location and who can see this from user input


        mContinueButton = findViewById(R.id.continueButton);
        mContinueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                continueToUserInfo3();
            }
        });
    }


    private void continueToUserInfo3() {
        Intent intent = new Intent(this, UserInfo3.class);
        //TODO: pass all user information along
        startActivity(intent);
    }
}
