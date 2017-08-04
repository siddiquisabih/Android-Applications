package com.example.android.chakri;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView sabihTextView = (TextView)findViewById(R.id.sabih);

        sabihTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sabihIntent = new Intent(MainActivity.this , Sabih.class);
                startActivity(sabihIntent);
            }
        });


        TextView umairTextView = (TextView)findViewById(R.id.umair);

        umairTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent umairIntent = new Intent(MainActivity.this, Umair.class);
                startActivity(umairIntent);

            }
        });


        TextView anusTextView = (TextView)findViewById(R.id.anus);
        anusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent anusIntent = new Intent(MainActivity.this , Anus.class);
                startActivity(anusIntent);
            }
        });

        TextView talhaTextView = (TextView)findViewById(R.id.talha);
        talhaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent talhaIntent = new Intent(MainActivity.this , Anus.class);
                startActivity(talhaIntent);
            }
        });






    }
}

























/*
        if ( userName.getText().toString() == username1){

            /*TextView sab = new TextView(MainActivity.this);
            sab.setText("dekh mujhe pata hai yai tum ho");

            rootView.addView(sab);

Toast.makeText(this,"askhkjashd" , Toast.LENGTH_SHORT).show();

        } */



