package com.example.LifestyleApp.UserInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.LifestyleApp.R;
import java.io.IOException;
import java.io.Serializable;
import util.GetLocationUtil;

public class UserInfo2 extends AppCompatActivity implements View.OnClickListener{
    Button mContinueButton;
//    TextView mLocationTextView;
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
//        mWhoCanSeeTextView = findViewById(R.id.editTextWhoCanSee);
        mContinueButton.setOnClickListener(this);

        Location location = GetLocationUtil.getLocation(this);
        Address address = null;
        try {

            address = GetLocationUtil.getCity(location.getLatitude(), location.getLongitude(), getBaseContext());
            System.out.println("City " + address.getLocality() + " Country " + address.getCountryName());
            mCity.setText(address.getLocality());
            mCountry.setText(address.getCountryName());
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
