package com.example.LifestyleApp.Home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoRepository;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;
    private UserInfoRepository userInfoRepository;

    public HomeViewModel(Application application) {
        super(application);
        userInfoRepository = UserInfoRepository.getInstance(this.getApplication().getApplicationContext());
        userData = userInfoRepository.getUserData();
//        userInfoRepository.refreshData();
    }

    public LiveData<UserData> getUserData(){
//        userInfoRepository.removeObserver();
        return userData;
    }

//    public void loadUserDataFromRepo() {
//        this.userData = userInfoRepository.getUserData();
//        userInfoRepository.getUserData().observeForever(userInfoObserver);
//        userInfoRepository.loadUserDataFromDB();
//        userInfoRepository.getUserData().removeObserver(userInfoObserver);
//    }

//    final Observer<UserData> userInfoObserver = new Observer<UserData>() {
//        @Override
//        public void onChanged(@Nullable final UserData newUserData) {
//            if (userData != null) {
//                userData.setValue(newUserData);
//            }
//        }
//    };
}
