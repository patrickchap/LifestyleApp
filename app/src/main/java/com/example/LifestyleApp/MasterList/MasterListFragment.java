package com.example.LifestyleApp.MasterList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.LifestyleApp.R;

import java.util.List;

public class MasterListFragment extends Fragment {
    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.masterlist_layout, container, false);
        mRecyclerView = fragmentView.findViewById(R.id.rv_master);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        CustomMasterList customListData = getArguments().getParcelable("item_list");
        List<String> inputList = customListData.getItemList();

        mAdapter = new RvAdapter(inputList);
        mRecyclerView.setAdapter(mAdapter);

        return fragmentView;
    }
}

//package com.example.LifestyleApp.MasterList;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.util.Base64;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.LifestyleApp.R;
//import com.example.LifestyleApp.MasterList.RvAdapter;
//import com.example.LifestyleApp.UserInfo.UserData;
//
//import java.util.List;
//
//public class MasterListFragment extends Fragment {
//    ImageView mUserProfilePicture;
//    RecyclerView mRecyclerView;
//    LinearLayoutManager layoutManager;
//    private MasterListViewModel masterListViewModel;
//    private RecyclerView.Adapter mAdapter;
//    private CustomMasterList mCustomListData;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View fragmentView = inflater.inflate(R.layout.masterlist_layout, container, false);
//        mRecyclerView = fragmentView.findViewById(R.id.rv_master);
//        mRecyclerView.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(layoutManager);
//
//        mCustomListData = getArguments().getParcelable("item_list");
//        List<String> inputList = mCustomListData.getItemList();
//
//        mAdapter = new RvAdapter(inputList);
//        mRecyclerView.setAdapter(mAdapter);
//
//        mUserProfilePicture = fragmentView.findViewById(R.id.profilePictureIV);
//
//        masterListViewModel = ViewModelProviders.of(this).get(MasterListViewModel.class);
//        (masterListViewModel.getData()).observe(this, masterListObserver);
//
//        return fragmentView;
//    }
//
//    final Observer<UserData> masterListObserver = new Observer<UserData>() {
//        @Override
//        public void onChanged(@Nullable final UserData userData) {
//
//            CustomMasterList customMasterList = new CustomMasterList();
//
//            byte[] byteArray = Base64.decode(userData.getUserData3().getProfilePicture(), Base64.DEFAULT);//intent.getByteArrayExtra("profilePicture");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            mUserProfilePicture.setImageBitmap(bmp);
//
//            String bmi = userData.getUserData1().getBmi() +"";
//
//            // add item name and detail to custom list. This will be used for the master detail flow to show modules
//            mCustomListData.addItem("BMI", bmi);
//            mCustomListData.addItem("Weather", "Weather");
//            mCustomListData.addItem("Hikes near me", "Hikes");
//
//            String goalsName = userData.getUserGoals().isAllGoalsSet() ? "Update Goal" : "Set Goal";
//            mCustomListData.addItem(goalsName, "Goal");
//
//        }
//    };
//}
