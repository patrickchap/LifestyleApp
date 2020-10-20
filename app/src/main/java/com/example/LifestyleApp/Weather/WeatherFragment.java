package com.example.LifestyleApp.Weather;
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

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class WeatherFragment extends Fragment implements View.OnClickListener {

    TextView mTemp;
    TextView mLocation;
    Button mSearchWeather;

    private WeatherViewModel mWeatherViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_layout, container, false);
        mTemp = view.findViewById(R.id.tempValue);
        mLocation = view.findViewById(R.id.locationName);
        mSearchWeather = view.findViewById(R.id.searchWeatherData);
        mSearchWeather.setOnClickListener(this);

        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        (mWeatherViewModel.getData()).observe(this, weatherDataObserver);

        return view;

    }

    final Observer<WeatherData> weatherDataObserver  = new Observer<WeatherData>() {
        @Override
        public void onChanged(@Nullable final WeatherData weatherData) {
            if(weatherData!=null) {
                mTemp.setText("" + Math.round(weatherData.getTemperature().getTemp() - 273.15) + " C");
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.searchWeatherData:{
                String inputFromEt = mLocation.getText().toString().replace(' ','&');
                loadWeatherData(inputFromEt);
            }
            break;
        }
    }

    void loadWeatherData(String location){
        mWeatherViewModel.setLocation(location);
    }

    boolean isTablet()
    {
        return getResources().getBoolean(R.bool.isTablet);
    }
}
