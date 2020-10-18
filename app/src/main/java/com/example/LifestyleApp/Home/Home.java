package com.example.LifestyleApp.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.MasterList.MasterList;
import com.example.LifestyleApp.MasterList.MasterListViewModel;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.User;
import com.example.LifestyleApp.UserInfo.UserData;

public class Home extends AppCompatActivity implements View.OnClickListener{
    Button moduleBtn;
    ImageView mUserProfilePicture;
    TextView mBMI;
    TextView mWeight;
    TextView mHeight;
    TextView mGoalWeight;
    TextView mBMR;
    TextView mActivityLevel;
    TextView mCalories;

    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        moduleBtn = findViewById(R.id.moduleButton);
        moduleBtn.setOnClickListener(this);

        mUserProfilePicture = findViewById(R.id.profilePictureIV);

        mBMI = findViewById(R.id.bmiValue);
        mWeight = findViewById(R.id.weightValue);;
        mHeight = findViewById(R.id.heightValue);
        mGoalWeight = findViewById(R.id.goalWeightValue);
        mBMR = findViewById(R.id.bmrValue);
        mActivityLevel = findViewById(R.id.activityLevelValue);
        mCalories = findViewById(R.id.calories_value);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        (homeViewModel.getData()).observe(this,userInfoObserver);

        homeViewModel.loadData(mUserProfilePicture, mBMI, mHeight, mWeight, mGoalWeight, mBMR, mActivityLevel, mCalories);

    }

        final Observer<UserData> userInfoObserver = new Observer<UserData>() {
        @Override
        public void onChanged(@Nullable final UserData userData) {

            System.out.println("OBSERVING");

            String bmi = userData.getHeight() + "";
            if (!bmi.equals("")){
                mBMI.setText(bmi);
            }
            String weight = userData.getWeight() + "";
            if (!weight.equals("")){
                mBMI.setText(weight);
            }
            String height = userData.getHeight() + "";
            if (!height.equals("")){
                mBMI.setText(height);
            }
            String profilePicture = userData.getProfilePicture();
            if (!profilePicture.equals("")) {
                byte[] byteArray = profilePicture.getBytes();
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                mUserProfilePicture.setImageBitmap(bmp);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.moduleButton: {
                Intent intent = new Intent(this, MasterList.class);
                startActivity(intent);
            }
        }
    }

}