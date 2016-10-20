package com.infinitequarks.tgz.attenote;

import android.content.Intent;
import android.database.Cursor;
import android.os.Debug;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SetTimeTableActivity extends AppCompatActivity {

    AttenoteDatabase mDatabase;
    String[] subjectList;
    ListView mListView;
    private TextView mDayTextView;
    private Button mNextButton;
    int i = 0;
    String [] dayArray = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};


    TimeListAdapter currentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_table);

        mNextButton = (Button) findViewById(R.id.time_data_Next);
        mDatabase = new AttenoteDatabase(this);
        subjectList = mDatabase.getSubjectList();

        currentAdapter = new TimeListAdapter(SetTimeTableActivity.this, subjectList, i);
        mDayTextView = (TextView) findViewById(R.id.day_no);
        mDayTextView.setText(dayArray[i]);
        mListView = (ListView) findViewById(R.id.time_data_list);
        mListView.setAdapter(currentAdapter);
        i++;
        for(int i = 0; i < subjectList.length; i++){
            Log.d("HELOO",subjectList[i]);

        }
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i > 5){
                   mNextButton.setText("Finalize");
                   Intent intent = new Intent(SetTimeTableActivity.this,DailyNoteActivity.class);
                    startActivity(intent);
                }
                else if(i<= 5){
                    mDayTextView.setText(dayArray[i]);
                    mListView = (ListView) findViewById(R.id.time_data_list);

                    saveData(currentAdapter.mDataSaver);

                    currentAdapter = new TimeListAdapter(SetTimeTableActivity.this,subjectList,i);
                    mListView.setAdapter(currentAdapter);
                    i++;
                    mNextButton.setEnabled(true);
                }
             }
        });




    }

    public void saveData(HashMap<Integer, TimeListAdapter.DataSaver> data){
        Log.d("HELOO","asdasfasfasfas");
        for (Map.Entry<Integer, TimeListAdapter.DataSaver> integerDataSaverEntry : data.entrySet()) {
            Map.Entry pair = (Map.Entry) integerDataSaverEntry;

            TimeListAdapter.DataSaver dataSaver = (TimeListAdapter.DataSaver) pair.getValue();

            if (dataSaver.isOn) {
                TimeData timeData = new TimeData();
                timeData.setSubjectName(dataSaver.mSubjectName);
                timeData.setDay(i);
                timeData.setIsTrue(1);
                timeData.setStartTime(dataSaver.mStartHour + ":" + dataSaver.mStartMinute);
                timeData.setEndTime(dataSaver.mEndHour + ":" + dataSaver.mEndMinute);

                Log.d("ad",timeData.toString());

                mDatabase.insertData(timeData);
            }
        }

    }

}
