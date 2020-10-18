package com.example.LifestyleApp.MasterList;

import android.app.Application;
import android.os.Bundle;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

//import com.example.LifestyleApp.GoalManager.GoalManagerFragment;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoRepository;

public class MasterListViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;

    public MasterListViewModel(Application application) {
        super(application);
        userData = new UserInfoRepository(application).getData();
    }

    public void loadData(){
        CustomMasterList customMasterList = new CustomMasterList();


        String bmi = Double.toString(userData.getValue().getBmi());

        // add item name and detail to custom list. This will be used for the master detail flow to show modules
        customMasterList.addItem("BMI", bmi);


//        mCustomMasterList.addItem("Weather", "Weather");
//        mCustomMasterList.addItem("Hikes near me", "Hikes");

//        String goalsName = user.isAllGoalsSet() ? "Update Goal" : "Set Goal";
//        mCustomMasterList.addItem(goalsName, "Goal");

        //create fragrament that holds the master list and send the custom list


        MasterListFragment mMasterListFragment = new MasterListFragment();
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putParcelable("item_list",customMasterList);
        mMasterListFragment.setArguments(fragmentBundle);

    }

    public MutableLiveData<UserData> getData(){
        return userData;
    }

}
