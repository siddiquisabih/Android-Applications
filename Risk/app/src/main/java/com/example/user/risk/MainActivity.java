package com.example.user.risk;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.widget.TextView;

import java.util.Calendar;
import android.widget.DatePicker;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;


public class MainActivity extends AppCompatActivity {

    TextView tvDate;
    Calendar myCalendar;
    String[] hazard = { "Biological", "Chemical", "Ergonomical", "Physical"};
    String [] severity = {"Very High" , "High" , "Medium" , "Low"};
    String [] probability = {"Extremely High","Often Likely","Likely","Unlikely","Extremely Unlikely"};

    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + '/' + 'a' ;
    File dir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //create file directry
        dir = new File(path);
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
    public void btn(View view) throws FileNotFoundException, DocumentException {


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

        Rectangle pagesize = new Rectangle(216f, 720f);

        Document document = new Document();

        PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(dir + "/abc.pdf"));
        //Rotate event = new Rotate();
        //writer.setPageEvent(event);

        document.open();


        document.add(new Paragraph("Hello World! Hello People! " +
                "Hello Sky! Hello Sun! Hello Moon! Hello Stars!"));



        PdfPTable table = new PdfPTable(4);
        //table.setTotalWidth(new float[]{ 160, 120 });
        //table.setLockedWidth(true);

        PdfPCell cell = new PdfPCell(new Phrase("Hazards Involved(HI)"));
        cell.setFixedHeight(30);
        //cell.setBorder(Rectangle.NO_BORDER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        table.addCell(cell);


        PdfPCell cell1 = new PdfPCell(new Phrase("Ergonomical(E)"));
        cell1.setFixedHeight(30);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setColspan(1);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase("Physical  "));
        cell2.setFixedHeight(30);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setColspan(1);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Phrase("Chemical(Ch)"));
        cell3.setFixedHeight(30);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setColspan(1);
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Phrase("Biological(B)"));
        cell4.setFixedHeight(30);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setColspan(1);
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell(new Phrase("Y"));
        cell5.setFixedHeight(30);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setColspan(1);
        table.addCell(cell5);

        PdfPCell cell6 = new PdfPCell(new Phrase("Y"));
        cell6.setFixedHeight(30);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell6.setColspan(1);
        table.addCell(cell6);

        PdfPCell cell7 = new PdfPCell(new Phrase("N"));
        cell7.setFixedHeight(30);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setColspan(1);
        table.addCell(cell7);

        PdfPCell cell8 = new PdfPCell(new Phrase("N"));
        cell8.setFixedHeight(30);
        cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell8.setColspan(1);
        table.addCell(cell8);



        document.add(table);


        document.close();

    }

    public class Rotate extends PdfPageEventHelper {

        protected PdfNumber orientation = PdfPage.LANDSCAPE;

        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }




    private void updateLabel() {
        String myFormat = "dd-MMMM-yyyy"; // format In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDate.setText(sdf.format(myCalendar.getTime()));

    }

}
