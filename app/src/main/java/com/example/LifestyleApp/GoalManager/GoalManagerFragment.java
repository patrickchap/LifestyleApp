package com.example.LifestyleApp.GoalManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.Home;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.TypeConverters;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoViewModel;

import java.time.Period;
import java.util.Date;

import Dialogs.ActivityLevelPicker;
import Dialogs.GoalWeightPicker;
import Dialogs.HeightPickerDialog;
import Dialogs.WeightPickerDialog;

public class GoalManagerFragment extends Fragment implements View.OnClickListener {

    private SeekBar seekBar;
    private TextView goalTV;
    private TextView mWeight;
    private TextView mHeight;
    private TextView mGoalWeight;
    private TextView mActivity;

    private Button mLose;
    private Button mMaintain;
    private Button mGain;
    private Button mSave;

    private float weight;
    private int height;
    private Date dob;
    private String gender;

    private String goal = "";
    private int prog = 0;

    private UserInfoViewModel userInfoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goal_manager, container, false);

        mWeight = view.findViewById(R.id.weightTextView);
        seekBar = view.findViewById(R.id.seekBar);
        goalTV = view.findViewById(R.id.goalTextView);
        mHeight = view.findViewById(R.id.heightTextView);
        mGoalWeight = view.findViewById(R.id.goalWeightTextView);
        mActivity = view.findViewById(R.id.activityLevelTextView);
        mLose = view.findViewById(R.id.loseBtn);
        mMaintain = view.findViewById(R.id.maintainBtn);
        mGain = view.findViewById(R.id.gainBtn);
        mGoalWeight = view.findViewById(R.id.goalWeightTextView);
        mActivity = view.findViewById(R.id.activityLevelTextView);
        mSave = view.findViewById(R.id.saveGoalButton);

        mWeight.setOnClickListener(this);
        mHeight.setOnClickListener(this);
        mGoalWeight.setOnClickListener(this);
        mActivity.setOnClickListener(this);
        mLose.setOnClickListener(this);
        mMaintain.setOnClickListener(this);
        mGain.setOnClickListener(this);
        mSave.setOnClickListener(this);
        seekBar.setMax(5);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(goal != "maintain"){
                    prog = progress;
                    goalTV.setText(goal + " " + prog + "lb per week");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        userInfoViewModel.loadUserData();
        userInfoViewModel.getUserData().observe(this, goalManagerObserver);

        return view;
    }

    final Observer<UserData> goalManagerObserver = new Observer<UserData>() {
        @Override
        public void onChanged(@Nullable final UserData userData) {
            weight = userData.getUserData1().getWeight();
            mWeight.setText(weight + " lbs >");
            height = userData.getUserData1().getHeight();
            int ft = height/12;
            int in = height%12;
            mHeight.setText(ft + " ft " + in + " in" + " >");
            dob = TypeConverters.fromTimestamp(userData.getUserData1().getDob());
            gender = userData.getUserData1().getGender();
            float goalWeight = userData.getUserGoals().getGoalWeight();
            if(goalWeight != 0){
                mGoalWeight.setText(goalWeight + " >");
            }
            String activityLevel  = userData.getUserGoals().getActivity();
            if(!activityLevel.equals("")){
                mActivity.setText(activityLevel + " >");
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loseBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mGain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                goal = "lose";
                goalTV.setText(goal + " " + prog + "lb per week");

//                goalManagerViewModel.insertSetGoal("lose");
                //user.setGoal("lose");
                break;
            }
            case R.id.maintainBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                mGain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                goal = "maintain";
                goalTV.setText(goal + " weight");

//                goalManagerViewModel.insertSetGoal("maintain");
                //user.setGoal("maintain");
                break;
            }
            case R.id.gainBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mGain.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                goal = "gain";
                goalTV.setText(goal + " " + prog + "lb per week");

//                goalManagerViewModel.insertSetGoal("gain");
                //user.setGoal("gain");
                break;
            }
            case R.id.weightTextView: {
                DialogFragment dialogFragment = new WeightPickerDialog();
                assert getFragmentManager() != null;
                dialogFragment.show(getFragmentManager(), "WeightPicker");
                break;
            }
            case R.id.heightTextView: {
                DialogFragment dialogFragment = new HeightPickerDialog();
                assert getFragmentManager() != null;
                dialogFragment.show(getFragmentManager(), "HeightPicker");
                break;
            }
            case R.id.goalWeightTextView: {
                DialogFragment dialogFragment = new GoalWeightPicker();
                assert getFragmentManager() != null;
                dialogFragment.show(getFragmentManager(), "GoalWeightPicker");
                //goalweight dialog
                break;
            }
            case R.id.activityLevelTextView: {
                DialogFragment dialogFragment = new ActivityLevelPicker();
                dialogFragment.show(getFragmentManager(), "ActivityLevelPicker");
                //activity level dialog box
                break;
            }
            case R.id.saveGoalButton: {
//                userInfoViewModel.insertGoalInfo(prog, fGWeight, heightInInches,
//                        fGWeight, true, activityLevel, true, goal,
//                        true, prog, BMR, true, calories, true);

                userInfoViewModel.insertGoalInfo(prog, goal, weight, height, mGoalWeight, mActivity, dob, gender);
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
                break;
            }
        }
    }

//    public void sendGoalWeight(String goalWeight){
//        mGoalWeight.setText(goalWeight);
//    }
//    public void sendActivity(String activityLevel){
//        mActivity.setText(activityLevel);
//    }

}