package com.example.LifestyleApp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.UserInfo.UserInfo1;
import com.example.LifestyleApp.UserInfo.UserInfoViewModel;

import java.util.UUID;


public class Login extends AppCompatActivity {
    Button mSubmit, mSignUp;
    UserInfoViewModel userInfoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);

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
        userInfoViewModel.insertUserID(UUID.randomUUID().toString());
        Intent intent = new Intent(this, UserInfo1.class);
        startActivity(intent);
    }

}