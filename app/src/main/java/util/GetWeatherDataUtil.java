package util;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.LifestyleApp.MasterList;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.Weather.WeatherActivity;
import com.example.LifestyleApp.Weather.WeatherData;
import org.json.JSONException;
import org.json.JSONObject;

public class GetWeatherDataUtil {



    public static WeatherData createWeatherDate(String data) throws JSONException{
        WeatherData weatherData = new WeatherData();
        JSONObject jsonObj = new JSONObject(data);
        weatherData.setmTemp(jsonObj.getJSONObject("main").getString("temp"));

        return weatherData;
    }



    public static void getWeatherInfo(final Context context, String location, final boolean isTablet) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=3e827fa26d3a6b13a5408c6c46ff8469&units=imperial";


        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response + "<<<<< Response");

                        try {
                            if(isTablet){
                                ((MasterList)context).passResponse(response);
                            }else{
                                ((WeatherActivity)context).passResponse(response);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("err <<< + " + error);
            }
        });

        queue.add(stringRequest);

       }








}
