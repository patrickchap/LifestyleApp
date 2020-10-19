package com.example.LifestyleApp.UserInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.LifestyleApp.Home.Home;
import com.example.LifestyleApp.R;
import java.io.ByteArrayOutputStream;
import java.util.UUID;


public class UserInfo3 extends AppCompatActivity implements View.OnClickListener{
    private TextView mSnapSelfieTextView;
    private ImageView mProfilePictureImageView;
    private Button mCreateButton;
    private String mEmail;
//    private UserInfo3ViewModel mUserInfo3ViewModel;

    //Define a request code for the camera
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_3);

        mSnapSelfieTextView = findViewById(R.id.snapSelfie);
        mProfilePictureImageView = findViewById(R.id.userImage);
        mCreateButton = findViewById(R.id.createButton);

        mSnapSelfieTextView.setOnClickListener(this);
        mCreateButton.setOnClickListener(this);

//        mUserInfo3ViewModel = ViewModelProviders.of(this).get(UserInfo3ViewModel.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.snapSelfie: {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
//                    mEmail = "userInfo3";
//                    mUserInfo3ViewModel.setViews(mEmail, mProfilePictureImageView);
                }
                break;
            }
            case R.id.createButton: {
//                mEmail = UUID.randomUUID().toString();
//                mUserInfo3ViewModel.setViews(mEmail, mProfilePictureImageView);
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap thumbnailImage = (Bitmap) extras.get("data");
            mProfilePictureImageView.setImageBitmap(thumbnailImage);
        }
    }
}
