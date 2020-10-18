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

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetLocationUtil {


    public static Location getLocation(Context context) {


        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    new String [] {
                            android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    1
            );
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double[] longitude = new double[1];
        final double[] latitude = new double[1];
        if(location!= null){
            longitude[0] = location.getLongitude();
            latitude[0] = location.getLatitude();
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

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, locationListener);

        return location;
    }

    public static Address getAddress(double lat, double lon, Context context) throws IOException {

        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = gcd.getFromLocation(lat, lon,
                1);

        return addresses.get(0);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        System.out.println("IN ONREQUESTPERMISSIONSRESULT");

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    System.out.println("PERMISSION GRANTED");

                } else {
                    System.out.println("PERMISSION DENIED");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }




}
