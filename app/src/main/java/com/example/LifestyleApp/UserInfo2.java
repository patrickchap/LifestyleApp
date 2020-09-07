package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        mLocationTextView = findViewById(R.id.editTextTextPostalAddress);
        mWhoCanSeeTextView = findViewById(R.id.editTextWhoCanSee);
        mContinueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String location = mLocationTextView.getText().toString();
                String whoCanSee = mWhoCanSeeTextView.getText().toString();
                       continueToUserInfo3(location, whoCanSee);
            }
        });
    }


    private void continueToUserInfo3(String location, String whoCanSee) {
        Intent intent = new Intent(this, UserInfo3.class);

        //TODO: pass all user information alon
        startActivity(intent);
    }
}
