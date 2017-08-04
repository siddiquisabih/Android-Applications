package com.example.android.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void printToLogs(View view) {

        // Find first menu item TextView and print the text to the logs


        TextView menuItem1 = (TextView) findViewById(R.id.menu_item_1);
        String item1 = menuItem1.getText().toString();
        Log.v("MainActivity",item1);

        // Find second menu item TextView and print the text to the logs

        TextView menuItem2 = (TextView) findViewById(R.id.menu_item_2);
        String item2 = menuItem2.getText().toString();
        Log.v("MainActivity",item2);


        // Find third menu item TextView and print the text to the logs

        TextView menuItem3 = (TextView) findViewById(R.id.menu_item_3);
        String item3 = menuItem3.getText().toString();
        Log.v("MainActivity",item3);

    }
}