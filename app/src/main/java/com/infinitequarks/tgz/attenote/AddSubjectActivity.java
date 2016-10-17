package com.infinitequarks.tgz.attenote;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

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
    AttenoteDatabase mDb;
    private Button mBackButton;
    private Button mSaveButton;
    private EditText mSubjectName;
    private CheckBox mIsTheory;
    private Subject mSubject = new Subject();
    private static final String EXTRA_SUBJECT = "com.infinitequarks.tgz.attenote";

    public static Subject getSubjectIntent(Intent result){
        Subject newSubject = (Subject) result.getSerializableExtra(EXTRA_SUBJECT);
        return newSubject;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        mDb = new AttenoteDatabase(this);
        mBackButton = (Button) findViewById(R.id.sub_back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mSaveButton = (Button) findViewById(R.id.sub_save_button);
        mSaveButton.setEnabled(false);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean datainserted =  mDb.insertData(mSubject.getName().toString(),mSubject.isTheory());
                if(datainserted){
//                    Toast.makeText(AddSubjectActivity.this , "DATA INSERTED",Toast.LENGTH_SHORT).show();
                    setSubject(mSubject);

                }

            }
        });


        mIsTheory = (CheckBox) findViewById(R.id.is_theory);
        mIsTheory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSubject.setTheory(isChecked);
            }
        });


        mSubjectName = (EditText) findViewById(R.id.subject_name);
        mSubjectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSubject.setName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s!=null){
                    mSaveButton.setEnabled(true);
                }

            }
        });

    }

    private void setSubject(Subject newSubject){
        Subject subject = newSubject;
        String name = subject.getName();
        boolean theory = subject.isTheory();
        Intent data = new Intent();

        data.putExtra(EXTRA_SUBJECT, subject);

        setResult(RESULT_OK, data);
        finish();
    }
}
