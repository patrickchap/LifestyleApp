package com.example.LifestyleApp.Home;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoRepository;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<UserData> userData;

    public HomeViewModel(Application application) {
        super(application);

        userData = new UserInfoRepository(application).getData();
    }

    public void loadData(ImageView mUserProfilePicture, TextView mBMI, TextView mHeight, TextView mWeight, TextView mGoalWeight, TextView mBMR, TextView mActivityLevel, TextView mCalories) {

        System.out.println("LOADING DATA");
        if (userData.getValue() != null) {
            double bmi = userData.getValue().getBmi();
            if (bmi != 0) {
                mBMI.setText(bmi + "");
            }

            float weight = userData.getValue().getWeight();
            if (weight != 0) {
                mWeight.setText(weight + "");
            }
            int height = userData.getValue().getHeight();
            if (height != 0) {
                mHeight.setText(height + "");
            }

            String profilePicture = userData.getValue().getProfilePicture();
            if (!profilePicture.equals("")) {
                byte[] byteArray = profilePicture.getBytes();
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                mUserProfilePicture.setImageBitmap(bmp);
            }
        }
    }
//        if(user.isGoalWeightSet()){
//            mGoalWeight.setText(user.getGoalWeight() + "");
//        }
//
//        if(user.isBMRSet()){
//            mBMR.setText(user.getBMR() +"");
//        }

//        if(user.isActivitySet()){
//            mActivityLevel.setText(user.getActivity() +"");
//        }

//        if(user.isCaloriesSet()){
//            if(user.getGender().equals("Male") && user.getCalories() < 1200){
//                //alert for calories too low
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Calories are too low")
//                        .setMessage("Based on your goals, your calories will be below 1,200")
//                        .setCancelable(false)
//                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finishActivity(this.hashCode());
//                            }
//                        });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }else if(user.getGender().equals("Female") && user.getCalories() < 1000 ){
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Calories are too low")
//                        .setMessage("Based on your goals, your calories will be below 1,000")
//                        .setCancelable(false)
//                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finishActivity(this.hashCode());
//                            }
//                        });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            }
//            mCalories.setText(user.getCalories() + "");
//        }

//        if(user.getPerWeekPounds() > 2){
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("High goal")
//                    .setMessage("Your goal to " + user.getGoal() + " " + user.getPerWeekPounds() + "lbs per week is more than the recommended 2lbs per week")
//                    .setCancelable(false)
//                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finishActivity(this.hashCode());
//                        }
//                    });
//
//            AlertDialog dialog = builder.create();
//            dialog.show();
//        }

//    }


    public MutableLiveData<UserData> getData() {
        return userData;
    }

}
