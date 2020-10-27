package com.example.LifestyleApp.MasterList;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CustomMasterList implements Parcelable {
    private List <String> mItemList;
    private List <String> mItemDetails;


    public void addItem (String itemName, String detail) {
        mItemList.add (itemName);
        mItemDetails.add (detail);
    }

    protected CustomMasterList (Parcel in) {
        in.readStringList (mItemList);
        in.readStringList (mItemDetails);
    }
    public CustomMasterList () {
        this.mItemList = new ArrayList <> ();
        this.mItemDetails = new ArrayList <> ();
    }

    public static final Creator <CustomMasterList> CREATOR = new Creator <CustomMasterList> () {
        @Override
        public CustomMasterList createFromParcel (Parcel in) {
            return new CustomMasterList (in);
        }

        @Override
        public CustomMasterList [] newArray (int size) {
            return new CustomMasterList [size];
        }
    };

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeStringList (mItemList);
        dest.writeStringList (mItemDetails);
    }

    public List <String> getItemList () {
        return mItemList;
    }

    public void setItemList (List <String> itemList) {
        mItemList = itemList;
    }

    // Implement getter for item details at a position
    public String getItemDetail (int position) {
        return mItemDetails.get (position);
    }
}

//package com.example.LifestyleApp.MasterList;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomMasterList  implements Parcelable {
//    private List<String> mItemList;
//    private List<String> mItemDetails;
//
//
//    public void addItem(String itemName, String detail){
//        mItemList.add(itemName);
//        mItemDetails.add(detail);
//    }
//
//    protected CustomMasterList(Parcel in) {
//        in.readStringList(mItemList);
//        in.readStringList(mItemDetails);
//    }
//    public CustomMasterList() {
//        this.mItemList = new ArrayList<>();
//        this.mItemDetails =  new ArrayList<>();
//    }
//
//
//    public static final Creator<CustomMasterList> CREATOR = new Creator<CustomMasterList>() {
//        @Override
//        public CustomMasterList createFromParcel(Parcel in) {
//            return new CustomMasterList(in);
//        }
//
//        @Override
//        public CustomMasterList[] newArray(int size) {
//            return new CustomMasterList[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeStringList(mItemList);
//        dest.writeStringList(mItemDetails);
//    }
//
//    public List<String> getItemList() {
//        return mItemList;
//    }
//
//    public void setItemList(List<String> itemList) {
//        mItemList = itemList;
//    }
//
//    //Implement getter for item details at a position
//    public String getItemDetail(int position) {
//        return mItemDetails.get(position);
//    }
//}
