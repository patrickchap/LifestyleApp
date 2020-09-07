package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Login extends Activity {
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
            public void onClick(View v) {
                continueToUserInfo1();
            }
        });


    }

    private void continueToUserInfo1(){
        Intent intent = new Intent(this, UserInfo1.class);
        startActivity(intent);
    }
}
