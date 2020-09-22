package com.example.LifestyleApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.LifestyleApp.MasterList;
import com.example.LifestyleApp.R;
import com.example.LifestyleApp.UserInfo.User;

public class Home extends AppCompatActivity implements View.OnClickListener{
    Button moduleBtn;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        user = (User) getIntent().getSerializableExtra("user");

        moduleBtn = findViewById(R.id.moduleButton);
        moduleBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.moduleButton: {
                Intent intent = new Intent(this, MasterList.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }
    }
}
