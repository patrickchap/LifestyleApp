package com.example.LifestyleApp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.LifestyleApp.UserInfo.UserInfo1;


public class Login extends AppCompatActivity {
    Button mSubmit, mSignUp;
    String mPassword, mEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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
        Intent intent = new Intent(this, UserInfo1.class);
        startActivity(intent);
    }

}
