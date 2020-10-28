package com.example.LifestyleApp.StepCounter;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StepCounterUtility {

    private SensorManager mSensorManager;
    private TextView mStepsCount;
    private Sensor mStepCounter;
    private int steps;

    public void startStepCounter() {

    }

    public void stopStepCounter() {

    }

    public int getSteps() { return steps; }
}
