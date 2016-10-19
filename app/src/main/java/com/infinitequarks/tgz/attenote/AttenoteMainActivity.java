package com.infinitequarks.tgz.attenote;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

import java.util.ArrayList;
import java.util.List;


public class AttenoteMainActivity extends AppCompatActivity {

    AttenoteDatabase mDb;
    private Button mAddSubButton;
    private int REQUEST_CODE = 0;
    private Button mSetTimeTableButton;
    private Subject newSubject;
    private List<Subject> mSubjects = new ArrayList<>();
    private LinearLayout mSubjectList;
    private int count = 1;

    private static final String EXTRA_SUBJECT = "com.infinitequarks.tgz.attenote";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attenote_main);
        mDb = new AttenoteDatabase(this);
        mSubjectList = (LinearLayout) findViewById(R.id.subject_list);


        showData();
        mAddSubButton = (Button) findViewById(R.id.add_subject);

        mAddSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttenoteMainActivity.this , AddSubjectActivity.class);
                mSubjectList.removeAllViews();
                startActivityForResult(intent , REQUEST_CODE);
            }
        });

        mSetTimeTableButton = (Button) findViewById(R.id.add_timetable);

        mSetTimeTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttenoteMainActivity.this, SetTimeTableActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE){
            if(data == null){
                showData();
                return;

//                Subject mSubject = (Subject) intent.getSerializableExtra("EXTRA_SUBJECT");
            }

            newSubject = AddSubjectActivity.getSubjectIntent(data);
            mSubjects.add(newSubject);
            showData();

//            TextView mlistItem = new TextView(this);
//            mlistItem.setPadding(20,20,20,20);
//            mlistItem.setTextSize(14);
//            mlistItem.setGravity(Gravity.CENTER);
//            mlistItem.setText("Subjet Name: "+  newSubject.getName());
//            mlistItem.setLayoutParams(new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));
//            if(count%2==0)
//                mlistItem.setBackgroundColor(0xFFFFFFFF);
//            count++;
//            mSubjectList.addView(mlistItem);

        }
    }

    private void showData(){
        Cursor res = mDb.getData(newSubject);
        if(res.getCount()== 0){
            TextView mlistItem = new TextView(this);
            mlistItem.setPadding(20,20,20,20);
            mlistItem.setTextSize(14);
            mlistItem.setGravity(Gravity.CENTER);
            mlistItem.setText("No Subjects Added");
            mlistItem.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            mSubjectList.addView(mlistItem);
        }

            while (res.moveToNext()){

                TextView mlistItem = new TextView(this);
                mlistItem.setPadding(20,20,20,20);
                mlistItem.setTextSize(14);
                mlistItem.setGravity(Gravity.CENTER);
                mlistItem.setText("ID: "+  res.getString(0) + " Subject Name: " + res.getString(1) );
                if(res.getInt(0)%2==0)
                mlistItem.setBackgroundColor(0xFFFFFFFF);
                mlistItem.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                mSubjectList.addView(mlistItem);
            }

    }



}
