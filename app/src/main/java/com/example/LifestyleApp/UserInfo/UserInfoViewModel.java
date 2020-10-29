package com.example.LifestyleApp.UserInfo;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.util.Base64;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.LifestyleApp.Tables.UserInfoTable;
import com.google.gson.Gson;

import org.json.JSONException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserInfoViewModel extends AndroidViewModel {
    private MutableLiveData<UserData> userData = new MutableLiveData<UserData>();
    private UserInfoRepository userInfoRepository;

    public UserInfoViewModel(Application application) {
        super(application);
        userInfoRepository = UserInfoRepository.getInstance(this.getApplication().getApplicationContext());
    }

    public void loadUserData() {
        userInfoRepository.refreshData();
        userData = userInfoRepository.getUserData();
        userInfoRepository.removeObserver();
    }

    public LiveData<UserData> getUserData() {
        return userData;
    }

    public void insertUserID(String userID) {
        userInfoRepository.createNewUser(userID);
    }

    public List<UserInfoTable> getUsersByUserName(String userName){
        return userInfoRepository.getUsersByUserName(userName);
    }

    //new
    public void insertUserInfo0(TextView userNameText, TextView userPasswordText){
        if (userNameText != null && userPasswordText != null) {
            String userName = userNameText.getText().toString();
            String userPassword = userPasswordText.getText().toString();
            userInfoRepository.insertPassword(userPassword);
            userInfoRepository.insertUserName(userName);

        }

    }


    public void insertUserInfo1(TextView heightTextView, TextView weightTextView, TextView genderTextView, TextView dobTextView) {

        if (heightTextView != null && weightTextView != null && genderTextView != null && dobTextView != null) {

            String height = heightTextView.getText().toString();
            String weight = weightTextView.getText().toString();
            String gender = genderTextView.getText().toString();
            String dob = dobTextView.getText().toString();

            int heightInInches = 0;
            float fWeight = 0;

            if (!height.equals("Height")) {

                int ft = Integer.parseInt(height.split(" ")[0]);
                int in = Integer.parseInt(height.split(" ")[2]);
                heightInInches = (ft * 12) + in;

                try {
                    userInfoRepository.insertUserHeight(heightInInches);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (!weight.equals("Weight")) {

                fWeight = Float.parseFloat(weight.split(" ")[0]);

                try {
                    userInfoRepository.insertUserWeight(fWeight);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (!gender.equals("Gender")) {

                try {
                    userInfoRepository.insertUserGender(gender);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (!dob.equals("Birthday")) {

                Long dobDate = TypeConverters.dateToTimestamp(new Date(dob));

                try {
                    userInfoRepository.insertUserDob(dobDate);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (heightInInches != 0 && fWeight != 0) {

                double bmi = ((703 * fWeight) / Math.pow(heightInInches, 2));

                try {
                    userInfoRepository.insertBMI(bmi);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertUserInfo3(byte[] imageBytes) {
        if (imageBytes != null) {
            String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            userInfoRepository.insertProfilePicture(imageString);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertGoalInfo(int prog,
                               String goal,
                               float weight,
                               int height,
                               TextView goalWeightTextView,
                               TextView activityTextView, Date userDOB, String gender) {

        if (!goal.equals(null)) {
            userInfoRepository.insertGoal(goal);
            if (prog != 0) {
                if(!goal.equals("maintain")) {
                        userInfoRepository.insertPerWeekPounds(prog);
                }


            }
        String goalWeightText = goalWeightTextView.getText().toString();
        float goalWeight;
        if (!goalWeightText.equals("N/A >")) {
            goalWeight = Float.parseFloat(goalWeightText.split(" ")[0]);
                userInfoRepository.insertGoalWeight(goalWeight);
        }

        String activity = activityTextView.getText().toString();
        if (!activity.equals("N/A >")) {
                userInfoRepository.insertActivity(activity);
        }
        float BMR = 0;
        if (userDOB != null) {
            Period period = Period.between(userDOB.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate(), LocalDate.now());
            if (!gender.equals(null)) {
                if (gender.equals("Male")) {
                    //        men 66.47 + (6.24 * weight) + (12.7 * height) - (6.755 * age in years)
                    BMR = (float) (66.47 + (6.24 * weight) + (12.7 * height) - (6.755 * period.getYears()));
                } else {
                    //        women 655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age in years)
                    BMR = (float) (655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * period.getYears()));
                }
                //         sedentary = bmr * 1.2
                //         active = bmr * 1.5
                BMR = (activity == "Active") ? (BMR *= 1.5) : (BMR *= 1.2);
                if (BMR != 0) {
                    userInfoRepository.insertBmr(BMR);
                }
            }
        }
            //calculate calories
            int diff = prog * 500;
            float calories = BMR;

            if (goal.equals("gain")) {
                calories += diff;
            } else if (goal.equals("lose")) {
                calories -= diff;
            }

        if (calories != 0) {
                userInfoRepository.insertCalories(calories);
        }
        }
    }



    public void setSteps(int mSteps) {
        if(mSteps != 0){
            userInfoRepository.insertSteps(mSteps);
        }

    }
}
