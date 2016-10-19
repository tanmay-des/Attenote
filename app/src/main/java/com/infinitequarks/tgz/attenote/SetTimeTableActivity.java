package com.infinitequarks.tgz.attenote;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

import java.util.ArrayList;

public class SetTimeTableActivity extends AppCompatActivity {

    AttenoteDatabase mDatabase;
    String[] subjectList;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_table);
        mDatabase = new AttenoteDatabase(this);
        subjectList = mDatabase.getSubjectList();
        mListView = (ListView) findViewById(R.id.time_data_list);

        mListView.setAdapter(new TimeListAdapter(this,subjectList));

    }



}
