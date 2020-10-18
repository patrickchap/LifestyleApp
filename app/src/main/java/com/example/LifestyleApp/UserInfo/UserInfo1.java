package com.example.LifestyleApp.UserInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.R;

import java.util.UUID;

import Dialogs.DatePickerDialogMyTheme;
import Dialogs.GenderSpinnerDialog;
import Dialogs.HeightPickerDialog;
import Dialogs.WeightPickerDialog;


public class UserInfo1 extends AppCompatActivity implements View.OnClickListener {

    private Button mContinueButton;
    private TextView mWeightTextView;
    private TextView mHeightTextView;
    private TextView mGenderTextView;
    private TextView mDOBTextView;

    private String mEmail;

    private UserInfo1ViewModel mUserInfo1ViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        System.out.println("CREATING USERINFO1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_1);

        mContinueButton = findViewById(R.id.continueButton);

        mHeightTextView = findViewById(R.id.heightTextView);
        mWeightTextView = findViewById(R.id.weightTextView);
        mGenderTextView = findViewById(R.id.genderTextView);
        mDOBTextView = findViewById(R.id.birthdayTextView);

        mContinueButton.setOnClickListener(this);

        mGenderTextView.setOnClickListener(this);
        mDOBTextView.setOnClickListener(this);
        mWeightTextView.setOnClickListener(this);
        mHeightTextView.setOnClickListener(this);

        mUserInfo1ViewModel = ViewModelProviders.of(this).get(UserInfo1ViewModel.class);

    }

//    final Observer<UserData> userInfo1Observer = new Observer<UserData>() {
//        @Override
//        public void onChanged(@Nullable final UserData userData) {
//            int height = userData.getHeight();
//            if (height != 0) {
////                user.setHeight(height);
//            }
//
//            float weight = userData.getWeight();
//            if (weight != 0) {
////                user.setWeight(weight);
//            }
//
//            double bmi = userData.getBmi();
//            if (bmi != 0) {
////                user.setBmi(bmi);
//            }
//
//            String gender = userData.getGender();
//            if (!gender.equals(null)) {
//                user.setGender(gender);
//            }
//
//            Long dob = userData.getDob();
//            if (dob != 0) {
//                user.setDOB(TypeConverters.fromTimestamp(dob));
//            }
//
//        }
//    };

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueButton: {

                mEmail = UUID.randomUUID().toString();

                mUserInfo1ViewModel.setViews(mEmail, mHeightTextView, mWeightTextView, mGenderTextView, mDOBTextView);
//                (mUserInfo1ViewModel.getData()).observe(this, userInfo1Observer);

                Intent intent = new Intent(this, UserInfo2.class);
                startActivity(intent);
                break;
            }

            case R.id.birthdayTextView: {
                DialogFragment dialogFragment = new DatePickerDialogMyTheme();
                dialogFragment.show(getSupportFragmentManager(), "myTheme");
                break;
            }
            case R.id.weightTextView: {
                DialogFragment dialogFragment = new WeightPickerDialog();
                dialogFragment.show(getSupportFragmentManager(), "WeightPicker");
                break;
            }
            case R.id.heightTextView: {
                DialogFragment dialogFragment = new HeightPickerDialog();
                dialogFragment.show(getSupportFragmentManager(), "HeightPicker");

                break;
            }

            case R.id.genderTextView: {
                DialogFragment dialogFragment = new GenderSpinnerDialog();
                dialogFragment.show(getSupportFragmentManager(), "GenderSpinner");
            }
        }
    }
}
