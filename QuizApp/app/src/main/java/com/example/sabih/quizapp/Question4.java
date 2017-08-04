package com.example.sabih.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Question4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);



        final Button nextButton1 = (Button)findViewById(R.id.nextToQuestion5);
        nextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextToQuestion2 = new Intent(Question4.this, Question5.class);
                startActivity(nextToQuestion2);


            }
        });



    }
}
