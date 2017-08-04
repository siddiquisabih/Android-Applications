package com.example.sabih.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {

    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        int sabih = mainActivity.count;

        Toast.makeText(Result.this, sabih + "yahooo" , Toast.LENGTH_SHORT).show();



    }
}
