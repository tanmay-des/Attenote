package com.infinitequarks.tgz.attenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DailyNoteActivity extends AppCompatActivity {

    private TextView mTextViewDay;
    private TextView mTextViewTime;
    private TextView mTextViewClass;
    private TextView mTextViewAttend;
    private String  dayno;
    AttenoteDatabase mDatabase;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_note);

        mDatabase = new AttenoteDatabase(this);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        DateFormat df = new SimpleDateFormat("h:mm a");
        String date_now = df.format(Calendar.getInstance().getTime());

        Date date = calendar.getTime();
        switch (day) {
            case Calendar.SUNDAY:
                dayno = "6";

                // Current day is Sunday
                break;
            case Calendar.MONDAY:
                dayno = "0";

                // Current day is Monday
                break;
            case Calendar.TUESDAY:
                dayno = "1";
                // etc.
                break;

            case Calendar.WEDNESDAY:
                dayno = "2";

                break;
            case Calendar.THURSDAY:
                dayno = "3";
                break;

            case Calendar.FRIDAY:
                dayno = "4";

                break;
            case Calendar.SATURDAY:
                dayno = "5";

                break;
        }
        ArrayList<TimeData> dailyList = mDatabase.getDailyData(dayno);
        Log.d("DATA" , dailyList.toString());

//        mDatabase.viewTableData();

        mTextViewDay = (TextView) findViewById(R.id.daily_day);
        mTextViewTime = (TextView) findViewById(R.id.daily_time);
        mTextViewTime.setText(date_now);
        mTextViewDay.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()) );
//
////
//        mTextViewClass = (TextView) findViewById(R.id.daily_class);
//        mTextViewClass.setText(dailyList.get(1).getSubjectName());

        mRecyclerView = (RecyclerView) findViewById(R.id.daily_list_view);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DailyListRecyclerViewAdapter(dailyList,this,dayno);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        MenuInflater mymenu = getMenuInflater();
        mymenu.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.link_attendance_manager:
                Intent i= new Intent(this,AttendanceManagerActivity.class);
                startActivity(i);
                break;

            case R.id.link_notes_manager:
                Intent i1= new Intent(this,NotesViewerActivity.class);
                startActivity(i1);
                break;

            case R.id.link_settings:
                Intent i2= new Intent(this,AttenoteMainActivity.class);
                startActivity(i2);
                break;


        }

        return false;
    }
}
