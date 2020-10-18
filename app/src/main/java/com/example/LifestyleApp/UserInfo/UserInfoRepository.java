package com.example.LifestyleApp.UserInfo;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.util.Date;

public class UserInfoRepository {
    private static MutableLiveData<UserData> userData =
            new MutableLiveData<UserData>();

    private String mUserEmail;

    private TextView mWeightTextView;
    private TextView mHeightTextView;
    private TextView mGenderTextView;
    private TextView mDOBTextView;

    private TextView mCityTextView;
    private TextView mCountryTextView;

    ImageView mProfilePictureImageView;

    UserInfoTable userInfoTable;
    private String userInfoJson;

    private UserInfoDao mUserInfoDao;

    public UserInfoRepository(Application application) {
        UserInfoDatabase db = UserInfoDatabase.getDatabase(application);
        mUserInfoDao = db.mUserInfoDao();
    }

    public void setUserInfo1Views(String userEmail,
                                  TextView heightTextView,
                                  TextView weightTextView,
                                  TextView genderTextView,
                                  TextView dobTextView) {

        mUserEmail = userEmail;
        mHeightTextView = heightTextView;
        mWeightTextView = weightTextView;
        mGenderTextView = genderTextView;
        mDOBTextView = dobTextView;

        loadData();
    }

    public void setUserInfo2Views(String userEmail,
                                  TextView cityTextView,
                                  TextView countryTextView) {

        mUserEmail = userEmail;
        mCityTextView = cityTextView;
        mCountryTextView = countryTextView;

        loadData();
    }

    public void setUserInfo3Views(String userEmail, ImageView profilePictureImageView) {

        mUserEmail = userEmail;
        mProfilePictureImageView = profilePictureImageView;

        loadData();
    }

    private void insert() {
        userInfoTable = new UserInfoTable(mUserEmail, userInfoJson);
        new insertAsyncTask(mUserInfoDao).execute(userInfoTable);
    }

    public static MutableLiveData<UserData> getData() {
        return userData;
    }

    public void loadData() {
        new fetchUserInfoAsyncTask(this).execute();
    }

    private static class fetchUserInfoAsyncTask extends AsyncTask<String, Void, String> {

        private WeakReference<UserInfoRepository> mRepoWReference;

        fetchUserInfoAsyncTask(UserInfoRepository repo) {
            mRepoWReference = new WeakReference<UserInfoRepository>(repo);
        }

