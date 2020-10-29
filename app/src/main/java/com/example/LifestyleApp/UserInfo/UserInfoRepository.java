package com.example.LifestyleApp.UserInfo;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.LifestyleApp.Login;
import com.example.LifestyleApp.Tables.UserIDTable;
import com.example.LifestyleApp.Tables.UserInfoTable;
import com.example.LifestyleApp.daos.UserIDDao;

import org.json.JSONException;

import java.util.List;

public class UserInfoRepository {

    private static UserInfoRepository mInstance;
    private static Context mContext;
    private static UserInfoDatabase roomDatabase;
    private static UserInfoDao userInfoDao;
    private static UserIDDao userIDDao;
    private static UserInfoTable userInfoTable;
    private static int userIDIndex;
//    private static String userID;

    private final MutableLiveData<UserData> userData =
            new MutableLiveData<>();


    private UserInfoRepository(Context context) {
        mContext = context;
        userIDIndex = 0;
    }

    public static synchronized UserInfoRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserInfoRepository(context);
        }
        roomDatabase = UserInfoDatabase.getDatabase(mContext);
        userInfoDao = roomDatabase.mUserInfoDao();
        userIDDao = roomDatabase.mUserIDDao();
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
                userInfoTable = userInfoTables.get(0);
                userData.getValue().getUserData1().setHeight(userInfoTable.getHeight());
            }
        }
    };

    public void createNewUser(String userID) {
        UserData newUser = new UserData(userID);
        userData.setValue(newUser);

        userIDDao.getAll().observeForever(userIDTables -> {
            if (userIDTables.size() > 0) {
                UserIDTable userIDTable = userIDTables.get(0);
                userIDTable.setIndex(userIDIndex++);
                userIDTable.setUserId(userID);
                new insertAsyncTaskUserID(userIDDao).execute(userIDTable);
            }
            else {
                UserIDTable userIDTable = new UserIDTable(userIDIndex++, userID);
                new insertAsyncTaskUserID(userIDDao).execute(userIDTable);
            }
        });

        userInfoTable = new UserInfoTable(userID);
        new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
    }

