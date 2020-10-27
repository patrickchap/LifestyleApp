package com.example.LifestyleApp.Weather;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class WeatherViewModel extends AndroidViewModel {
    private MutableLiveData<WeatherData> weatherData;
    private WeatherRepository mWeatherRepository;

    public WeatherViewModel(Application application){
        super(application);

        mWeatherRepository = new WeatherRepository(application);
        weatherData = mWeatherRepository.getData();

    }

    public void setLocation(String location){
        mWeatherRepository.setLocation(location);
    }

    public LiveData<WeatherData> getData(){
        return weatherData;
    }

}