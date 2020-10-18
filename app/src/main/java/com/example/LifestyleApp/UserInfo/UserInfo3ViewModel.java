package com.example.LifestyleApp.UserInfo;

import android.app.Application;
import android.widget.ImageView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


public class UserInfo3ViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;
    private UserInfoRepository mUserInfoRepository;

    public UserInfo3ViewModel(Application application){
        super(application);
        mUserInfoRepository = new UserInfoRepository(application);
        userData = UserInfoRepository.getData();
    }

    public void setViews(String email, ImageView profilePictureImageView) {
        mUserInfoRepository.setUserInfo3Views(email, profilePictureImageView);
    }

}