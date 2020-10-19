package com.example.LifestyleApp.UserInfo;

import android.app.Application;
import android.widget.TextView;
import androidx.lifecycle.AndroidViewModel;
import com.google.gson.Gson;
import org.json.JSONException;
import java.util.Date;

public class UserInfo1ViewModel extends AndroidViewModel {

    private UserInfoRepository userInfoRepository;

    public UserInfo1ViewModel(Application application){
        super(application);
        userInfoRepository = UserInfoRepository.getInstance(this.getApplication().getApplicationContext());
    }

    public void insert(TextView heightTextView, TextView weightTextView, TextView genderTextView, TextView dobTextView){

        if (heightTextView != null && weightTextView != null && genderTextView != null && dobTextView != null) {

            String height = heightTextView.getText().toString();
            String weight = weightTextView.getText().toString();
            String gender = genderTextView.getText().toString();
            String dob = dobTextView.getText().toString();

            if (!height.equals("Height") && !weight.equals("Weight") && !gender.equals("Gender") && !dob.equals("Birthday")) {

                int ft = Integer.parseInt(height.split(" ")[0]);
                int in = Integer.parseInt(height.split(" ")[2]);
                int heightInInches = (ft * 12) + in;

                float fWeight = Float.parseFloat(weight.split(" ")[0]);

                double bmi = ((703 * fWeight) / Math.pow(heightInInches, 2));

                Long dobDate = TypeConverters.dateToTimestamp(new Date(dob));

                UserInfoInput inputObject = new UserInfoInput(heightInInches, fWeight, bmi, gender, dobDate);

                String userInputJson = new Gson().toJson(inputObject);

                try {
                    userInfoRepository.insert("userInfo1", userInputJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class UserInfoInput {
        private int height;
        private float weight;
        private double bmi;
        private String gender;
        private Long dob;
        private String profilePicture;

        public UserInfoInput(int height, float weight, double bmi, String gender, Long dob) {
            this.height = height;
            this.weight = weight;
            this.bmi = bmi;
            this.gender = gender;
            this.dob = dob;
            this.profilePicture = "";
        }
    }
}