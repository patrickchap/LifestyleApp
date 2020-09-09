package com.example.LifestyleApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;


public class UserInfo3 extends AppCompatActivity implements View.OnClickListener{
    TextView mSnapSelfieTextView;
    ImageView mProfilePictureImageView;
    Button mCreateButton;


    //Define a request code for the camera
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_3);

        //TODO: create intent and get member variables from UserInfo2

        mSnapSelfieTextView = findViewById(R.id.snapSelfie);
        mProfilePictureImageView = findViewById(R.id.userImage);
        mCreateButton = findViewById(R.id.createButton);

        mSnapSelfieTextView.setOnClickListener((View.OnClickListener) this);
        mCreateButton.setOnClickListener((View.OnClickListener) this);

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
                Intent intentFromUserInfo1 = getIntent();
                double bmi = intentFromUserInfo1.getDoubleExtra("bmi",0);
                System.out.println("bmi from user2 in 3 " + bmi);

                Intent intent = new Intent(this, Home.class);
                intent.putExtra("bmi", bmi);

                Bitmap bmp = ((BitmapDrawable)mProfilePictureImageView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
//                Drawable drawable = mProfilePictureImageView.getDrawable();
                intent.putExtra("profilePicture", byteArray);

                //TODO: create a user
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

