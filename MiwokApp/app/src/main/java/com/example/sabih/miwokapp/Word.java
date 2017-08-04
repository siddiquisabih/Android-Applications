package com.example.sabih.miwokapp;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by sabih on 12/8/2016.
 */
public class Word extends AppCompatActivity{


    private String mDefaultTranslation;
    private String mMiwokTranslation;


    public Word(String DefaultTranslation , String MiwokTranslation){


        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
    }


        public String getDefaultTranslation(){

        return mDefaultTranslation;
    }

    public String getTranslation(){

        return mMiwokTranslation;
    }




    }








