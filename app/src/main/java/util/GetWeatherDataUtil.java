package util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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

public class GetWeatherDataUtil {

    public static void getWeatherInfo(Context context, final TextView textView) {


        String consumerKey = BuildConfig.WEATHERKEY;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Salt Lake City&APPID="+consumerKey+"&units=imperial";

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    new String [] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    11
            );
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double[] longitude = new double[1];
        final double[] latitude = new double[1];
        if(location!= null){
            longitude[0] = location.getLongitude();
            latitude[0] = location.getLatitude();
            System.out.println("Long lat " + longitude[0] + " " + latitude[0]);
        }

//        final LocationListener locationListener = new LocationListener() {
//            public void onLocationChanged(Location location) {
//                longitude[0] = location.getLongitude();
//                latitude[0] = location.getLatitude();
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//            }
//        };
//
//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);




    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObj = new JSONObject(response);
                        textView.setText(jsonObj.getJSONObject("main").getString("temp") + "°F");
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

}







}
