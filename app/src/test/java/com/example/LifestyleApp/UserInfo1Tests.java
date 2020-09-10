package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.junit.Test;

import Dialogs.DatePickerDialogMyTheme;
import Dialogs.GenderSpinnerDialog;
import Dialogs.HeightPickerDialog;
import Dialogs.WeightPickerDialog;

import static org.junit.Assert.*;


class userInfoInputTests {

    @Test
    public void testGenderInput() {

    }

    @Test
    public void testDOBInput() {

    }

    @Test
    public void testWeightInput() {

    }

    @Test
    public void testHeightInput() {

    }

}

class BMICalculationTests {

    Button mContinueButton;
    TextView mGenderTextView;
    TextView mDOB;
    TextView mWeight;
    //height is in inches
    TextView mHeight;

    @Test
    public void testBMICalculation() {

        String height = (String) mHeight.getText();
        int ft = Integer.parseInt(height.split(" ")[0]);
        int in = Integer.parseInt(height.split(" ")[2]);
        int heightInInches = (ft * 12) + in;

        String weight = (String) mWeight.getText();
        float fWeight = Float.parseFloat(weight.split(" ")[0]);
        //bmi Formula: 703 x weight (lbs) / [height (in)]2
        double bmi = ((703 * fWeight) / Math.pow(heightInInches,2));

    }

}