        @Override
        protected String doInBackground(String... strings) {
            UserInfoRepository localWRvar = mRepoWReference.get();

            TextView heightTextView = localWRvar.mHeightTextView;
            TextView weightTextView = localWRvar.mWeightTextView;
            TextView genderTextView = localWRvar.mGenderTextView;
            TextView dobTextView = localWRvar.mDOBTextView;

            TextView cityTextView = localWRvar.mCityTextView;
            TextView countryTextView = localWRvar.mCountryTextView;

            ImageView profilePictureImageView = localWRvar.mProfilePictureImageView;

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

                    UserInfoInput input = new UserInfoInput();
                    input.setHeight(heightInInches);
                    input.setWeight(fWeight);
                    input.setBmi(bmi);
                    input.setGender(gender);
                    input.setDOB(dobDate);

//                    UserInfo1Input input = new UserInfo1Input(heightInInches, fWeight, bmi, gender, dobDate);

                    Gson gson = new Gson();

                    String userInputJson = gson.toJson(input);

                    return userInputJson;

                }
//            } else if (cityTextView != null && countryTextView != null) {
//                String city = cityTextView.getText().toString();
//                String country = countryTextView.getText().toString();
//                if (!city.equals("City") && !country.equals("Country")) {
//
//                    UserInfo2Input input = new UserInfo2Input(city, country);
//
//                    Gson gson = new Gson();
//
//                    String userInputJson = gson.toJson(input);
//
//                    return userInputJson;
//                }
//
            } else if (profilePictureImageView != null) {
                Bitmap bmp = ((BitmapDrawable) profilePictureImageView.getDrawable()).getBitmap();
                if (!bmp.equals(0)) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    String profilePictureString = stream.toString();
//                    byte[] byteArray = stream.toByteArray();
                    UserInfoInput input = new UserInfoInput();
                    input.setProfilePicture(profilePictureString);
                    Gson gson = new Gson();
                    String userInputJson = gson.toJson(input);
                    return userInputJson;
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String userInputJson) {
            if (userInputJson != null) {
                UserInfoRepository localWRvar = mRepoWReference.get();
                localWRvar.userInfoJson = userInputJson;
                localWRvar.insert();
                try {
                    UserData userData = new UserData(localWRvar.mUserEmail);
                    UserData userDataParsed = JSONUserInputUtils.getUserInfoData(userInputJson, userData);
                    localWRvar.userData.setValue(userDataParsed);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        private static class UserInfoInput {
            private String city;
            private String country;
            private int height;
            private float weight;
            private double bmi;
            private String gender;
            private Long dob;
            private String profilePicture;

            public UserInfoInput(){
                city = "";
                country = "";
                height = 0;
                weight = 0;
                bmi = 0;
                gender = "";
                dob = Long.valueOf(0);
                profilePicture = "";
            }

            private void setHeight(int height) {
                this.height = height;
            }

            private void setWeight(float weight) {
                this.weight = weight;
            }

            private void setBmi(double bmi) {
                this.bmi = bmi;
            }

            private void setGender(String gender) {
                this.gender = gender;
            }

            private void setDOB(Long dob) {
                this.dob = dob;
            }

            private void setProfilePicture(String profilePicture) {
                this.profilePicture = profilePicture;
            }

            private int getHeight() {
                return this.height;
            }

            private float getWeight() {
                return this.weight;
            }

            private double getBMI() {
                return this.bmi;
            }

            private String getGender() {
                return this.gender;
            }

            private Long getDOB() {
                return this.dob;
            }

        }
    }

    private class insertAsyncTask extends AsyncTask<UserInfoTable, Void, Void> {
        private UserInfoDao mAsyncTaskDao;

        insertAsyncTask(UserInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfoTable... userInfoTables) {
            mAsyncTaskDao.insert(userInfoTables[0]);
            return null;
        }

    }

//        private static class UserInfo1Input {
//            private int height;
//            private float weight;
//            private double bmi;
//            private String gender;
//            private Long dob;
//
//            private UserInfo1Input(int height, float weight, double bmi, String gender, Long dob) {
//                this.height = height;
//                this.weight = weight;
//                this.bmi = bmi;
//                this.gender = gender;
//                this.dob = dob;
//            }
//        }
//
//    private static class UserInfo2Input {
//        private String city;
//        private String country;
//
//        private UserInfo2Input(String city, String country) {
//            this.city = city;
//            this.country = country;
//        }
//    }
//
//    private static class UserInfo3Input {
//        private byte[] profilePicture;
//
//        private UserInfo3Input(byte[] profilePicture) {
//            this.profilePicture = profilePicture;
//        }
//    }

//        private void setHeight(int height){
//            this.height = height;
//        }
//        private void setWeight(float weight){
//            this.weight = weight;
//        }
//        private void setBmi(double bmi){
//            this.bmi = bmi;
//        }
//        private void setGender(String gender){
//            this.gender = gender;
//        }
//        private void setDOB(Long dob){
//            this.dob = dob;
//        }
//
//        private int getHeight(){
//            return this.height;
//        }
//        private float getWeight(){
//            return this.weight;
//        }
//        private double getBMI(){
//            return this.bmi;
//        }
//        private String getGender(){
//            return this.gender;
//        }
//        private Long getDOB(){
//            return this.dob;
//        }

}


//        private void setHeight(int height){
//            this.height = height;
//        }
//        private void setWeight(float weight){
//            this.weight = weight;
//        }
//        private void setBmi(double bmi){
//            this.bmi = bmi;
//        }
//        private void setGender(String gender){
//            this.gender = gender;
//        }
//        private void setDOB(Long dob){
//            this.dob = dob;
//        }
//
//        private int getHeight(){
//            return this.height;
//        }
//        private float getWeight(){
//            return this.weight;
//        }
//        private double getBMI(){
//            return this.bmi;
//        }
//        private String getGender(){
//            return this.gender;
//        }
//        private Long getDOB(){
//            return this.dob;
//        }



