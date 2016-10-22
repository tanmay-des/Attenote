package com.infinitequarks.tgz.attenote;

import android.content.Intent;
import android.os.Handler;
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
import java.text.ParseException;
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
    private ArrayList<String []>  newTimeSubjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_note);

        mDatabase = new AttenoteDatabase(this);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        DateFormat df = new SimpleDateFormat("HH:mm ");




        final String date_now = df.format(Calendar.getInstance().getTime());

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
        newTimeSubjects = mDatabase.getTimeOfSubjects(dayno);
        mTextViewDay = (TextView) findViewById(R.id.daily_day);
        mTextViewTime = (TextView) findViewById(R.id.daily_time);

        mTextViewDay.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()) );


        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime = new SimpleDateFormat("HH:mm", Locale.US).format(new Date());
                mTextViewTime.setText(currentTime);

                mTextViewClass = (TextView) findViewById(R.id.daily_class);
                mTextViewClass.setText("Free Time");
                mTextViewAttend = (TextView) findViewById(R.id.daily_class_attend);
                mTextViewAttend.setText("Chilling!");
                for(int i = 1; i<newTimeSubjects.size();i++){
                    try {
                        boolean neaw = isTimeBetweenTwoTime(newTimeSubjects.get(i)[1],newTimeSubjects.get(i)[2],currentTime);
                        Log.d("isTrue",neaw+"");
                        if(neaw){
                            mTextViewClass = (TextView) findViewById(R.id.daily_class);
                            mTextViewClass.setText(newTimeSubjects.get(i)[0]);

                            Date cDate = new Date();
                            String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
                            String attended = mDatabase.getAttendanceData(fDate,newTimeSubjects.get(i)[0]);

                            mTextViewAttend = (TextView) findViewById(R.id.daily_class_attend);
                            if(attended.equals("1")){
                                mTextViewAttend.setText("Class Attended");
                            }
                            else {
                                mTextViewAttend.setText("Not Attended");
                            }
//                            Log.d("as",currentTime);

                        }


                    } catch (ParseException e) {
                        e.printStackTrace() ;
                    }
                }


                someHandler.postDelayed(this, 1000);
            }
        }, 10);


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

    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime) throws ParseException {

            boolean valid = false;
            //Start Time
            java.util.Date inTime = new SimpleDateFormat("HH:mm").parse(initialTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(inTime);

            //Current Time
            java.util.Date checkTime = new SimpleDateFormat("HH:mm").parse(currentTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(checkTime);

            //End Time
            java.util.Date finTime = new SimpleDateFormat("HH:mm").parse(finalTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(finTime);

            if (finalTime.compareTo(initialTime) < 0) {
                calendar2.add(Calendar.DATE, 1);
                calendar3.add(Calendar.DATE, 1);
            }

            java.util.Date actualTime = calendar3.getTime();
            if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0)
                    && actualTime.before(calendar2.getTime())) {
                valid = true;
            }
            return valid;

    }
}
