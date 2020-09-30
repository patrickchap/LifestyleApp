package com.example.LifestyleApp.UserInfo;

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

import com.example.LifestyleApp.R;

import java.text.ParseException;
import java.util.Date;

import Dialogs.DatePickerDialogMyTheme;
import Dialogs.GenderSpinnerDialog;
import Dialogs.HeightPickerDialog;
import Dialogs.WeightPickerDialog;

import java.text.SimpleDateFormat;


public class UserInfo1 extends AppCompatActivity  implements View.OnClickListener{

    Button mContinueButton;
    TextView mGenderTextView;
    TextView mDOB;
    TextView mWeight;
    //height is in inches
    TextView mHeight;


    private String height;
    private String weight;
    private String dob;
    private String gender;

    User user;

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

    }


    private void continueToUserInfo2() throws ParseException {
        String height = (String) mHeight.getText();

        int ft = Integer.parseInt(height.split(" ")[0]);
        int in = Integer.parseInt(height.split(" ")[2]);
        int heightInInches = (ft * 12) + in;

        float fWeight = Float.parseFloat(weight.split(" ")[0]);

        //bmi Formula: 703 x weight (lbs) / [height (in)]2
        double bmi = ((703 * fWeight) / Math.pow(heightInInches,2));


        //Create user
        user = new User();
        user.setWeight(fWeight);
        user.setBmi(bmi);
        user.setHeight(heightInInches);
        Date dob=new SimpleDateFormat("dd/MM/yyyy").parse(mDOB.getText().toString());
        user.setDOB(dob);
        user.setGender(gender);



        Intent intent = new Intent(this, UserInfo2.class);

        intent.putExtra("user", user);

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

                    try {
                        continueToUserInfo2();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

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
                //  System.out.println("Show picker");
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
