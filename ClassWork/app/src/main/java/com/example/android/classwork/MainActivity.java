package com.example.android.classwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void submit_info(){

        EditText editText =  (EditText)findViewById(R.id.edit_name);
        editText.getText().toString();


        TextView textView = (TextView)findViewById(R.id.info_show);
        textView.setText("" + editText);


    }




}
