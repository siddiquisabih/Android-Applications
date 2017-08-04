package com.example.sabih.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Question1 extends AppCompatActivity {

 MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);


          final   String a[] = new String[10];
            final String ans[] = new String[10];
            ans[0] = "sahe";
            a[0] = "what is text?";

        final Button nextButton1 = (Button)findViewById(R.id.nextToQuestion2);
        nextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextToQuestion2 = new Intent(Question1.this, Question2.class);
                for(int ab  = 0 ;ab<10;ab++){
                    TextView tv = (TextView) findViewById(R.id.dynamic);

                    tv.setText(a[0]);
                    RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);



                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    RadioButton rd  = (RadioButton)findViewById(selectedId);
                    String tex = "TextView";
                    if (rd.getText().equals(tex)){
                        Toast.makeText(Question1.this,"sai hai", Toast.LENGTH_SHORT).show();

                    }


                    else {

                        Toast.makeText(Question1.this,"sai nai hai", Toast.LENGTH_SHORT).show();

                    }
                }





                startActivity(nextToQuestion2);





            }
        });





    }}