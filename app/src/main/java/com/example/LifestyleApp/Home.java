package com.example.LifestyleApp;

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

import com.example.LifestyleApp.MasterList;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.User;

public class Home extends AppCompatActivity implements View.OnClickListener{
    Button moduleBtn;
    User user;
    ImageView mUserProfilePicture;
    TextView mBMI;
    TextView mWeight;
    TextView mHeight;
    TextView mGoalWeight;
    TextView mBMR;
    TextView mActivityLevel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        user = (User) getIntent().getSerializableExtra("user");

        moduleBtn = findViewById(R.id.moduleButton);
        moduleBtn.setOnClickListener(this);

        mBMI = findViewById(R.id.bmiValue);
        mBMI.setText(user.getBmi() + "");

        mWeight = findViewById(R.id.weightValue);
        mWeight.setText(user.getWeight() + "");

        mHeight = findViewById(R.id.heightValue);
        mHeight.setText(user.getHeight() + "");

        mGoalWeight = findViewById(R.id.goalWeightValue);
        mBMR = findViewById(R.id.bmrValue);
        mActivityLevel = findViewById(R.id.activityLevelValue);



        mUserProfilePicture = findViewById(R.id.profilePictureIV);
        byte[] byteArray = user.getProfilePicture();//intent.getByteArrayExtra("profilePicture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mUserProfilePicture.setImageBitmap(bmp);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.moduleButton: {
                Intent intent = new Intent(this, MasterList.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }
    }
}
