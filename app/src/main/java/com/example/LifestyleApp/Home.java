package com.example.LifestyleApp;

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

import androidx.annotation.NonNull;
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
    TextView mCalories;


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

        if(user.isGoalWeightSet()){
            mGoalWeight.setText(user.getGoalWeight() + "");
        }
        mBMR = findViewById(R.id.bmrValue);
        if(user.isBMRSet()){
            mBMR.setText(user.getBMR() +"");
        }
        mActivityLevel = findViewById(R.id.activityLevelValue);
        if(user.isActivitySet()){
            mActivityLevel.setText(user.getActivity() +"");
        }

        mCalories = findViewById(R.id.calories_value);
        if(user.isCaloriesSet()){
            if(user.getGender().equals("Male") && user.getCalories() < 1200){
                //alert for calories too low
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Calories are too low")
                        .setMessage("Based on your goals, your calories will be below 1,200")
                        .setCancelable(false)
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 finishActivity(this.hashCode());
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }else if(user.getGender().equals("Female") && user.getCalories() < 1000 ){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Calories are too low")
                        .setMessage("Based on your goals, your calories will be below 1,000")
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
            mCalories.setText(user.getCalories() + "");
        }

        if(user.getPerWeekPounds() > 2){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("High goal")
                    .setMessage("Your goal to " + user.getGoal() + " " + user.getPerWeekPounds() + "lbs per week is more than the recommended 2lbs per week")
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

    public void getUser(User user){
        System.out.println(user.getGoalWeight() + " getUser >>>>>");
    }
}
