package com.example.LifestyleApp;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Alpine extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("AlpineApp-Login", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("AlpineApp-Login", "Could not initialize Amplify", error);
        }
    }

    public void uploadFile() {
        File userInfo = new File("/data/data/com.example.myapplication/databases/userInfo.db");
        File userInfo_shm = new File("/data/data/com.example.myapplication/databases/userInfo.db");
        File userInfo_wal = new File("/data/data/com.example.myapplication/databases/userInfo.db");

        Amplify.Storage.uploadFile(
                "userInfo",
                userInfo,
                result -> Log.i("AlpineApp-userInfo", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("AlpineApp", "Upload failed", storageFailure)
        );
    }
}
