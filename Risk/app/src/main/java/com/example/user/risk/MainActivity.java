package com.example.user.risk;
import android.os.Environment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;



import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.widget.TextView;

import java.util.Calendar;
import android.widget.DatePicker;



public class MainActivity extends AppCompatActivity {

    TextView tvDate;
    Calendar myCalendar;
    String[] hazard = { "Biological", "Chemical", "Ergonomical", "Physical"};
    String [] severity = {"Very High" , "High" , "Medium" , "Low"};
    String [] probability = {"Extremely High","Often Likely","Likely","Unlikely","Extremely Unlikely"};

    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + '/' + 'a' ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //create file directry
        File dir = new File(path);
        dir.mkdir();



        //Date Picker Code start
        myCalendar = Calendar.getInstance();
        tvDate =  findViewById(R.id.tv_date);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Date Picker End

        //Spinner Code Start

        Spinner spin = findViewById(R.id.sp_picker);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),hazard[i] , Toast.LENGTH_LONG).show();
            }

            //Aye waie...
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,hazard);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);




        setBaseSeverity();
        setActualProbability();
        setActualSeverity();
        setBaseProbability();
    }



    public void setBaseSeverity(){


        Spinner spin = findViewById(R.id.base_severity);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),severity[i] , Toast.LENGTH_LONG).show();
            }

            //Aye waie...
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,severity);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }


    public void setBaseProbability(){
        Spinner spin = findViewById(R.id.base_prob);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),probability[i] , Toast.LENGTH_LONG).show();
            }

            //Aye waie...
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,probability);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);




    }


    public void setActualSeverity(){
        Spinner spin = findViewById(R.id.actual_severity);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),severity[i] , Toast.LENGTH_LONG).show();
            }

            //Aye waie...
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,severity);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }


    public void setActualProbability(){
    Spinner spin = findViewById(R.id.actual_prob);
    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getApplicationContext(),probability[i] , Toast.LENGTH_LONG).show();
        }

        //Aye waie...
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    });

    ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,probability);

    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spin.setAdapter(aa);




}







    // show some result according to his/her input
    // when this button clicked we collect all information from user then
    public void btn(View view){


        //save file
//        File file = new File(path + '/abc.txt');





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


        // here we get date
        TextView datePicker = (TextView) findViewById(R.id.tv_date);
        String date= datePicker.getText().toString();


        Spinner spin = findViewById(R.id.sp_picker);
            String text = spin.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
//        spin.setVisibility(View.GONE);




        // here we get date
        TextView risk = (TextView) findViewById(R.id.riskImpact);
        String riskImpact = risk.getText().toString();



        //here we get existingControl text
        TextView control = (TextView) findViewById(R.id.existingControl);
        String existingControl= control.getText().toString();



        //further control required
        TextView fcontrol = (TextView) findViewById(R.id.furtherControl);
        String furtherControl= fcontrol.getText().toString();




    }





    private void updateLabel() {
        String myFormat = "dd-MMMM-yyyy"; // format In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDate.setText(sdf.format(myCalendar.getTime()));

    }

}
