package com.infinitequarks.tgz.attenote.database;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.NonNull;

import android.util.Log;

import com.infinitequarks.tgz.attenote.Subject;
import com.infinitequarks.tgz.attenote.TimeData;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema.SubjectTable;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema.TimeTable;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema.Attendance;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema.NotesTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;

/**
 * Created by m on 10/18/2016.
 */

public class AttenoteDatabase extends SQLiteOpenHelper {

    public  static  final  String DATABASE_NAME = "Attenote.db";
    public  static  final  int VERSION = 1;

    public AttenoteDatabase(Context context) {
        super(context, DATABASE_NAME, null , VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TimeTable.NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TimeTable.Cols.day_no + " ," +
                TimeTable.Cols.subjectName + " ," +
                TimeTable.Cols.isTrue + " ," +
                TimeTable.Cols.startTime + " ," +
                TimeTable.Cols.endTime + " )"
        );

        db.execSQL("create table "+ SubjectTable.NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SubjectTable.Cols.subjectName + " ," +
                SubjectTable.Cols.isTheory + " )"
        );


        db.execSQL("create table "+ Attendance.NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Attendance.Cols.subjectName + " ," +
                Attendance.Cols.attended + " ," +
                Attendance.Cols.date + " )"
        );

        db.execSQL("create table "+ NotesTable.NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NotesTable.Cols.subjectName + " ," +
                NotesTable.Cols.note + " ," +
                NotesTable.Cols.title + " ," +
                NotesTable.Cols.date + " )"
        );

        fakeData(db);


    }

    public void fakeData(SQLiteDatabase mdb){
        SQLiteDatabase db = mdb;


        for(int i=0;i<9;i++){
            ContentValues values = new ContentValues();
            int mj = i +1;
            values.put(SubjectTable.Cols.subjectName ,"Subject "+mj);
            if(i<4){
                values.put(SubjectTable.Cols.isTheory,"true");
            }
            else
                values.put(SubjectTable.Cols.isTheory,"false");

            db.insert(SubjectTable.NAME,null,values);
        }


        for (int i = 0;i<5;i++){

            for (int j =0;j<4;j++){
                ContentValues values = new ContentValues();
                Random r = new Random();
                int i1 = r.nextInt(10 - 1) + 1;
                values.put(TimeTable.Cols.subjectName,"Subject "+ i1);
                values.put(TimeTable.Cols.day_no,i);
                int mj = 9+j;
                values.put(TimeTable.Cols.startTime,mj+":"+"30");
                mj++;
                values.put(TimeTable.Cols.endTime,mj+":"+"30");

                db.insert(TimeTable.NAME,null,values);
            }

        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+SubjectTable.NAME);
        db.execSQL("DROP TABLE IF EXIST "+TimeTable.NAME);
        db.execSQL("DROP TABLE IF EXIST "+Attendance.NAME);
        db.execSQL("DROP TABLE IF EXIST "+ NotesTable.NAME);
        onCreate(db);
    }

    // Functions for table Subject TABLE
    public void insertData (Subject newSubject){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValues(newSubject);
        db.insert(SubjectTable.NAME,null,values);

    }

    public static ContentValues getContentValues(Subject newSubject){
        ContentValues values = new ContentValues();
        values.put(SubjectTable.Cols.subjectName , newSubject.getName());
        values.put(SubjectTable.Cols.isTheory, newSubject.isTheory());

        return values;
    }


