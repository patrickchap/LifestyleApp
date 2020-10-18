package com.example.LifestyleApp.UserInfo;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


public class UserInfo1ViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;
    private UserInfoRepository mUserInfoRepository;

    public UserInfo1ViewModel(Application application){
        super(application);
        mUserInfoRepository = new UserInfoRepository(application);
        userData = UserInfoRepository.getData();
    }

    public void setViews(String email,
                         TextView heightTextView,
                         TextView weightTextView,
                         TextView genderTextView,
                         TextView dobTextView) {
        mUserInfoRepository.setUserInfo1Views(email,
                heightTextView,
                weightTextView,
                genderTextView,
                dobTextView);
    }

    public MutableLiveData<UserData> getData(){
        return userData;
    }

}