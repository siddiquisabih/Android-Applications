package com.example.user.risk;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }






    // when this button clicked we collect all information from user then
    // show some result according to his/her input
    public void btn(View view){

        // first we get all data from user
        // access data by id's


        // here we get participants names and converted into "String" in second line
        TextView allMemberName = (TextView) findViewById(R.id.namesOfParticipants);
        String Names = allMemberName.getText().toString();


        // here we get Approver name
        TextView approver = (TextView) findViewById(R.id.approverName);
        String approverName = approver.getText().toString();



        // here we get activity  name
        TextView activity = (TextView) findViewById(R.id.activityName);
        String activityName = activity.getText().toString();


        // here we get area of assessment
        TextView assessmentName = (TextView) findViewById(R.id.assessment);
        String assessment = assessmentName.getText().toString();






    }


}
