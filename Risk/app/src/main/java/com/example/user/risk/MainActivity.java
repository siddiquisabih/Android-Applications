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

    String FileNameForCreate = "";

    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + '/' + 'a' ;
    File dir;

    Button getResuntBtn;

    boolean isBilogical,isChemical,isErgo,isPhy = false;

   boolean isBaseVeryHigh , isBaseHigh , isBaseMedium, isBaseLow = false;

   boolean isBaseProbExtremHigh , isBaseProbOftenLikely , isBaseProbLikely, isBaseProbUnlikely , isBaseProbExtremUnlikely = false;




    boolean isActualVeryHigh , isActualHigh , isActualMedium, isActualLow = false;

    boolean isActualProbExtremHigh , isActualProbOftenLikely , isActualProbLikely, isActualProbUnlikely , isActualProbExtremUnlikely = false;



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
//                Toast.makeText(getApplicationContext(),hazard[i] , Toast.LENGTH_LONG).show();

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


        final Spinner spin = findViewById(R.id.base_severity);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),severity[i] , Toast.LENGTH_LONG).show();

                isBaseVeryHigh=false;
                isBaseHigh=false;
                isBaseMedium=false;
                isBaseLow=false;


                if(spin.getSelectedItem().toString().trim() == "Very High"){
                    isBaseVeryHigh = true;
                }

                if(spin.getSelectedItem().toString().trim() == "High"){
                    isBaseHigh = true;
                }

                if(spin.getSelectedItem().toString().trim() == "Medium"){
                    isBaseMedium = true;
                }

                if(spin.getSelectedItem().toString().trim() == "Low"){
                    isBaseLow = true;
                }













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
        final Spinner spin = findViewById(R.id.base_prob);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),probability[i] , Toast.LENGTH_LONG).show();

                isBaseProbExtremHigh=false;
                isBaseProbOftenLikely=false;
                isBaseProbLikely=false;
                isBaseProbUnlikely=false;
                isBaseProbExtremUnlikely = false;



                if(spin.getSelectedItem().toString().trim() == "Extremely High"){
                    isBaseProbExtremHigh = true;
                }
                if(spin.getSelectedItem().toString().trim() == "Often Likely"){
                    isBaseProbOftenLikely = true;
                }
                if(spin.getSelectedItem().toString().trim() == "Likely"){
                    isBaseProbLikely = true;
                }
                if(spin.getSelectedItem().toString().trim() == "Unlikely"){
                    isBaseProbUnlikely = true;
                }
                if(spin.getSelectedItem().toString().trim() == "Extremely Unlikely"){
                    isBaseProbExtremUnlikely = true;
                }







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
        final Spinner spin = findViewById(R.id.actual_severity);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),severity[i] , Toast.LENGTH_LONG).show();



