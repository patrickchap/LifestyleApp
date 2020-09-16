package com.example.LifestyleApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Home extends AppCompatActivity implements MyRVAdapter.DataPasser {
    ImageView mUserProfilePicture;
    TextView mBMI;
    private MasterListFragment mMasterListFragment;
    private CustomMasterList mCustomMasterList = new CustomMasterList();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mMasterListFragment = new MasterListFragment();


        mUserProfilePicture = findViewById(R.id.profilePictureIV);
        Intent intent = getIntent();


        //get profile picture
        byte[] byteArray = intent.getByteArrayExtra("profilePicture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        mUserProfilePicture.setImageBitmap(bmp);


        String bmi = Double.toString(intent.getDoubleExtra("bmi", 0));
        mCustomMasterList.addItem("BMI", bmi);

        //TODO: add actual weather
        mCustomMasterList.addItem("Weather", "current weather");
        //TODO: add actual hikes
        mCustomMasterList.addItem("Hikes near me", "Hikes");


        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putParcelable("item_list",mCustomMasterList);
        mMasterListFragment.setArguments(fragmentBundle);

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        if(isTablet()){
            System.out.println("Is tablet is true");
        }else {
            System.out.println("Is tablet is false");
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

        //Put this into a bundle
        Bundle detailBundle = new Bundle();
        detailBundle.putString("item_detail",itemDetailString);

        if(isTablet()) {

            ItemDetailFragment itemDetailFragment = new ItemDetailFragment();

            itemDetailFragment.setArguments(detailBundle);


            FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.fl_frag_itemdetail_container_tablet, itemDetailFragment, "frag_itemdetail");
            fTrans.commit();
        }
        else{
            Intent sendIntent = new Intent(this, ItemDetailActivity.class);
            sendIntent.putExtras(detailBundle);
            startActivity(sendIntent);
        }
    }
}