    public Cursor getData(Subject newSubject){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+SubjectTable.NAME,null);
        return res;
    }
    public String[] getSubjectList(){

        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+SubjectTable.NAME,null);
        String[] list = new String[res.getCount()];
        while (res.moveToNext()){
            list[i] = res.getString(1);
            i++;
        }
        i=0;
        return list;
    }

    // Functions for table Subject TABLE

    public void insertData (TimeData newTimeTable){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValues(newTimeTable);
        db.insert(TimeTable.NAME,null,values);

    }

    public static ContentValues getContentValues(TimeData newTimeTable){
        ContentValues values = new ContentValues();
//        values.put(TimeTable.Cols.day_no , newTimeTable.getDay_no());

        values.put(TimeTable.Cols.subjectName, newTimeTable.getSubjectName());
        values.put(TimeTable.Cols.day_no, newTimeTable.getDay());
        values.put(TimeTable.Cols.isTrue, newTimeTable.getIsTrue());
        values.put(TimeTable.Cols.startTime, newTimeTable.getStartTime());
        values.put(TimeTable.Cols.endTime, newTimeTable.getEndTime());
        Log.d("data save",values.toString());
        return values;
    }


    public Cursor getData(TimeData newTimeTable){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TimeTable.NAME,null);
        return res;
    }


    public ArrayList<TimeData> getDailyData(String day_no){

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<TimeData> newList = new ArrayList<>();


        Cursor res = db.rawQuery("select * from "+TimeTable.NAME +" WHERE day_no = " + day_no ,null);

        while (res.moveToNext()){

            TimeData newTimeData = new TimeData();

            newTimeData.setSubjectName(res.getString(2));
            newTimeData.setDay(res.getInt(1));
            newTimeData.setStartTime(res.getString(4));
            newTimeData.setEndTime(res.getString(5));

            Log.d("My Data",newTimeData.toString());

            newList.add(newTimeData);
//            Log.d("data",newList.toString());


        }
        return newList;
    }

    // functions for attendance table

    public void insertData (String mdate,String subjectName,boolean attended){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValues(mdate,subjectName,attended);
        db.insert(Attendance.NAME,null,values);

    }

    public void attendanceInitializer(String mdate,String day_no){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+Attendance.NAME +  " where date = " + "'"+mdate+"'" ,null);
        if(res.getCount()==0){
            Cursor res2 = db.rawQuery("select * from "+TimeTable.NAME +" WHERE day_no = " + day_no ,null);
            while (res2.moveToNext()){
                ContentValues values = new ContentValues();
                values.put(Attendance.Cols.subjectName ,res2.getString(2));
                values.put(Attendance.Cols.date, mdate);
                values.put(Attendance.Cols.attended, false);

                db.insert(Attendance.NAME,null,values);
            }
        }
        else
            Log.d("data","found");
    }

    public static ContentValues getContentValues(String mdate,String subjectName,boolean attended){
        ContentValues values = new ContentValues();
        values.put(Attendance.Cols.subjectName ,subjectName);
        values.put(Attendance.Cols.date, mdate);
        values.put(Attendance.Cols.attended, attended);

        return values;
    }


    public String getAttendanceData(String mdate, String subjectname){
        SQLiteDatabase db = this.getWritableDatabase();
        String mylol= new String();
        Cursor res = db.rawQuery("select * from "+Attendance.NAME + " where date = "+"'"+mdate+"'"+" AND subjectName = "+"'"+subjectname+"'",null);
//        Log.d("res",res.getCount()+" ");
//        Log.d("res",res.getString(0));
//        Log.d("res",res.getString(1));
//        Log.d("res",res.getString(2));
        while (res.moveToNext()){
          mylol =  res.getString(2);
        }
        return mylol;
    }

    public void updateSubjectAttendance(Boolean attended,String mDate,String subjectName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("attended", attended);

        String[] args = new String[]{mDate, subjectName};
        db.update(Attendance.NAME, newValues, "date=? AND subjectName=?", args);
    }


    public void viewTableData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Attendance.NAME,null);
        while (res.moveToNext()){
            Log.d("Id",res.getString(0));
            Log.d("Name",res.getString(1));
            Log.d("Day No",res.getString(2));
            Log.d("asdfasf",res.getString(3));
//            Log.d("start Time",res.getString(4));
//            Log.d("end Time",res.getString(5));

        }
    }


//    public int deleteData(String mName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        return  db.delete(TABLE_SUBJECTS,"SubjectName = ?",new String[] {mName});

//    }


    public void notesInitializer(String mDate,String subjectName){

        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{mDate, subjectName};
        Cursor res = db.rawQuery("select * from "+ NotesTable.NAME +  " where date = ? AND subjectName = ?",args);

        if(res.getCount()==0){

            ContentValues values = new ContentValues();

            values.put(NotesTable.Cols.subjectName ,subjectName);
            values.put(NotesTable.Cols.date, mDate);
//            values.put(NotesTable.Cols.title, );
            values.put(NotesTable.Cols.note,"-Start Adding Notes :) \n");

            db.insert(NotesTable.NAME,null,values);
        }
        else
            Log.d("data","found");
    }
    public String[] getNotes(String mDate,String subjectName){
        SQLiteDatabase db = this.getWritableDatabase();
        String note = new String();
        String title = new String();
        String[] args = new String[]{mDate, subjectName};
        Cursor res = db.rawQuery("select * from "+ NotesTable.NAME +  " where date = ? AND subjectName = ?",args);
        while (res.moveToNext()){
            note = res.getString(2);
            title = res.getString(3);
        }
        String[] ret = new String[]{note,title};
        return ret;
    }

    public void notesUpdater(String notes,String title,String mDate, String subjectName){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();

        newValues.put(NotesTable.Cols.note, notes);
        newValues.put(NotesTable.Cols.title , title);

        String[] args = new String[]{mDate, subjectName};
        db.update(NotesTable.NAME, newValues, "date=? AND subjectName=?", args);
    }
}