//                String [] severity = {"Very High" , "High" , "Medium" , "Low"};

                isActualVeryHigh= false;
                isActualHigh= false;
                isActualMedium= false;
                isActualLow = false;




                if(spin.getSelectedItem().toString().trim() == "Very High"){
                    isActualVeryHigh= true;
                }

                if(spin.getSelectedItem().toString().trim() == "High"){
                    isActualHigh= true;
                }

                if(spin.getSelectedItem().toString().trim() == "Medium"){
                    isActualMedium = true;
                }

                if(spin.getSelectedItem().toString().trim() == "Low"){
                    isActualLow = true;
                }









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
        final Spinner spin = findViewById(R.id.actual_prob);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),probability[i] , Toast.LENGTH_LONG).show();

                isActualProbExtremHigh=false;
                isActualProbOftenLikely=false;
                isActualProbLikely=false;
                isActualProbUnlikely=false;
                isActualProbExtremUnlikely = false;




                if(spin.getSelectedItem().toString().trim() == "Extremely High"){
                    isActualProbExtremHigh = true;
                }
                if(spin.getSelectedItem().toString().trim() == "Often Likely"){
                    isActualProbOftenLikely= true;
                }
                if(spin.getSelectedItem().toString().trim() == "Likely"){
                    isActualProbLikely = true;
                }
                if(spin.getSelectedItem().toString().trim() == "Unlikely"){
                    isActualProbUnlikely= true;
                }
                if(spin.getSelectedItem().toString().trim() == "Extremely Unlikely"){
                    isActualProbExtremUnlikely = true;
                }







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



            TextView descriptionRisk = (TextView) findViewById(R.id.descriptionRisk);
            String description = descriptionRisk.getText().toString();





            // here we get Approver name
            TextView approver = (TextView) findViewById(R.id.approverName);
            String approverName = approver.getText().toString();



            // here we get activity  name
            TextView activity = (TextView) findViewById(R.id.activityName);
            String activityName = activity.getText().toString();


            // here we get area of assessment
            TextView assessmentName = (TextView) findViewById(R.id.assessment);
            String assessment = assessmentName.getText().toString();
            FileNameForCreate = assessment;

            // here we get date
            TextView datePicker = (TextView) findViewById(R.id.tv_date);
            String date= datePicker.getText().toString();


            Spinner spin = findViewById(R.id.sp_picker);
            String text = spin.getSelectedItem().toString();
