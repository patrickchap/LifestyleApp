package util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetLocationUtil {

    public static Location getLocation(Context context) {
//        FusedLocationProviderClient fusedLocationClient;
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
//
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                    (Activity) context,
//                    new String [] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
//                    11
//            );
////            return;
//        }
//
//        final Location[] location = new Location[1];
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location loc) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (loc != null) {
//                            location[0] = loc;
//                            System.out.println(location);
//                            // Logic to handle location object
//                        }
//                    }
//                });

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    new String [] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    11
            );
           // return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double[] longitude = new double[1];
        final double[] latitude = new double[1];
        if(location!= null){
            longitude[0] = location.getLongitude();
            latitude[0] = location.getLatitude();
            System.out.println("Long lat " + longitude[0] + " " + latitude[0]);
        }


        final LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                longitude[0] = location.getLongitude();
                latitude[0] = location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

        return location;
    }

    public static Address getCity(double lat, double lon, Context context) throws IOException {

        Geocoder gcd = new Geocoder(context, Locale.getDefault());

        List<Address> addresses = gcd.getFromLocation(lat, lon,
                1);



        return addresses.get(0);
    }


}
