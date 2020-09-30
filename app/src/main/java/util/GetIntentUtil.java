package util;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;


import com.example.LifestyleApp.GoalManager.GoalManagerActivity;
import com.example.LifestyleApp.ItemDetail.ItemDetailActivity;
import com.example.LifestyleApp.UserInfo.User;

import java.io.IOException;

public class GetIntentUtil {

    public static Intent getIntent(Context context, String detail, Bundle bundle, User user){
        if(detail.equals("Hikes")){
            Location location = GetLocationUtil.getLocation(context);


            String uri = "geo:" + location.getLatitude() + ","  + location.getLongitude() + "?q=hikes";
            Uri gmmIntentUri = Uri.parse(uri);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                return mapIntent;
            }
        }else if(detail.equals("Goal")){
            Intent returnIntent = new Intent(context, GoalManagerActivity.class);
            returnIntent.putExtra("user", user);
//            returnIntent.putExtras(bundle);
            return returnIntent;
        }


        Intent returnIntent = new Intent(context, ItemDetailActivity.class);
        returnIntent.putExtras(bundle);
        return returnIntent;
    }
}
