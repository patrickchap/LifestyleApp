package com.example.LifestyleApp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.MasterList.MasterList;
import com.example.LifestyleApp.StepCounter.OnSwipeTouchListener;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoViewModel;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button moduleBtn;
    ImageView mUserProfilePicture;
    TextView mBMI;
    TextView mWeight;
    TextView mHeight;
    TextView mGoalWeight;
    TextView mBMR;
    TextView mActivityLevel;
    TextView mCalories;
    TextView mStepsNum;
    ConstraintLayout mStepCounterLayout;

    private UserInfoViewModel userInfoViewModel;

    private boolean stepCounterIsActive = false;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        moduleBtn = findViewById(R.id.moduleButton);
        moduleBtn.setOnClickListener(this);

        mUserProfilePicture = findViewById(R.id.profilePictureIV);

        mBMI = findViewById(R.id.bmiValue);
        mWeight = findViewById(R.id.weightValue);
        mHeight = findViewById(R.id.heightValue);
        mGoalWeight = findViewById(R.id.goalWeightValue);
        mBMR = findViewById(R.id.bmrValue);
        mActivityLevel = findViewById(R.id.activityLevelValue);
        mCalories = findViewById(R.id.calories_value);
        mStepsNum = findViewById(R.id.stepsNum);

        mStepCounterLayout = findViewById(R.id.stepCounterLayout);
        Context context = getApplicationContext();
        CharSequence swipeLeftText = "Stopping step counter!";
        CharSequence swipeRightText = "Starting step counter!";
        int duration = Toast.LENGTH_SHORT;
        final MediaPlayer swipeLeftMP = MediaPlayer.create(this, R.raw.ui_quirky11);
        final MediaPlayer swipeRightMP = MediaPlayer.create(this, R.raw.ui_quirky12);

        mStepCounterLayout.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeLeft() {
                Toast toast = Toast.makeText(context, swipeLeftText, duration);
                swipeLeftMP.start();
                toast.show();
            }

            @Override
            public void onSwipeRight() {
                Toast toast = Toast.makeText(context, swipeRightText, duration);
                swipeRightMP.start();
                toast.show();
            }
        });


        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        userInfoViewModel.loadUserData();
        userInfoViewModel.getUserData().observe(this, userInfoObserver);
    }

    final Observer<UserData> userInfoObserver = new Observer<UserData>() {
        @Override
        public void onChanged(@Nullable final UserData userData) {
            if (userData != null) {
                String bmi = userData.getUserData1().getBmi() + "";
                if (!bmi.equals("")) {
                    mBMI.setText(bmi);
                }
                String weight = userData.getUserData1().getWeight() + "";
                if (!weight.equals("")) {
                    mWeight.setText(weight);
                }
                String height = userData.getUserData1().getHeight() + "";
                if (!height.equals("")) {
                    mHeight.setText(height);
                }
                String profilePicture = userData.getUserData3().getProfilePicture();
                if (!profilePicture.equals("")) {
                    byte[] byteArray = Base64.decode(profilePicture, Base64.DEFAULT);
                    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    mUserProfilePicture.setImageBitmap(bmp);
                }
                float goalWeight = userData.getUserGoals().getGoalWeight();
                if (goalWeight != 0) {
                    mGoalWeight.setText(""+goalWeight);
                }
                String activity = userData.getUserGoals().getActivity();
                if (activity != "") {
                    mActivityLevel.setText(activity);
                }
                float bmr = userData.getUserGoals().getBmr();
                if(bmr != 0){
                    mBMR.setText(""+bmr);
                }
                float calories = userData.getUserGoals().getCalories();
                if(calories != 0) {
                    mCalories.setText("" + calories);
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.moduleButton: {
                Intent intent = new Intent(this, MasterList.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}