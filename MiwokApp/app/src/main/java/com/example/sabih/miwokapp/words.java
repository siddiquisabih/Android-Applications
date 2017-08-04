package com.example.sabih.miwokapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by sabih on 1/4/2017.
 */
public class words extends AppCompatActivity {

    private String mMiwokTranslation;
    private String mEnglishTranslation;


    public words(String MiwokTranslation, String EnglishTranslation){

        mMiwokTranslation = MiwokTranslation;
        mEnglishTranslation = EnglishTranslation;

    }

    public String getMiwokTranslation(){

        return mMiwokTranslation;

    }


    public String getEnglishTranslation(){

        return mEnglishTranslation;

    }

}

