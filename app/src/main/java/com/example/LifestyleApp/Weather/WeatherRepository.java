package com.example.LifestyleApp.Weather;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

public class WeatherRepository {
    private final MutableLiveData<WeatherData> jsonData =
            new MutableLiveData<WeatherData>();
    private String mLocation;
    private String mJsonString;
    private WeatherDao mWeatherDao;

    WeatherRepository(Application application){
        WeatherRoomDatabase db = WeatherRoomDatabase.getDatabase(application);
        mWeatherDao = db.weatherDao();
        loadData();
    }

    public void setLocation(String location){
        mLocation = location;
        loadData();
    }

    private void insert(){
        WeatherTable weatherTable = new WeatherTable(mLocation,mJsonString);
        new insertAsyncTask(mWeatherDao).execute(weatherTable);
    }

    public MutableLiveData<WeatherData> getData() {
        return jsonData;
    }

    private void loadData()
    {
        new fetchWeatherAsyncTask(this).execute(mLocation);
    }

    private static class fetchWeatherAsyncTask extends AsyncTask<String,Void,String>{

        private WeakReference<WeatherRepository> mRepoWReference;

        fetchWeatherAsyncTask(WeatherRepository repo)
        {
            mRepoWReference = new WeakReference<WeatherRepository>(repo);
        }

        @Override
        protected String doInBackground(String... strings) {

            String location = strings[0];
            URL weatherDataURL = null;
            String retrievedJsonData = null;

            //checks if location already has been searched for (maintain local list of locations).
            if(location!=null) {

                //If not, get that data from the internet.
                weatherDataURL = NetworkUtils.buildURLFromString(location);
                try {
                    retrievedJsonData = NetworkUtils.getDataFromURL(weatherDataURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //If location has already been searched for, repository hands that up to ViewModel.
            return retrievedJsonData;
        }

        @Override
        protected void onPostExecute(String returnedJson) {
            WeatherRepository localWRvar = mRepoWReference.get();
            if(returnedJson!=null) {

                //Put it into database.
                localWRvar.mJsonString = returnedJson;
                localWRvar.insert();

                try {
                    //Hands it to ViewModel.
                    localWRvar.jsonData.setValue(JSONWeatherUtils.getWeatherData(returnedJson));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static class insertAsyncTask extends AsyncTask<WeatherTable,Void,Void>{
        private WeatherDao mAsyncTaskDao;

        insertAsyncTask(WeatherDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(WeatherTable... weatherTables) {
            mAsyncTaskDao.insert(weatherTables[0]);
            return null;
        }
    }
}
