package com.example.LifestyleApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import Dialogs.DatePickerDialogMyTheme;
import Dialogs.GenderSpinnerDialog;
import Dialogs.HeightPickerDialog;
import Dialogs.WeightPickerDialog;

public class UserInfo1 extends AppCompatActivity  implements View.OnClickListener{

    Button mContinueButton;
    TextView mGenderTextView;
    TextView mDOB;
    TextView mWeight;
    //height is in inches
    TextView mHeight;

    String height;
    String weight;
    String dob;
    String gender;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_1);

        mContinueButton = findViewById(R.id.continueButton);
        mWeight = findViewById(R.id.weightTextView);
        mDOB = findViewById(R.id.birthdayTextView);
        mHeight = findViewById(R.id.heightTextView);
        mGenderTextView = findViewById(R.id.genderTextView);

        mDOB.setOnClickListener(this);
        mContinueButton.setOnClickListener(this);
        mWeight.setOnClickListener(this);
        mHeight.setOnClickListener(this);
        mGenderTextView.setOnClickListener(this);

        //TODO: handle onClick for Gender
    }

    private void continueToUserInfo2() {

        int ft = Integer.parseInt(height.split(" ")[0]);
        int in = Integer.parseInt(height.split(" ")[2]);
        int heightInInches = (ft * 12) + in;

        float fWeight = Float.parseFloat(weight.split(" ")[0]);

        //bmi Formula: 703 x weight (lbs) / [height (in)]2
        double bmi = ((703 * fWeight) / Math.pow(heightInInches,2));

        Intent intent = new Intent(this, UserInfo2.class);
        intent.putExtra("bmi", bmi);
        //TODO: pass Gender(String), Weight(float), Height(int ... inches), and DOB(date) along

        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continueButton: {

                height = (String) mHeight.getText();
                weight = (String) mWeight.getText();
                dob = (String) mDOB.getText();
                gender = (String) mGenderTextView.getText();

                if (!height.equals("Height") && !weight.equals("Weight")
                && !dob.equals("Birthday") && !gender.equals("Gender")){

                    continueToUserInfo2();

                }

                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(UserInfo1.this);
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
                break;
            }
            case R.id.birthdayTextView: {
                System.out.println("Show picker");
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

