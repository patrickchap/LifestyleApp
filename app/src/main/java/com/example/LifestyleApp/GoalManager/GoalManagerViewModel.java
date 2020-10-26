package com.example.LifestyleApp.GoalManager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoRepository;
import com.google.gson.Gson;

import org.json.JSONException;

public class GoalManagerViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;
    private UserInfoRepository userInfoRepository;

    public GoalManagerViewModel(Application application) {
        super(application);
        userInfoRepository = UserInfoRepository.getInstance(this.getApplication().getApplicationContext());
        userData = userInfoRepository.getUserData();
//        userInfoRepository.refreshData();
    }

    public LiveData<UserData> getUserData() {
//        userInfoRepository.removeObserver();
        return userData;
    }

    public void insertSetGoal(String goal) {
//        try {
//            userInfoRepository.insert("setGoal", goal);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }


    public void insert(int progress, float weight, int height, float goalWeight, boolean isGoalWeightSet,
                       String activityLevel, boolean isActivitySet, String goal,
                       boolean isGoalSet, int prog, float BMR, boolean isBMRSet, float calories, boolean isCaloriesSet) {

        GoalManagerViewModel.GoalInfo goalInfoObject = new GoalManagerViewModel.GoalInfo(progress, weight, height,
                goalWeight, isGoalWeightSet, activityLevel, isActivitySet, goal,
                isGoalSet, prog, BMR, isBMRSet, calories, isCaloriesSet);

        String userInputJson = new Gson().toJson(goalInfoObject);

//        try {
//            userInfoRepository.insert("userGoals_table", userInputJson);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private class GoalInfo {
        int progress;
        float weight;
        int height;
        float goalWeight;
        boolean isGoalWeightSet;
        String activityLevel;
        boolean isActivitySet;
        String goal;
        boolean isGoalSet;
        int prog;
        float BMR;
        boolean isBMRSet;
        float calories;
        boolean isCaloriesSet;

        private GoalInfo(int progress, float weight, int height, float goalWeight, boolean isGoalWeightSet,
                         String activityLevel, boolean isActivitySet, String goal,
                         boolean isGoalSet, int prog, float BMR, boolean isBMRSet, float calories, boolean isCaloriesSet) {

            this.progress = progress;
            this.weight = weight;
            this.height = height;
            this.goalWeight = goalWeight;
            this.isGoalWeightSet = isGoalWeightSet;
            this.activityLevel = activityLevel;
            this.isActivitySet = isActivitySet;
            this.goal = goal;
            this.isGoalSet = isGoalSet;
            this.prog = prog;
            this.BMR = BMR;
            this.isBMRSet = isBMRSet;
            this.calories = calories;
            this.isCaloriesSet = isCaloriesSet;
        }
    }

}
