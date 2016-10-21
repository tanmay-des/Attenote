package com.infinitequarks.tgz.attenote;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

public class NoteActivity extends AppCompatActivity {

    private static final String NOTE_GET_EXTRA = "getdateandsubject";
    private static final String NOTE_GET = "addgetdateandsubject";
    private String mSubjectName;
    private String mDate;
    private TextView mTextViewSubject;
    private EditText mEditTextTitle;
    private EditText mEditTextNote;
    private Button mButtonSave;
    AttenoteDatabase mDatabase;


    public static Intent noteIntent(Context context , String subjectName , String date){
        Intent intent = new Intent(context,NoteActivity.class);
        intent.putExtra(NOTE_GET_EXTRA,subjectName);
        intent.putExtra(NOTE_GET,date);

        return intent;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note2);

        mDatabase = new AttenoteDatabase(this);



        mSubjectName = getIntent().getStringExtra(NOTE_GET_EXTRA);
        mDate = getIntent().getStringExtra(NOTE_GET);

        mDatabase.notesInitializer(mDate,mSubjectName);
        String[] mString = mDatabase.getNotes(mDate,mSubjectName);

        mTextViewSubject = (TextView) findViewById(R.id.note_subject);
        mTextViewSubject.setText(mSubjectName);

        mEditTextTitle = (EditText) findViewById(R.id.note_edit_text_title);
        mEditTextTitle.setText(mString[1]);

        mEditTextNote = (EditText) findViewById(R.id.note_edit_text_note);
        mEditTextNote.setText(mString[0]);

        mButtonSave = (Button) findViewById(R.id.note_save_button);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notes = mEditTextNote.getText().toString();
                String title = mEditTextTitle.getText().toString();

                mDatabase.notesUpdater(notes,title,mDate,mSubjectName);

                Toast.makeText(v.getContext(),"Notes Saved!",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
