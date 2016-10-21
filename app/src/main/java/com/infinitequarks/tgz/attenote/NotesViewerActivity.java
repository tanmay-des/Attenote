package com.infinitequarks.tgz.attenote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

import java.util.ArrayList;

public class NotesViewerActivity extends AppCompatActivity {

    AttenoteDatabase mDatabase;
    private ArrayList<String> values  = new ArrayList<>();
    private ListView mListView ;
    private Subject subject = new Subject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_viewer);
        mDatabase = new AttenoteDatabase(this);

        Cursor res = mDatabase.getData(subject);
        while (res.moveToNext()){
            values.add(res.getString(1));
        }

        mListView = (ListView) findViewById(R.id.notes_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition  = position;

                // ListView Clicked item value
                String  itemValue  = (String) mListView.getItemAtPosition(position);

                Intent intent = SingleNoteViewActivity.getIntent(view.getContext(),itemValue);
                startActivity(intent);

                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();

            }

        });
    }
}


