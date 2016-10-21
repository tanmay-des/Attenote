package com.infinitequarks.tgz.attenote;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

import java.util.ArrayList;

public class AttendanceManagerActivity extends AppCompatActivity{
    AttenoteDatabase mDatabase;
    Subject newSubject = new Subject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = new AttenoteDatabase(this);
        setContentView(R.layout.activity_attendance_manager);

        AttendanceListAdapter adapter = new AttendanceListAdapter(this, generateData());


        ListView listView = (ListView) findViewById(R.id.attendance_list);
        listView.setAdapter(adapter);
    }

    public ArrayList<ItemAttendance> generateData(){
        ArrayList<ItemAttendance> items = new ArrayList<ItemAttendance>();

        Cursor res = mDatabase.getData(newSubject);
        while (res.moveToNext()){
            String res2 = mDatabase.getAttendanceForSubject(res.getString(1));

            items.add(new ItemAttendance(res2,res.getString(1)));
        }

        return items;
    }

}


