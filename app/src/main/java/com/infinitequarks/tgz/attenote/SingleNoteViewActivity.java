package com.infinitequarks.tgz.attenote;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

import java.util.ArrayList;

public class SingleNoteViewActivity extends AppCompatActivity {

    private static final String SUBJECT_EXTRA = "extra for subject";
    AttenoteDatabase mDatabase;
    private ListView listview;
    private TextView subjectName;
    private String mSubjectName;


    public static Intent getIntent(Context context,String subjectname){
        Intent intent = new Intent(context,SingleNoteViewActivity.class);
        intent.putExtra(SUBJECT_EXTRA,subjectname);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = new AttenoteDatabase(this);
        setContentView(R.layout.activity_single_note_view);

        mSubjectName = getIntent().getStringExtra(SUBJECT_EXTRA);

        subjectName = (TextView) findViewById(R.id.note_view_single_text_view);
        subjectName.setText(mSubjectName);



        NoteListAdapter adapter = new NoteListAdapter(this,generateData());
        listview = (ListView) findViewById(R.id.note_view_single_list_view);
        listview.setAdapter(adapter);
    }

    public ArrayList<NotesItem> generateData(){
        ArrayList<NotesItem> items = new ArrayList<NotesItem>();

        Cursor res = mDatabase.getNotesForSubject(mSubjectName);
        while (res.moveToNext()){
            NotesItem mItem = new NotesItem(res.getString(4),res.getString(3),res.getString(2));
            items.add(mItem);
        }
        return items;
    }
}
