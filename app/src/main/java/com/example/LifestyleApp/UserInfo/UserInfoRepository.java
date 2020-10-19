package com.example.LifestyleApp.UserInfo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import org.json.JSONException;
import java.util.List;

public class UserInfoRepository {

    private static UserInfoRepository mInstance;
    private static Context mContext;

    private final MutableLiveData<UserData> userData =
            new MutableLiveData<>();

    private UserInfoDao userInfoDao;
    private UserInfoDatabase roomDatabase;

    private UserInfoRepository(Context context) {
        mContext = context;
        roomDatabase = UserInfoDatabase.getDatabase(context);
        userInfoDao = roomDatabase.mUserInfoDao();
    }

    public static synchronized UserInfoRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserInfoRepository(context);
        }
        return mInstance;
    }

    public MutableLiveData<UserData> getUserData() {
        return userData;
    }

    public void refreshData() {
        userInfoDao.getAll().observeForever(userInfoObserver);
    }

    public void removeObserver() {
        userInfoDao.getAll().removeObserver(userInfoObserver);
    }

    final Observer<List<UserInfoTable>> userInfoObserver = new Observer<List<UserInfoTable>>() {
        @Override
        public void onChanged(@Nullable final List<UserInfoTable> userInfoTables) {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                String userInfoJson = userInfoTable.getUserInfoJson();
                UserData userDataParsed = null;
                try {
                    userDataParsed = JSONUserInputUtils.getUserInfoData(userInfoJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (userDataParsed != null) {
                    userData.setValue(userDataParsed);
                }
            }
        }
    };

    public void insert(String userInfoLevel, String userInfoJson) throws JSONException {
        UserData userDataParsed = JSONUserInputUtils.getUserInfoData(userInfoJson);
        userData.setValue(userDataParsed);
        UserInfoTable userInfoTable = new UserInfoTable(userInfoLevel, userInfoJson);
        userInfoTable.setUserInfoLevel(userInfoLevel);
        userInfoTable.setUserInfoJson(userInfoJson);
        new insertAsyncTask(userInfoDao).execute(userInfoTable);
    }

    private static class insertAsyncTask extends AsyncTask<UserInfoTable,Void,Void> {
        private UserInfoDao mAsyncTaskDao;

        insertAsyncTask(UserInfoDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfoTable... userInfoTables) {
            mAsyncTaskDao.insert(userInfoTables[0]);
            return null;
        }
    }
}