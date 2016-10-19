package com.infinitequarks.tgz.attenote.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.infinitequarks.tgz.attenote.Subject;
import com.infinitequarks.tgz.attenote.TimeData;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema.SubjectTable;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema.TimeTable;

import java.util.ArrayList;
import java.util.List;

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
        db.execSQL("create table "+ SubjectTable.NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SubjectTable.Cols.subjectName + " ," +
                SubjectTable.Cols.isTheory + " )"
        );

        db.execSQL("create table "+ TimeTable.NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TimeTable.Cols.day_no + " ," +
                TimeTable.Cols.subjectName + " ," +
                TimeTable.Cols.isTrue + " ," +
                TimeTable.Cols.startTime + " ," +
                TimeTable.Cols.endTime + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+SubjectTable.NAME);
        db.execSQL("DROP TABLE IF EXIST "+TimeTable.NAME);
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
        db.insert(SubjectTable.NAME,null,values);

    }

    public static ContentValues getContentValues(TimeData newTimeTable){
        ContentValues values = new ContentValues();
//        values.put(TimeTable.Cols.day_no , newTimeTable.getDay_no());
        values.put(TimeTable.Cols.subjectName, newTimeTable.getSubjectName());
        values.put(TimeTable.Cols.isTrue, newTimeTable.getIsTrue());
        values.put(TimeTable.Cols.startTime, newTimeTable.getStartTime().toString());
        values.put(TimeTable.Cols.endTime, newTimeTable.getEndTime().toString());

        return values;
    }


    public Cursor getData(TimeData newTimeTable){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TimeTable.NAME,null);
        return res;
    }



//    public int deleteData(String mName){
//        SQLiteDatabase db = this.getWritableDatabase();
//        return  db.delete(TABLE_SUBJECTS,"SubjectName = ?",new String[] {mName});

//    }
}
