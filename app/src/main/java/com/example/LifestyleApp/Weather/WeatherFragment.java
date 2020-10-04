package com.example.LifestyleApp.Weather;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.LifestyleApp.R;
import org.json.JSONException;
import java.io.IOException;
import util.GetLocationUtil;
import util.GetWeatherDataUtil;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    WeatherData weatherData;
    TextView mTemp;
    TextView mLocation;
    Button mSearchWeather;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_layout, container, false);
        mTemp = view.findViewById(R.id.tempValue);
        mLocation = view.findViewById(R.id.locationName);
        mSearchWeather = view.findViewById(R.id.searchWeatherData);
        mSearchWeather.setOnClickListener(this);


        Location location = GetLocationUtil.getLocation(getContext());
        Address address = null;
        try {
            address = GetLocationUtil.getCity(location.getLatitude(), location.getLongitude(), getContext());
            mLocation.setText(address.getLocality() + ", " + address.getCountryCode());
            String cityCountry = address.getLocality() + "," + address.getCountryCode();
            GetWeatherDataUtil.getWeatherInfo(getContext(), cityCountry, isTablet());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }

    public void getResponse(String response) throws JSONException {
        weatherData = GetWeatherDataUtil.createWeatherDate(response);
        if(weatherData != null){
            mTemp.setText(weatherData.getmTemp());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchWeatherData : {
                System.out.println("Search");
                GetWeatherDataUtil.getWeatherInfo(getContext(), mLocation.getText().toString(), isTablet());
            }
        }

    }

    boolean isTablet()
    {
        return getResources().getBoolean(R.bool.isTablet);
    }
}