//            Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
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
            document.setMargins(4, 4, 4, 4);
            Rectangle two = new Rectangle(650,1000);
            document.setPageSize(two);
            try {
                PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(dir + "/"+ assessment + ".pdf"));
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
                topTable.setWidthPercentage(100);
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



                document.add(topTable);


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


            PdfPCell cell = new PdfPCell(new Phrase("Hazards Involved     (HI)"));
            cell.setFixedHeight(30);
            //cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(4);
            table.addCell(cell);


            PdfPCell cell1 = new PdfPCell(new Phrase("Ergonomical   (E)"));
            cell1.setFixedHeight(30);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setColspan(1);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase("Physical       (Ph)"));
            cell2.setFixedHeight(30);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setColspan(1);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Phrase("Chemical      (Ch)"));
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

                /*Last Table goes here*/


                PdfPTable TableParent = new PdfPTable(1);
                TableParent.setWidthPercentage(100);

                PdfPTable lastTableParent = new PdfPTable(15);

                PdfPCell OS = new PdfPCell(new Phrase("OH Hazard"));
                OS.setColspan(2);
                OS.setHorizontalAlignment(Element.ALIGN_CENTER);
                OS.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
                OS.setBackgroundColor(BaseColor.LIGHT_GRAY);
                PdfPCell HI = new PdfPCell(new Phrase("HI"));
                HI.setColspan(1);
                HI.setBackgroundColor(BaseColor.LIGHT_GRAY);
                HI.setHorizontalAlignment(Element.ALIGN_CENTER);
                HI.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell IMPACT = new PdfPCell(new Phrase("IMPACT"));
                IMPACT.setColspan(2);
                IMPACT.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell BASE = new PdfPCell(new Phrase("BASE RISK"));
                BASE.setColspan(2);
                BASE.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell EXISTING = new PdfPCell(new Phrase("EXISTING CONTROL"));
                EXISTING.setColspan(2);
                EXISTING.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell ACTUAL = new PdfPCell(new Phrase("ACTUAL RISK"));
                ACTUAL.setColspan(2);
                ACTUAL.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell FurtherControlMeanuse = new PdfPCell(new Phrase("Further Control Measure"));
                FurtherControlMeanuse.setColspan(2);
                FurtherControlMeanuse.setHorizontalAlignment(Element.ALIGN_CENTER);


                lastTableParent.addCell(OS);
                lastTableParent.addCell(HI);
                lastTableParent.addCell(IMPACT);
                //lastTableParent.addCell(BASE);



                PdfPTable BASETable = new PdfPTable(3);
                BASETable.setWidthPercentage(100);
                PdfPCell baseRisk = new PdfPCell(new Phrase("Base Risk"));
                baseRisk.setColspan(3);
                baseRisk.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Sey = new PdfPCell(new Phrase("Sev"));
                Sey.setColspan(1);
                Sey.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Prob = new PdfPCell(new Phrase("Prob"));
                Prob.setColspan(1);
                Prob.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell RR = new PdfPCell(new Phrase("RR"));
                RR.setColspan(1);
                RR.setHorizontalAlignment(Element.ALIGN_CENTER);

                BASETable.addCell(baseRisk);
                BASETable.addCell(Sey);
                BASETable.addCell(Prob);
                BASETable.addCell(RR);


                PdfPCell y1 = new PdfPCell();
                y1.addElement(BASETable);
                y1.setColspan(3);
                y1.setPadding(0);
                //y1.setBorder(Rectangle.NO_BORDER);

                lastTableParent.addCell(y1);
                lastTableParent.addCell(EXISTING);


                PdfPTable ActualTable = new PdfPTable(3);
                ActualTable.setWidthPercentage(100);

                PdfPCell baseRisk1 = new PdfPCell(new Phrase("Actual Risk"));
                baseRisk1.setColspan(3);
                baseRisk1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Sey1 = new PdfPCell(new Phrase("Sev"));
                Sey1.setColspan(1);
                Sey1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Prob1 = new PdfPCell(new Phrase("Prob"));
                Prob1.setColspan(1);
                Prob1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell RR1 = new PdfPCell(new Phrase("RR"));
                RR1.setColspan(1);
                RR1.setHorizontalAlignment(Element.ALIGN_CENTER);

                ActualTable.addCell(baseRisk1);
                ActualTable.addCell(Sey1);
                ActualTable.addCell(Prob1);
                ActualTable.addCell(RR1);


                PdfPCell y11 = new PdfPCell();
                y11.addElement(ActualTable);
                y11.setColspan(3);
                y11.setPadding(0);

                lastTableParent.addCell(y11);
                lastTableParent.addCell(FurtherControlMeanuse);

                lastTableParent.setWidthPercentage(100);

                PdfPCell pCell = new PdfPCell(lastTableParent);
                pCell.setBorder(Rectangle.NO_BORDER);
                pCell.setColspan(1);
                TableParent.addCell(pCell);




                //data entry start

                //for(int a=0;a<10;a++){

                PdfPTable lastTableParent1 = new PdfPTable(15);

                PdfPCell OS1 = new PdfPCell(new Phrase(description));
                OS1.setColspan(2);
                OS1.setHorizontalAlignment(Element.ALIGN_CENTER);
                OS1.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
                //OS.setBackgroundColor(BaseColor.LIGHT_GRAY);
                PdfPCell HI1 = new PdfPCell(new Phrase((isBilogical)?"(B)": (isChemical)? "(Ch)": (isPhy)? "(Ph)": (isErgo)?"(E)":""));
                HI1.setColspan(1);
                //HI.setBackgroundColor(BaseColor.LIGHT_GRAY);
                HI1.setHorizontalAlignment(Element.ALIGN_CENTER);
                HI1.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell IMPACT1 = new PdfPCell(new Phrase(riskImpact));
                IMPACT1.setColspan(2);
                IMPACT1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell BASE1 = new PdfPCell(new Phrase("BASE RISK"));     // isko dekhna hai bad mai
                BASE1.setColspan(2);
                BASE1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell EXISTING1 = new PdfPCell(new Phrase(existingControl));
                EXISTING1.setColspan(2);
                EXISTING1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell ACTUAL1 = new PdfPCell(new Phrase("ACTUAL RISK"));
                ACTUAL1.setColspan(2);
                ACTUAL1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell FurtherControlMeanuse1 = new PdfPCell(new Phrase(furtherControl));
                FurtherControlMeanuse1.setColspan(2);
                FurtherControlMeanuse1.setHorizontalAlignment(Element.ALIGN_CENTER);


                lastTableParent1.addCell(OS1);
                lastTableParent1.addCell(HI1);
                lastTableParent1.addCell(IMPACT1);
                //lastTableParent.addCell(BASE);



                PdfPTable BASETable1 = new PdfPTable(3);
                BASETable1.setWidthPercentage(100);
