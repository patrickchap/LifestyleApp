package com.example.LifestyleApp;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Alpine extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Amplify.configure(getApplicationContext());
            Log.i("AlpineApp-Login", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("AlpineApp-Login", "Could not initialize Amplify", error);
        }
    }

    public void uploadFile() {
        File fileToUpload = new File(getApplicationContext().getFilesDir(), "ExampleKey");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToUpload));
            writer.append("File Contents");
            writer.close();
        } catch (Exception exception) {
            Log.e("AlpineApp", "Upload failed", exception);
        }


        Amplify.Storage.uploadFile(
                "ExampleKey",
                fileToUpload,
                result -> Log.i("AlpineApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("AlpineApp", "Upload failed", storageFailure)
        );
    }
}
