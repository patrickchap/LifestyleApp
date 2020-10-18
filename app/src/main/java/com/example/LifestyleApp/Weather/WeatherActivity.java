package com.example.LifestyleApp.Weather;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.LifestyleApp.R;

import org.json.JSONException;

public class WeatherActivity extends AppCompatActivity {
    WeatherFragment weatherFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        weatherFragment = new WeatherFragment();
        weatherFragment.setArguments(getIntent().getExtras());
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fl_frag_itemdetail_container_phone, weatherFragment, "frag_itemdetail_gm");
        fTrans.commit();

    }
}
