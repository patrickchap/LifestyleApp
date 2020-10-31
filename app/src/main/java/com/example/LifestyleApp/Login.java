package com.example.LifestyleApp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.multidex.MultiDex;

import com.example.LifestyleApp.Tables.UserInfoTable;
import com.example.LifestyleApp.UserInfo.UserInfo1;
import com.example.LifestyleApp.UserInfo.UserInfoViewModel;

import java.util.List;
import java.util.UUID;


public class Login extends AppCompatActivity {
    Button mSubmit, mSignUp;
    UserInfoViewModel userInfoViewModel;
    EditText mUserName;
    EditText mPassword;
    List<UserInfoTable> userList;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);


        mUserName = findViewById(R.id.editTextTextEmailAddress);
        mPassword = findViewById(R.id.editTextTextPassword);

        mSubmit = findViewById(R.id.submitButton);
        mSignUp = findViewById(R.id.signUpButton);

        mSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                continueToUserInfo1();
            }
        });

    }



    private void continueToUserInfo1() {
//        userInfoViewModel.loadUserData();
//        List<UserInfoTable> users  = userInfoViewModel.getUsersByUserName(mUserName.getText().toString());
//        List<UserInfoTable> users = userInfoViewModel.getUsersByUserName(mUserName.getText().toString());
//        System.out.println(users.size() + " <<< users size");

        if (!mUserName.getText().toString().equals("") || !mPassword.getText().toString().equals("")){
            userInfoViewModel.insertUserID(UUID.randomUUID().toString());
            userInfoViewModel.insertUserInfo0(mUserName,mPassword);
            Intent intent = new Intent(this, UserInfo1.class);
            startActivity(intent);
        }

        else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Login Info Incomplete")
                    .setMessage("Please enter User Name and Password")
                    .setCancelable(false)
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishActivity(this.hashCode());
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

}