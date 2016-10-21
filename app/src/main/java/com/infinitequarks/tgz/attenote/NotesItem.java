package com.infinitequarks.tgz.attenote;

/**
 * Created by m on 10/21/2016.
 */

public class NotesItem {
    private String mDate;
    private String mTitle;
    private String mNote;

    public NotesItem(String date,String title,String note){
        mDate= date;
        mTitle = title;
        mNote = note;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }
}
