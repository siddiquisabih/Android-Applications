package com.example.sabih.miwokapp;

import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sabih on 12/3/2016.
 */
public class NumbersActivity  extends AppCompatActivity
    {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_numbers);


            /*ArrayList<Word> words = new ArrayList<Word>();




/*

            String words [] = new String[10];

            words[0] = "One";
            words[1] = "Two";
            words[2] = "Three";
            words[3] = "Four";
            words[4] = "Five";
            words[5] = "Six";
            words[6] = "Seven";
            words[7] = "Eight";
            words[8] = "Nine";
            words[9] = "Ten";
*/


            ArrayList<words> word = new ArrayList<words>();


            word.add(new words("one","lutti"));
            word.add(new words("two","otiiko"));
            word.add(new words("three","tolookosu"));
            word.add(new words("four","oyyisa"));
            word.add(new words("five","massokka"));
            word.add(new words("six","temmokka"));
            word.add(new words("seven","kenekaku"));
            word.add(new words("eight","kawinta"));
            word.add(new words("nine","wo'e"));
            word.add(new words("ten","na'aacha"));



            ListView listView = (ListView)findViewById(R.id.list);

/*

            wordAdapter adapter = new wordAdapter(this , word );

            listView.setAdapter(adapter);
*/


        }}















            /*
           /////////// shaban aye ga to is k sath yahan say shru karna hia inshallah /////

            LinearLayout engWords = (LinearLayout) findViewById(R.id.view);


            int i = 0;
            while (i < words.size()){
                TextView word = new TextView(NumbersActivity.this);
                word.setText(words.get(i));
                engWords.addView(word);
                i++;
*/





/*            LinearLayout list  = (LinearLayout) findViewById(R.id.list);





            for (int i = 0; i< words.size(); i++){
                TextView numberInWords = new TextView(NumbersActivity.this);
                numberInWords.setText(words.get(i));
                list.addView(numberInWords);

                 }*/









//            ListView listView = (ListView) findViewById(R.id.list);







