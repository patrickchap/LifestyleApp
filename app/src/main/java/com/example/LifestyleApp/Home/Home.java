package com.example.LifestyleApp.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.LifestyleApp.MasterList.MasterList;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.UserData;

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

    private HomeViewModel homeViewModel;

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
        ;
        mHeight = findViewById(R.id.heightValue);
        mGoalWeight = findViewById(R.id.goalWeightValue);
        mBMR = findViewById(R.id.bmrValue);
        mActivityLevel = findViewById(R.id.activityLevelValue);
        mCalories = findViewById(R.id.calories_value);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getUserData().observe(this, userInfoObserver);
    }

    final Observer<UserData> userInfoObserver = new Observer<UserData>() {
        @Override
        public void onChanged(@Nullable final UserData userData) {
            if (userData != null) {
                String bmi = userData.getUserData1().getBmi() + "";
                if (!bmi.equals("")) {
                    mWeight.setText(bmi);
                }
                String weight = userData.getUserData1().getWeight() + "";
                if (!weight.equals("")) {
                    mHeight.setText(weight);
                }
                String height = userData.getUserData1().getHeight() + "";
                if (!height.equals("")) {
                    mBMI.setText(height);
                }
//                String profilePicture = userData.getUserData3().getProfilePicture();
//                if (!profilePicture.equals("")) {
//                    byte[] byteArray = Base64.decode(profilePicture, Base64.DEFAULT);
//                    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                    mUserProfilePicture.setImageBitmap(bmp);
//                }
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
}