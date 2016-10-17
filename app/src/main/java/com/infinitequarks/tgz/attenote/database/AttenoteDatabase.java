package com.infinitequarks.tgz.attenote.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by m on 10/18/2016.
 */

public class AttenoteDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Attenote.db";
    public  static  final  String TABLE_NAME ="subjects";
    public  static  final  String COL_1 = "ID";
    public  static  final  String COL_2 = "SubjectName";
    public  static  final  String COL_3 = "isTheory";



    public AttenoteDatabase(Context context) {
        super(context, DATABASE_NAME, null , 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, SubjectName TEXT ,isTheory BOOLEAN )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String name,boolean theory){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,theory);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public int deleteData(String mName){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME,"SubjectName = ?",new String[] {mName});

    }
}
