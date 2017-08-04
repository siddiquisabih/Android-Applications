package com.example.sabih.miwokapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView number = (TextView) findViewById(R.id.numbers);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent numberIntent = new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numberIntent);



            }
        });


        TextView familyText = (TextView) findViewById(R.id.family);
        familyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent familyIntent = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        TextView colorText = (TextView) findViewById(R.id.colors);
        colorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent colorIntent = new Intent(MainActivity.this,colorsActivity.class);
                startActivity(colorIntent);


            }
        });

        TextView phraseText = (TextView) findViewById(R.id.phrases);
        phraseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent phraseIntent = new Intent(MainActivity.this,PhraseActivity.class);
                startActivity(phraseIntent);

            }
        });





    }
}
