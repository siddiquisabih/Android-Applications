package com.example.user.risk;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;



import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.widget.TextView;

import java.util.Calendar;
import android.widget.DatePicker;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
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

    Button getResuntBtn;

    boolean isBilogical,isChemical,isErgo,isPhy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getResuntBtn = findViewById(R.id.getResuntBtn);

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

        final Spinner spin = findViewById(R.id.sp_picker);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),hazard[i] , Toast.LENGTH_LONG).show();

                isBilogical=false;
                isChemical=false;
                isErgo=false ;
                isPhy = false;
                if(spin.getSelectedItem().toString().trim() == "Biological"){
                    isBilogical = true;
                }

                if(spin.getSelectedItem().toString().trim() == "Chemical"){
                    isChemical = true;
                }

                if(spin.getSelectedItem().toString().trim() == "Ergonomical"){
                    isErgo = true;
                }

                if(spin.getSelectedItem().toString().trim() == "Physical"){
                    isPhy = true;
                }


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

    getResuntBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            generatePDF();
        }
    });
}


    private void generatePDF(){
        if(!checkIfAlreadyhavePermissionFileWrite(MainActivity.this)){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
        }else{

            dir = new File(path);
            if(!dir.exists()){
                dir.mkdir();
            }


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

            Document document = new Document();

            try {
                PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(dir + "/abc.pdf"));
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Rotate event = new Rotate();
            //writer.setPageEvent(event);

            document.open();


            try {

                PdfPTable topTable = new PdfPTable(2);
                topTable.getDefaultCell().setBorder(0);
                PdfPCell ba = new PdfPCell(new Phrase("Activity: " + activityName));
                PdfPCell b = new PdfPCell(new Phrase("Participant: " + Names));
                PdfPCell c = new PdfPCell(new Phrase("Depart/Area of Assessment: " + assessment));
                PdfPCell d = new PdfPCell(new Phrase("Approved By: " + approverName));
                PdfPCell e = new PdfPCell(new Phrase("Hira Date: " + date));
                PdfPCell f = new PdfPCell(new Phrase(""));



                ba.setBorder(Rectangle.NO_BORDER);
                b.setBorder(Rectangle.NO_BORDER);
                c.setBorder(Rectangle.NO_BORDER);
                d.setBorder(Rectangle.NO_BORDER);
                e.setBorder(Rectangle.NO_BORDER);
                f.setBorder(Rectangle.NO_BORDER);


                d.setHorizontalAlignment(Element.ALIGN_RIGHT);
                b.setHorizontalAlignment(Element.ALIGN_RIGHT);


                topTable.addCell(ba);
                topTable.addCell(b);
                topTable.addCell(c);
                topTable.addCell(d);
                topTable.addCell(e);
                topTable.addCell(f);

//                ba.setBorder(0);
//                b.setBorder(0);
//                c.setBorder(0);
//                d.setBorder(0);
//                e.setBorder(0);
//                f.setBorder(0);


                document.add(topTable);

                /*//paragraph
                Paragraph activityText = new Paragraph("Activity: " + activityName);
                activityText.setAlignment(Element.ALIGN_LEFT);

                Paragraph NameOfMember = new Paragraph("Participant: " + Names);
                NameOfMember.setAlignment(Element.ALIGN_RIGHT);

                Paragraph DepartAreaName = new Paragraph("Depart/Area of Assessment: " + assessment);
                DepartAreaName.setAlignment(Element.ALIGN_LEFT);

                Paragraph ApprovedBy = new Paragraph("Approved By: " + approverName);
                ApprovedBy.setAlignment(Element.ALIGN_RIGHT);

                Paragraph HiraDate = new Paragraph("Hira Date: " + date);
                HiraDate.setAlignment(Element.ALIGN_LEFT);


                document.add(activityText);
                document.add(NameOfMember);
                document.add(DepartAreaName);
                document.add(ApprovedBy);
                document.add(HiraDate);*/








 


//                document.add(p1);
//                document.add(p2);



            } catch (DocumentException e) {
                e.printStackTrace();
            }


            Image image = null;
            try {
                // get input stream
                InputStream ims = getAssets().open("image_1.png");
                Bitmap bmp = BitmapFactory.decodeStream(ims);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                image = Image.getInstance(stream.toByteArray());
                //image.setAbsolutePosition(250f, 10f);
                image.scalePercent(30f);
                image.setAlignment(Element.ALIGN_RIGHT);
                //document.add(image);
                document.add(new Phrase("\n"));
            }
            catch(IOException ex)
            {
                Log.e("TEXT",""+ex.toString());
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            PdfPTable table1 = new PdfPTable(2);
            table1.setWidthPercentage(100);

            try {
                table1.setWidths(new int[]{2, 1});
            } catch (DocumentException e) {
                e.printStackTrace();
            }


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

            PdfPCell cell2 = new PdfPCell(new Phrase("Physical (Ph)"));
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

            PdfPCell cell5 = new PdfPCell(new Phrase(((isErgo)?"Y":"N")));
            cell5.setFixedHeight(30);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setColspan(1);
            table.addCell(cell5);

            PdfPCell cell6 = new PdfPCell( new Phrase(((isPhy)?"Y":"N")));
            cell6.setFixedHeight(30);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setColspan(1);
            table.addCell(cell6);

            PdfPCell cell7 = new PdfPCell(new Phrase(((isChemical)?"Y":"N")));
            cell7.setFixedHeight(30);
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setColspan(1);
            table.addCell(cell7);

            PdfPCell cell8 = new PdfPCell(new Phrase(((isBilogical)?"Y":"N")));
            cell8.setFixedHeight(30);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setColspan(1);
            table.addCell(cell8);
            try {
                //document.add(table);
                PdfPCell y = new PdfPCell();
                y.addElement(table);
                y.setBorder(Rectangle.NO_BORDER);

                PdfPCell imgCell = new PdfPCell();
                imgCell.addElement(image);
                imgCell.setBorder(Rectangle.NO_BORDER);

                table1.addCell(y);
                table1.addCell(imgCell);

                table1.getDefaultCell().setBorder(0);
                document.add(table1);


                Paragraph Note1 = new Paragraph("1 Note: Please identify the type of Hazard involved in HI column i.e Ph, Ch, B & E against each OH&S Hazard.");
                Paragraph Note2 = new Paragraph("2 Note: Please specify the impact of Hazard in term of injury/Disease or Major/Minor loss in Asserts in impact column, if required");


                document.add(Note1);
                document.add(Note2);






            } catch (DocumentException e) {
                e.printStackTrace();
            }
            document.close();
        }
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


    public static boolean checkIfAlreadyhavePermissionFileWrite(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }


    private void updateLabel() {
        String myFormat = "dd-MMMM-yyyy"; // format In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDate.setText(sdf.format(myCalendar.getTime()));

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


            generatePDF();
            Log.e("PDF","GENERATED");

        }else{
            Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
        }
    }
}
