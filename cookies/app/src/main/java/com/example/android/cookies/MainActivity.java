package com.example.android.cookies;

import android.media.Image;
import android.support.annotation.DimenRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


    }



    public void afterText(){

        TextView sa = (TextView) findViewById(R.id.status_text_view);
        sa.setText("I'm so full");

    }


    public void afterPic(){

        ImageView sa = (ImageView) findViewById(R.id.android_cookie_image_view);
        sa.setImageResource(R.drawable.after_cookie);


    }






    /**
     * Called when the cookie should be eaten.
     */
    public void eatCookie(View view) {
        // TODO: Find a reference to the ImageView in the layout. Change the image.


        afterText();


        // TODO: Find a reference to the TextView in the layout. Change the text.

        afterPic();

    }

}