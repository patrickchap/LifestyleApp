package com.example.LifestyleApp.StepCounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class StepCounterListener implements SensorEventListener {

    private int mSteps = 0;

    public int getSteps() { return mSteps; }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (mSteps == 0) {
            mSteps = (int)event.values[0];
        }
        if (mSteps > event.values[0]) {   // this should never happen
            return;
        }

        // publish the difference between last known step count and current count
        mSteps = (int)event.values[0] - mSteps;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}   // Ignore
}
