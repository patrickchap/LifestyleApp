package com.example.LifestyleApp.UserInfo;

import android.app.Application;
import android.util.Base64;
import androidx.lifecycle.AndroidViewModel;
import com.google.gson.Gson;
import org.json.JSONException;

public class UserInfo3ViewModel extends AndroidViewModel {

    private UserInfoRepository userInfoRepository;

    public UserInfo3ViewModel(Application application){
        super(application);
        userInfoRepository = UserInfoRepository.getInstance(this.getApplication().getApplicationContext());
    }

    public void insert(byte[] imageBytes) {
        if (imageBytes != null) {

            String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            UserInfoInput inputObject = new UserInfoInput(imageString);
            String userInputJson = new Gson().toJson(inputObject);

            try {
                userInfoRepository.insert("userInfo3", userInputJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private static class UserInfoInput {
        private String profilePicture;
        public UserInfoInput(String profilePicture) {
            this.profilePicture = profilePicture;
        }
    }

}