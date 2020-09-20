package util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.LifestyleApp.ItemDetailActivity;

public class GetIntentUtil {

    public static Intent getIntent(Context context, String detail){
        if(detail.equals("Hikes")){
            Uri gmmIntentUri = Uri.parse("geo:40.7608,111.8910?q=hikes");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                return mapIntent;
            }
        }
        return new Intent(context, ItemDetailActivity.class);
    }
}
