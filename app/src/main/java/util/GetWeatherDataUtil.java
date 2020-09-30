package util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.LifestyleApp.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetWeatherDataUtil {

    public static void getWeatherInfo(Context context, final TextView textView) {


        String weatherkey = BuildConfig.WEATHERKEY;
        RequestQueue queue = Volley.newRequestQueue(context);
        Location location = GetLocationUtil.getLocation(context);
        Address address = null;
        try {

            address = GetLocationUtil.getCity(location.getLatitude(), location.getLongitude(), context);
//            System.out.println("City " + address.getLocality() + " Country " + address.getCountryName());

            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + address.getLocality() + "&APPID="+weatherkey+"&units=imperial";


            final Address finalAddress = address;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                textView.setText("The weather in " + finalAddress.getLocality() + ", " + finalAddress.getCountryCode() + " is " +jsonObj.getJSONObject("main").getString("temp") + "°F");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("That didn't work " + error.getMessage());
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        } catch (IOException e) {
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + "Mountain View" + "&APPID="+weatherkey+"&units=imperial";

            final Address finalAddress = address;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                textView.setText("The weather in " + "Mountain View"  + ", " + "US" + " is " +jsonObj.getJSONObject("main").getString("temp") + "°F");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("That didn't work " + error.getMessage());
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
//            textView.setText("Can't find Location");
            e.printStackTrace();
        }






}







}
