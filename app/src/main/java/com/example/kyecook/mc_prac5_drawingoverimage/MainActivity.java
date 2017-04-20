package com.example.kyecook.mc_prac5_drawingoverimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int SELECT_IMAGE = 1;
    private Button selectButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectButton = (Button)findViewById(R.id.select);
        imageView = (ImageView)findViewById(R.id.image);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use implicit Intent
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*"); // Mime Type for Image
                startActivityForResult(intent, SELECT_IMAGE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK){

            Uri dataURI = intent.getData();
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), dataURI);
                imageView.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
