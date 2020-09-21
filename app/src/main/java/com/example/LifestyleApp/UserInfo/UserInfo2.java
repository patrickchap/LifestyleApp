package com.example.LifestyleApp.UserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.LifestyleApp.R;

import java.io.Serializable;

public class UserInfo2 extends AppCompatActivity implements View.OnClickListener{
    Button mContinueButton;
//    TextView mLocationTextView;
    TextView mCity;
    TextView mCountry;
    TextView mWhoCanSeeTextView;
    User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_2);
        System.out.println("get user bmi <<<<<<<,");

        user = (User) getIntent().getSerializableExtra("user");

        mContinueButton = findViewById(R.id.continueButton);
//        mLocationTextView = findViewById(R.id.editTextCountry);
        mCity = findViewById(R.id.editTextCity);
        mCountry = findViewById(R.id.editTextCountry);
        mWhoCanSeeTextView = findViewById(R.id.editTextWhoCanSee);
        mContinueButton.setOnClickListener(this);
    }


    private void continueToUserInfo3(String city, String country , String whoCanSee) {
//        Intent intentFromUserInfo1 = getIntent();
//        double bmi = intentFromUserInfo1.getDoubleExtra("bmi",0);


        Intent intent = new Intent(this, UserInfo3.class);
        user.setCity(city);
        user.setCountry(country);
        user.setWhoCanSee(whoCanSee);
        intent.putExtra("user", user);
//        intent.putExtra("city", city);
//        intent.putExtra("country", country);
//        intent.putExtra("whoCanSee", whoCanSee);
//        intent.putExtra("bmi", user.getBmi());
        //TODO: pass all user information along to UserInfo3
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continueButton: {
                String city = mCity.getText().toString();
                String country = mCountry.getText().toString();
                String whoCanSee = mWhoCanSeeTextView.getText().toString();



                //TODO: pass along information from userInfo1
                continueToUserInfo3(city, country, whoCanSee);
            }
        }
    }
}
