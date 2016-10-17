package com.infinitequarks.tgz.attenote;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddSubjectActivity extends AppCompatActivity {
//
//    private static final String EXTRA_SUBJECT =
//            "ADD_SUBJECT";
//
//    private static Intent newIntent(Context packageContext , String SubjectName){
//        Intent i  = new Intent(packageContext, AddSubjectActivity.class);
//        i.putExtra(EXTRA_SUBJECT , SubjectName);
//        return i;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
    }


}
