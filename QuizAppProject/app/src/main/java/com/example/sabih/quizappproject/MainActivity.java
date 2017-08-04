package com.example.sabih.quizappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //initialize global variable for array's index
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*  this button open the quiz_page xml file
        *   in quiz_page.xml, the structur of quiz is define
        *
        * */
        Button button = (Button)findViewById(R.id.nextToQuiz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setContentView(R.layout.quiz_page);





        //array for questions
        final String[] questions = new String[9];
        questions[0] = "what is your name?";
        questions[1] = "what is your city name?";
        questions[2] = "what is your country Name?";
        questions[3] = "who was quid e azam?";
        questions[4] = "how many days in a week?";
        questions[5] = "how many hours in a day?";
        questions[6] = "how many days in a year?";
        questions[7] = "how many months in a year?";
        questions[8] = "how many weeks in one month?";
        //questions[9] = "how many hours in 2 days";

        //array for right answers
        final String[] answers = new String[9];
        answers[0] = "sabih";
        answers[1] = "karachi";
        answers[2] = "Pakistan";
        answers[3] = "founder of pakistan";
        answers[4] = "7";
        answers[5] = "24";
        answers[6] = "365";
        answers[7] = "12";
        answers[8] = "4";
        //answers[9] = "48";


        // 1st wrong answer
        final String[] wrongAnswer1 = new String[9];
        wrongAnswer1[0] = "shahood" ;
        wrongAnswer1[1] = "lahore";
        wrongAnswer1[2] = "America";
        wrongAnswer1[3] = "founder of america";
        wrongAnswer1[4] = "5";
        wrongAnswer1[5] = "35";
        wrongAnswer1[6] = "345";
        wrongAnswer1[7] = "15";
        wrongAnswer1[8] = "5";
        //wrongAnswer1[9] = "47";

        //2nd wrong answer
        final String[] wrongAnswer2 = new String[9];
        wrongAnswer2[0] = "umar" ;
        wrongAnswer2[1] = "islamabad";
        wrongAnswer2[2] = "India";
        wrongAnswer2[3] = "founder of india";
        wrongAnswer2[4] = "6";
        wrongAnswer2[5] = "46";
        wrongAnswer2[6] = "354";
        wrongAnswer2[7] = "18";
        wrongAnswer2[8] = "7";
        //wrongAnswer2[9] = "41";



        //attach first radio button to op1 variable
        final RadioButton op1 = (RadioButton) findViewById(R.id.option1);

        //attach 2nd radio button to op1 variable
        final RadioButton op2 = (RadioButton) findViewById(R.id.option2);

        //attach 3rd radio button to op1 variable
        final RadioButton op3 = (RadioButton) findViewById(R.id.option3);

        //attach question number textView to questionno variable
        final TextView questionno = (TextView)findViewById(R.id.t1);

        //attach questions textView to question variable
        final TextView question = (TextView)findViewById(R.id.t2);

        //attach next button to NextButton variable
        final Button NextButton = (Button)findViewById(R.id.button);

        //attach Result textView to resultTextView variable
        final TextView resultTextView = (TextView)findViewById(R.id.result);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //initialize  variable to store random number
                int value = 0;

                //generate random number between o to 3 and store the number in 'value' variable
                Random rand = new Random();
               value = rand.nextInt(3);

                questionno.setText("Question # " + value);

                question.setText(questions[count]);


                //if random number generate 0 then the right answer set on 1st radio button
                if (value == 0) {
                    op1.setText(answers[count]);
                    op2.setText(wrongAnswer1[count]);
                    op3.setText(wrongAnswer2[count]);

                }

                //if random number generate 1 then the right answer set on 2nd radio button
                else if (value == 1){

                    op2.setText(answers[count]);
                    op1.setText(wrongAnswer1[count]);
                    op3.setText(wrongAnswer2[count]);


                }

                //if random number generate 2 then the right answer set on 3rd radio button
                else if (value == 2){

                    op3.setText(answers[count]);
                    op2.setText(wrongAnswer1[count]);
                    op1.setText(wrongAnswer2[count]);

                }

                count++;




               if (count == 9){



                   NextButton.setText("Result");






               }





                // int key = radioGroup.getCheckedRadioButtonId();
                //*RadioButton radioButton = (RadioButton)findViewById(key);
                /*if (radioButton.getText().equals("answer 1")){
                    Toast.makeText(MainActivity.this,"yahooo",Toast.LENGTH_SHORT).show();
                }
*/




            }
        });

            }
        });




    }
}





//                   Toast.makeText(MainActivity.this,"10 ho gya" , Toast.LENGTH_SHORT).show();





/*LinearLayout linearLayout = (LinearLayout)findViewById(R.id.parentView);
                   Button resultButton = new Button(MainActivity.this);
                   resultButton.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {

                           resultTextView.setText("kia hoa beta??");

                       }
                   });


                   linearLayout.addView(resultButton);*/










/*switch (value) {

                    case 1:

                        op1.setText(answers[count]);

                        //((RadioButton) radioGroup.getChildAt(value)).setText(answers[count]);
                        value = 0;

                        break;


                    case 2:

                        op2.setText(answers[count]);

                        //((RadioButton) radioGroup.getChildAt(value)).setText(answers[count]);
                        value = 0;

                        break;


                    case 3:

                        op3.setText(answers[count]);

                       // ((RadioButton) radioGroup.getChildAt(value)).setText(answers[count]);
                        value = 0;

                        break;



                }
*/