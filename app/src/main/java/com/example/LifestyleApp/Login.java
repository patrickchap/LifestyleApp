package com.example.LifestyleApp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.LifestyleApp.UserInfo.UserInfo1;
import com.example.LifestyleApp.UserInfo.UserInfo2;
import com.example.LifestyleApp.UserInfo.UserInfoViewModel;

import java.util.UUID;


public class Login extends AppCompatActivity {
    Button mSubmit, mSignUp;
    UserInfoViewModel userInfoViewModel;
    EditText mUserName;
    EditText mPassword;

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
        System.out.println(mUserName.getText());

        if (!mUserName.getText().toString().equals("") || !mPassword.getText().toString().equals("")){
            userInfoViewModel.insertUserID(UUID.randomUUID().toString());
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