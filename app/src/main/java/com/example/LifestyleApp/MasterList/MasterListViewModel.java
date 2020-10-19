package com.example.LifestyleApp.MasterList;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoRepository;

public class MasterListViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;
    private UserInfoRepository userInfoRepository;

    public MasterListViewModel(Application application) {
        super(application);
        userInfoRepository = UserInfoRepository.getInstance(this.getApplication().getApplicationContext());
        userData = userInfoRepository.getUserData();
        userInfoRepository.refreshData();
    }

    public MutableLiveData<UserData> getData(){
        return userData;
    }

}
