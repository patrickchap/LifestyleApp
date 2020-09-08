package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfo2 extends AppCompatActivity implements View.OnClickListener{
    Button mContinueButton;
    TextView mLocationTextView;
    TextView mWhoCanSeeTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_2);

        //TODO: create intent and get member variables from UserInfo1
        //TODO: get location and who can see this from user input
        //TODO: Location and who can see this are currently typed in from the user,
        // but we need to change them to onClick options with either drop downs or picker

        mContinueButton = findViewById(R.id.continueButton);
        mLocationTextView = findViewById(R.id.editTextTextPostalAddress);
        mWhoCanSeeTextView = findViewById(R.id.editTextWhoCanSee);
        mContinueButton.setOnClickListener(this);
    }


    private void continueToUserInfo3(String location, String whoCanSee) {
        Intent intent = new Intent(this, UserInfo3.class);
        intent.putExtra("location", location);
        intent.putExtra("whoCanSee", whoCanSee);
        //TODO: pass all user information along to UserInfo3
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continueButton: {
                String location = mLocationTextView.getText().toString();
                String whoCanSee = mWhoCanSeeTextView.getText().toString();
                //TODO: pass along information from userInfo1
                continueToUserInfo3(location, whoCanSee);
            }
        }
    }
}
