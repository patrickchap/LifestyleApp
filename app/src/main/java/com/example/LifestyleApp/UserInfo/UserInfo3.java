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

import com.example.LifestyleApp.Home;
import com.example.LifestyleApp.R;
import java.io.ByteArrayOutputStream;


public class UserInfo3 extends AppCompatActivity implements View.OnClickListener{
    private TextView mSnapSelfieTextView;
    private ImageView mProfilePictureImageView;
    private Button mCreateButton;
    private UserInfoViewModel userInfoViewModel;

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

        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.snapSelfie: {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;
            }
            case R.id.createButton: {
                Bitmap bmp = ((BitmapDrawable)mProfilePictureImageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                userInfoViewModel.insertUserInfo3(byteArray);
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