//                PdfPCell baseRisk2 = new PdfPCell(new Phrase("Base Risk"));
//                baseRisk2.setColspan(3);
//                baseRisk2.setHorizontalAlignment(Element.ALIGN_CENTER);   //yac
                PdfPCell Sey2 = new PdfPCell(new Phrase(
                        (isBaseVeryHigh)? "(I)": (isBaseHigh)? "(II)": (isBaseMedium)? "(III)" : (isBaseLow)? "(IV)" : ""
                ));
                Sey2.setColspan(1);
                Sey2.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Prob2 = new PdfPCell(new Phrase(
                        (isBaseProbExtremHigh)?"(A)":(isBaseProbOftenLikely) ? "(B)": (isBaseProbLikely) ? "(C)": (isBaseProbUnlikely)? "(D)": (isBaseProbExtremUnlikely)? "(E)": ""
                ));
                Prob2.setColspan(1);
                Prob2.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell RR2 = new PdfPCell(new Phrase("RR"));
                RR2.setColspan(1);
                RR2.setHorizontalAlignment(Element.ALIGN_CENTER);

//                BASETable1.addCell(baseRisk2);
                BASETable1.addCell(Sey2);
                BASETable1.addCell(Prob2);
                BASETable1.addCell(RR2);


                PdfPCell y12 = new PdfPCell();
                y12.addElement(BASETable1);
                y12.setColspan(3);
                y12.setPadding(0);
                //y1.setBorder(Rectangle.NO_BORDER);

                lastTableParent1.addCell(y12);
                lastTableParent1.addCell(EXISTING1);


                PdfPTable ActualTable1 = new PdfPTable(3);
                ActualTable1.setWidthPercentage(100);

//                PdfPCell baseRisk4 = new PdfPCell(new Phrase("Actual Risk"));
//                baseRisk4.setColspan(3);
//                baseRisk4.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Sey4 = new PdfPCell(new Phrase(

                (isActualVeryHigh)? "(I)": (isActualHigh)? "(II)": (isActualMedium)? "(III)" : (isActualLow)? "(IV)" : ""

                ));
                Sey4.setColspan(1);
                Sey4.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell Prob4 = new PdfPCell(new Phrase(
                (isActualProbExtremHigh)?"(A)":(isActualProbOftenLikely) ? "(B)": (isActualProbLikely) ? "(C)": (isActualProbUnlikely)? "(D)": (isActualProbExtremUnlikely )? "(E)": ""
               ));
                Prob4.setColspan(1);
                Prob1.setHorizontalAlignment(Element.ALIGN_CENTER);
                PdfPCell RR4 = new PdfPCell(new Phrase("RR"));
                RR4.setColspan(1);
                RR4.setHorizontalAlignment(Element.ALIGN_CENTER);

//                ActualTable1.addCell(baseRisk4);
                ActualTable1.addCell(Sey4);
                ActualTable1.addCell(Prob4);
                ActualTable1.addCell(RR4);


                PdfPCell y15 = new PdfPCell();
                y15.addElement(ActualTable1);
                y15.setColspan(3);
                y15.setPadding(0);

                lastTableParent1.addCell(y15);
                lastTableParent1.addCell(FurtherControlMeanuse1);

                lastTableParent1.setWidthPercentage(100);

                PdfPCell pCel5 = new PdfPCell(lastTableParent1);
                pCel5.setBorder(Rectangle.NO_BORDER);
                pCel5.setColspan(1);
                TableParent.addCell(pCel5);
                //}
                document.add(TableParent);


            } catch (DocumentException e) {
                e.printStackTrace();
            }
            document.close();
            Toast.makeText(MainActivity.this,   FileNameForCreate  + ".pdf Created Successfully",Toast.LENGTH_LONG).show();

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
