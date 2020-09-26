package com.example.LifestyleApp.GoalManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.User;

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
    private User user;

    private Button mLose;
    private Button mMaintain;
    private Button mGain;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goal_manager, container, false);

        user = (User) getArguments().getSerializable("user");
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

        mWeight.setText(user.getWeight() + " >");
        mHeight.setText(user.height + " >");

        mWeight.setOnClickListener(this);
        mHeight.setOnClickListener(this);
        mGoalWeight.setOnClickListener(this);
        mActivity.setOnClickListener(this);
        mLose.setOnClickListener(this);
        mMaintain.setOnClickListener(this);
        mGain.setOnClickListener(this);
        seekBar.setMax(5);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                goalTV.setText(""+ user.getGoal() + " " + progress + "lb per week");
                user.setPerWeekPounds(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loseBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mGain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                user.setGoal("lose");
                break;
            }
            case R.id.maintainBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                mGain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                user.setGoal("maintain");
                break;
            }
            case R.id.gainBtn: {
                mLose.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mMaintain.setBackgroundColor(getResources().getColor(R.color.button_gray));
                mGain.setBackgroundColor(getResources().getColor(R.color.continue_blue));
                user.setGoal("gain");
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
                dialogFragment.show(getFragmentManager(), "HeightPicker");
                break;
            }
            case R.id.goalWeightTextView: {
                DialogFragment dialogFragment = new GoalWeightPicker();
                dialogFragment.show(getFragmentManager(), "GoalWeightPicker");
                //goalweight dialog
                break;
            }
            case R.id.activityLevelTextView: {
                //activity level dialog box
                break;
            }

        }
    }


    public void sendGoalWeight(String goalWeight){
        mGoalWeight.setText(goalWeight);
    }

}
