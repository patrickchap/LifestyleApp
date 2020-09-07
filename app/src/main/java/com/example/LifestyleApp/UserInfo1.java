package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.LifestyleApp.R;

public class UserInfo1 extends AppCompatActivity {

    Button mContinueButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_1);

        mContinueButton = findViewById(R.id.continueButton);


        mContinueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                continueToUserInfo2();
            }
        });
    }

    private void continueToUserInfo2() {
        Intent intent = new Intent(this, UserInfo2.class);
        startActivity(intent);
    }
}
