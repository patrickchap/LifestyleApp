package com.example.LifestyleApp.UserInfo;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


public class UserInfo2ViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;
    private UserInfoRepository mUserInfoRepository;

    public UserInfo2ViewModel(Application application){
        super(application);
        mUserInfoRepository = new UserInfoRepository(application);
        userData = UserInfoRepository.getData();
    }

    public void setViews(String email,
                         TextView cityTextView,
                         TextView countryTextView) {
        mUserInfoRepository.setUserInfo2Views(email,
                cityTextView,
                countryTextView);
    }

    public MutableLiveData<UserData> getData(){
        return userData;
    }

}