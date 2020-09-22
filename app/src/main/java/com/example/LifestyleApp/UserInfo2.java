package com.example.LifestyleApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfo2 extends AppCompatActivity implements View.OnClickListener{
    Button mContinueButton;
//    TextView mLocationTextView;
    TextView mCity;
    TextView mCountry;
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
//        mLocationTextView = findViewById(R.id.editTextCountry);
        mCity = findViewById(R.id.editTextCity);
        mCountry = findViewById(R.id.editTextCountry);
        mWhoCanSeeTextView = findViewById(R.id.editTextWhoCanSee);
        mContinueButton.setOnClickListener(this);
    }


    private void continueToUserInfo3(String city, String country , String whoCanSee) {
        Intent intentFromUserInfo1 = getIntent();
        double bmi = intentFromUserInfo1.getDoubleExtra("bmi",0);
        System.out.println("bmi from user1 in 2 " + bmi);

        Intent intent = new Intent(this, UserInfo3.class);
        intent.putExtra("city", city);
        intent.putExtra("country", country);
        intent.putExtra("whoCanSee", whoCanSee);
        intent.putExtra("bmi", bmi);
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

                if (!city.equals("") && !country.equals("")
                        && !whoCanSee.equals("")){

                    //TODO: pass along information from userInfo1
                    continueToUserInfo3(city, country, whoCanSee);
                }

                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(UserInfo2.this);
                    builder.setTitle("User Info Incomplete")
                            .setMessage("Please finish entering your information")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finishActivity(this.hashCode());
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }
        }
    }
}
