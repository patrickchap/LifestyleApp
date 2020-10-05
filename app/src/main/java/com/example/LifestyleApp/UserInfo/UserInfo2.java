package com.example.LifestyleApp.UserInfo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.LifestyleApp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;

import util.GetLocationUtil;

public class UserInfo2 extends AppCompatActivity implements View.OnClickListener{
    Button mContinueButton;
    TextView mCity;
    TextView mCountry;
//    TextView mWhoCanSeeTextView;
    User user;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_2);
        user = (User) getIntent().getSerializableExtra("user");
        mContinueButton = findViewById(R.id.continueButton);
        mCity = findViewById(R.id.editTextCity);
        mCountry = findViewById(R.id.editTextCountry);
        mContinueButton.setOnClickListener(this);

        Location location = GetLocationUtil.getLocation(this);


        Address address = null;
        try {
            if(location != null){
                address = GetLocationUtil.getAddress(location.getLatitude(), location.getLongitude(), getBaseContext());
                // System.out.println("City " + address.getLocality() + " Country " + address.getCountryName());
                mCity.setText(address.getLocality());
                mCountry.setText(address.getCountryName());
            }else{
                throw new IOException("Location is null");
            }

        } catch (IOException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserInfo2.this);
            builder.setTitle("Cannot find location")
                    .setMessage("Please enter your City and Country")
                    .setCancelable(false)
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishActivity(this.hashCode());
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
            e.printStackTrace();
        }
    }

    private void continueToUserInfo3(String city, String country) {

        Intent intent = new Intent(this, UserInfo3.class);
        user.setCity(city);
        user.setCountry(country);
//        user.setWhoCanSee(whoCanSee);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continueButton: {
                String city = mCity.getText().toString();
                String country = mCountry.getText().toString();
//                String whoCanSee = mWhoCanSeeTextView.getText().toString();

                if (!city.equals("") && !country.equals("")){
                    continueToUserInfo3(city, country);
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
