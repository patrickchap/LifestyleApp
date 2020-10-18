package com.example.LifestyleApp.MasterList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

//import com.example.LifestyleApp.GoalManager.GoalManagerFragment;
import com.example.LifestyleApp.ItemDetail.ItemDetailFragment;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.RvAdapter;
import com.example.LifestyleApp.UserInfo.User;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfo1ViewModel;
import com.example.LifestyleApp.Weather.WeatherFragment;

import util.GetIntentUtil;

public class MasterList extends AppCompatActivity implements RvAdapter.DataPasser {
    ImageView mUserProfilePicture;
    private MasterListViewModel masterListViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_list);
        masterListViewModel = ViewModelProviders.of(this).get(MasterListViewModel.class);

        masterListViewModel.loadData();

//        (masterListViewModel.getData()).observe(this, masterListObserver);

//        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
//        if(isTablet()){ // if tablet replace the left 1/3 of screen with master list fragment
//            fTrans.replace(R.id.fl_frag_masterlist_container_tablet, mMasterListFragment, "frag_masterlist_tab");
//        }else {// if it is a phone replace the whole screen with the master list fragment
//            fTrans.replace(R.id.master_list_phone, mMasterListFragment, "frag_masterlist");
//        }
//        fTrans.commit();


    }

    @Override
    public void passData(int position) {
//        String itemDetailString = mCustomMasterList.getItemDetail(position);
//        Bundle detailBundle = new Bundle();
//        detailBundle.putString("item_detail", itemDetailString);
//
//        if (isTablet()) {
//            if (itemDetailString.equals("Hikes")) {
//                Intent sendIntent = GetIntentUtil.getIntent(this, itemDetailString, detailBundle, user);
//                startActivity(sendIntent);
//            } else if (itemDetailString.equals("Goal")) {
//                mTabletFragement = new GoalManagerFragment();
//                Bundle bundle1 = new Bundle();
//                bundle1.putSerializable("user", user);
//                mTabletFragement.setArguments(bundle1);
//                FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
//                fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, mTabletFragement, "frag_itemdetail");
//                fTrans.commit();
//
//            } else if (itemDetailString.equals("Weather")) {
//                weatherFragment = new WeatherFragment();
//                weatherFragment.setArguments(getIntent().getExtras());
//                FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
//                fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, weatherFragment, "frag_weather");
//                fTrans.commit();
//            } else {
//                ItemDetailFragment mTabletItemFragmetn = new ItemDetailFragment();
//                mTabletItemFragmetn.setArguments(detailBundle);
//                FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
//                fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, mTabletItemFragmetn, "frag_itemdetail");
//                fTrans.commit();
//            }
//        } else {
//            //Phones we will create a new activity that will be replaced by fragments for the detail
//            Intent sendIntent = GetIntentUtil.getIntent(this, itemDetailString, detailBundle, user);
//            startActivity(sendIntent);
//        }
    }
//
//    boolean isTablet()
//    {
//        return getResources().getBoolean(R.bool.isTablet);
//    }
//
//    public void passGoalWeight(String data) {
//        mTabletFragement.sendGoalWeight(data);
//
//    }
//
//    public void passActivityLeve(String toString) {
//        mTabletFragement.sendActivity(toString);
//    }

//    final Observer<UserData> masterListObserver = new Observer<UserData>() {
//        @Override
//        public void onChanged(@Nullable final UserData userData) {

//            mUserProfilePicture = findViewById(R.id.profilePictureIV);
//            byte[] byteArray = masterListData.getProfilePicture();//intent.getByteArrayExtra("profilePicture");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            mUserProfilePicture.setImageBitmap(bmp);

//            String bmi = Double.toString(userData.getCurrentUser().getBmi());

            // add item name and detail to custom list. This will be used for the master detail flow to show modules
//            mCustomMasterList.addItem("BMI", bmi);
//            mCustomMasterList.addItem("Weather", "Weather");
//            mCustomMasterList.addItem("Hikes near me", "Hikes");
//
//            String goalsName = masterList.isAllGoalsSet() ? "Update Goal" : "Set Goal";
//            mCustomMasterList.addItem(goalsName, "Goal");

//        }
//    };

//    public void passResponse(String response) throws JSONException {
//        weatherFragment.getResponse(response);
//    }
}