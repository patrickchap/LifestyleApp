package com.example.LifestyleApp.UserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.LifestyleApp.R;
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

    private UserInfoViewModel userInfoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

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

        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueButton: {
                userInfoViewModel.insertUserInfo1(mHeightTextView, mWeightTextView, mGenderTextView, mDOBTextView);
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