//    public void insertData(String tableName, String data){
//            if (userData.getValue() != null){
//                if (userData.getValue().getUserGoals() != null){
//                    UserData.UserGoals userGoals = userData.getValue().getUserGoals();
//                    if (columnName == "goal"){
//                        if (!userGoals.goalSet){
//                            userGoals.goal = data;
//                            userGoals.goalSet = true;
//                            userInfoTable.setUserInfoPage(tableName);
//                            userInfoTable.setGoalSet(true);
//                            userInfoTable.setGoal(data);
//                            new insertAsyncTask(userInfoDao).execute(userInfoTable);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void insertToColumn(String tableName, String columnName, String data) {
//        if (tableName == "goals") {
//            if (userData.getValue() != null) {
//                if (userData.getValue().getUserGoals() != null) {
//                    UserData.UserGoals userGoals = userData.getValue().getUserGoals();
//                    if (columnName == "goal") {
//                        if (!userGoals.goalSet) {
//                            userGoals.goal = data;
//                            userGoals.goalSet = true;
//                            userInfoTable.setUserInfoPage(tableName);
//                            userInfoTable.setGoalSet(true);
//                            userInfoTable.setGoal(data);
//                            new insertAsyncTask(userInfoDao).execute(userInfoTable);
//                        }
//                    }
//                }
//            }
//        }
//    }

    public void insertUserHeight(int height) throws JSONException {
        userData.getValue().getUserData1().setHeight(height);
        userInfoTable.setHeight(height);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setHeight(height);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertUserWeight(float weight) throws JSONException {
        userData.getValue().getUserData1().setWeight(weight);
        userInfoTable.setWeight(weight);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setWeight(weight);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertUserGender(String gender) throws JSONException {
        userData.getValue().getUserData1().setGender(gender);
        userInfoTable.setGender(gender);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setGender(gender);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertUserDob(Long dob) throws JSONException {
        userData.getValue().getUserData1().setDob(dob);
        userInfoTable.setDOB(dob);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setDOB(dob);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertBMI(double bmi) throws JSONException {
        userData.getValue().getUserData1().setBmi(bmi);
        userInfoTable.setBmi(bmi);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setBmi(bmi);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertProfilePicture(String imageString){
        userData.getValue().getUserData3().setProfilePicture(imageString);
        userInfoTable.setProfilePicture(imageString);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setProfilePicture(imageString);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertGoal(String goal){
        userData.getValue().getUserGoals().setGoal(goal);
        userInfoTable.setGoal(goal);

        userData.getValue().getUserGoals().setGoalSet(true);
        userInfoTable.setGoalSet(true);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setGoal(goal);
                userInfoTable.setGoalSet(true);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertPerWeekPounds(int perWeekPounds){
        userData.getValue().getUserGoals().setPerWeekPounds(perWeekPounds);
        userInfoTable.setPerWeekPounds(perWeekPounds);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setPerWeekPounds(perWeekPounds);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertGoalWeight(float goalWeight){
        userData.getValue().getUserGoals().setGoalWeight(goalWeight);
        userInfoTable.setGoalWeight(goalWeight);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setGoalWeight(goalWeight);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertActivity(String activity){
        userData.getValue().getUserGoals().setActivity(activity);
        userInfoTable.setActivity(activity);

        userData.getValue().getUserGoals().setActivitySet(true);
        userInfoTable.setActivitySet(true);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setActivity(activity);
                userInfoTable.setActivitySet(true);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertBmr(float bmr){
        userData.getValue().getUserGoals().setBmr(bmr);
        userInfoTable.setBmr(bmr);

        userData.getValue().getUserGoals().setBmrSet(true);
        userInfoTable.setBmrSet(true);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setBmr(bmr);
                userInfoTable.setBmrSet(true);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertCalories(float calories){
        userData.getValue().getUserGoals().setCalories(calories);
        userInfoTable.setCalories(calories);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setCalories(calories);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertPassword(String userPassword) {
        userData.getValue().getUserData0().setPassword(userPassword);
        userInfoTable.setPassword(userPassword);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setPassword(userPassword);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

    public void insertUserName(String userName) {
        userData.getValue().getUserData0().setUserName(userName);
        userInfoTable.setUserName(userName);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setUserName(userName);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });

    }

    public void insertSteps(int mSteps) {
        userData.getValue().getUserDataSteps().setSteps(mSteps);
        userInfoTable.setSteps(mSteps);

        userInfoDao.getAll().observeForever(userInfoTables -> {
            if (userInfoTables != null) {
                UserInfoTable userInfoTable = userInfoTables.get(0);
                userInfoTable.setSteps(mSteps);
                new insertAsyncTaskUserInfo(userInfoDao).execute(userInfoTable);
            }
        });
    }

//    List<UserInfoTable> usersList;

//    public void setUserList( List<UserInfoTable> list){
//        usersList = list;
//    }
    public List<UserInfoTable>  getUsersByUserName(String userName) {
//        new getAsyncTaskUser(userInfoDao).execute(userName);
        return null;
    }



    private static class insertAsyncTaskUserInfo extends AsyncTask<UserInfoTable, Void, Void> {
        private UserInfoDao mAsyncTaskDao;

        insertAsyncTaskUserInfo(UserInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfoTable... userInfoTables) {
            mAsyncTaskDao.insert(userInfoTables[0]);
            return null;
        }
    }

    private static class insertAsyncTaskUserID extends AsyncTask<UserIDTable, Void, Void> {
        private UserIDDao mAsyncTaskDao;

        insertAsyncTaskUserID(UserIDDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(UserIDTable... userIDTables) {
            mAsyncTaskDao.insert(userIDTables[0]);
            return null;
        }
    }

//    private class getAsyncTaskUser extends AsyncTask<String, Void, List<UserInfoTable>> {
//        private UserInfoDao mAsyncTaskDao;
//
//
//        getAsyncTaskUser(UserInfoDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected List<UserInfoTable> doInBackground(String... strings) {
//            List<UserInfoTable> table = mAsyncTaskDao.findByUserName(strings[0]);
//            return table;
//        }
//
//        @Override
//        protected void onPostExecute(List<UserInfoTable> userInfoTables) {
//            super.onPostExecute(userInfoTables);
////            setUserList(userInfoTables);
//        }
//    }



}