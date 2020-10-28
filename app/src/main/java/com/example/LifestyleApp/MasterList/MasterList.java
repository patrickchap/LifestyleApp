package com.example.LifestyleApp.MasterList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.GoalManager.GoalManagerFragment;
import com.example.LifestyleApp.ItemDetail.ItemDetailFragment;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoViewModel;
import com.example.LifestyleApp.Weather.WeatherFragment;
import com.example.LifestyleApp.R;

import util.GetIntentUtil;

public class MasterList extends AppCompatActivity implements RvAdapter.DataPasser {
    ImageView mUserProfilePicture;
    private MasterListFragment mMasterListFragment;
    private CustomMasterList mCustomMasterList = new CustomMasterList();
    private String mGoalWeight;
    private GoalManagerFragment mTabletFragement;
    private WeatherFragment weatherFragment;
    private UserInfoViewModel userInfoViewModel;
    private String bmi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_list);
        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        userInfoViewModel.loadUserData();
        userInfoViewModel.getUserData().observe(this, userInfoObserver);

        mCustomMasterList.addItem("Weather", "Weather");
        mCustomMasterList.addItem("Hikes near me", "Hikes");

//        String goalsName = user.isAllGoalsSet() ? "Update Goal" : "Set Goal";
        String goalsName ="Set Goal";
        mCustomMasterList.addItem(goalsName, "Goal");

        //create fragment that holds the master list and send the custom list
        mMasterListFragment = new MasterListFragment();
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putParcelable("item_list", mCustomMasterList);
        mMasterListFragment.setArguments(fragmentBundle);

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        if (isTablet()) { // if tablet replace the left 1/3 of screen with master list fragment
            fTrans.replace(R.id.fl_frag_masterlist_container_tablet, mMasterListFragment, "frag_masterlist_tab");
        } else {// if it is a phone replace the whole screen with the master list fragment
            fTrans.replace(R.id.master_list_phone, mMasterListFragment, "frag_masterlist");
        }
        fTrans.commit();
    }

    final Observer<UserData> userInfoObserver = new Observer<UserData>() {
        @Override
        public void onChanged(@Nullable final UserData userData) {
            if (userData != null) {

                mUserProfilePicture = findViewById(R.id.profilePictureIV);
                String profilePicture = userData.getUserData3().getProfilePicture();
                if (!profilePicture.equals("")) {
                    byte[] byteArray = Base64.decode(profilePicture, Base64.DEFAULT);
                    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    mUserProfilePicture.setImageBitmap(bmp);
                }
                bmi = userData.getUserData1().getBmi() + "";
                mCustomMasterList.addItem("BMI", bmi);
            }
        }
    };

    boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    @Override
    public void passData(int position) {
        String itemDetailString = mCustomMasterList.getItemDetail(position);
        Bundle detailBundle = new Bundle();
        detailBundle.putString("item_detail", itemDetailString);

        if (isTablet()) {
            if (itemDetailString.equals("Hikes")) {
                Intent sendIntent = GetIntentUtil.getIntent(this, itemDetailString, detailBundle);
                startActivity(sendIntent);
            } else if (itemDetailString.equals("Goal")) {
                mTabletFragement = new GoalManagerFragment();
                Bundle bundle1 = new Bundle();
                mTabletFragement.setArguments(bundle1);
                FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, mTabletFragement, "frag_itemdetail");
                fTrans.commit();

            } else if (itemDetailString.equals("Weather")) {
                weatherFragment = new WeatherFragment();
                weatherFragment.setArguments(getIntent().getExtras());
                FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, weatherFragment, "frag_weather");
                fTrans.commit();
            } else {
                ItemDetailFragment mTabletItemFragment = new ItemDetailFragment();
                mTabletItemFragment.setArguments(detailBundle);
                FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, mTabletItemFragment, "frag_itemdetail");
                fTrans.commit();
            }
        } else {
            //Phones we will create a new activity that will be replaced by fragments for the detail
            Intent sendIntent = GetIntentUtil.getIntent(this, itemDetailString, detailBundle);
            startActivity(sendIntent);
        }
    }

//    public void passGoalWeight(String data) {
//        mTabletFragement.sendGoalWeight(data);
//
//    }

//    public void passActivityLevel(String toString) {
//        mTabletFragement.sendActivity(toString);
//    }
//
//    public void passResponse(String response) throws JSONException {
//        weatherFragment.getResponse(response);
//    }
}
