package com.example.LifestyleApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import util.GetIntentUtil;


public class Home extends AppCompatActivity implements RvAdapter.DataPasser {
    ImageView mUserProfilePicture;
    private MasterListFragment mMasterListFragment;
    private CustomMasterList mCustomMasterList = new CustomMasterList();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mUserProfilePicture = findViewById(R.id.profilePictureIV);
        Intent intent = getIntent();
        //get profile picture
        byte[] byteArray = intent.getByteArrayExtra("profilePicture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mUserProfilePicture.setImageBitmap(bmp);

        String bmi = Double.toString(intent.getDoubleExtra("bmi", 0));

        // add item name and detail to custom list. This will be used for the master detail flow to show modules
        mCustomMasterList.addItem("BMI", bmi);
        mCustomMasterList.addItem("Weather", "Weather");
        mCustomMasterList.addItem("Hikes near me", "Hikes");

        //create fragrament that holds the master list and send the custom list
        mMasterListFragment = new MasterListFragment();
        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putParcelable("item_list",mCustomMasterList);
        mMasterListFragment.setArguments(fragmentBundle);

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        if(isTablet()){ // if tablet replace the left 1/3 of screen with master list fragment
        }else {// if it is a phone replace the whole screen with the master list fragment
            fTrans.replace(R.id.master_list_phone, mMasterListFragment, "frag_masterlist");
        }
        fTrans.commit();
    }


    boolean isTablet()
    {
        return getResources().getBoolean(R.bool.isTablet);
    }

    @Override
    public void passData(int position) {
        String itemDetailString = mCustomMasterList.getItemDetail(position);
        Bundle detailBundle = new Bundle();
        detailBundle.putString("item_detail",itemDetailString);

        if(isTablet()) {
//            ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
//            itemDetailFragment.setArguments(detailBundle);
//            FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
//            fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, itemDetailFragment, "frag_itemdetail");
//            fTrans.commit();
        }
        else{
            //Phones we will create a new activity that will be replaced by fragments for the detail
            Intent sendIntent = GetIntentUtil.getIntent(this, itemDetailString);
            sendIntent.putExtras(detailBundle);
            startActivity(sendIntent);
        }
    }
}
