package com.infinitequarks.tgz.attenote;

import java.sql.Time;
import java.util.UUID;

/**
 * Created by m on 10/18/2016.
 */

public class TimeData {

    private UUID mId;
    private int mday;
    private String msubjectName;
    private int isTrue;
    private String mstartTime;
    private String mendTime;

    public TimeData() {
        mId = UUID.randomUUID();
    }

    public int getDay() {
        return mday;
    }

    public void setDay(int day) {
        mday = day;
    }

    public UUID getId() {
        return mId;
    }


    public String getSubjectName() {
        return msubjectName;
    }

    public void setSubjectName(String subjectName) {
        msubjectName = subjectName;
    }

    public int getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(int isTrue) {
        this.isTrue = isTrue;
    }

    public String  getStartTime() {
        return mstartTime;
    }

    public void setStartTime(String startTime) {
        mstartTime = startTime;
    }

    public String getEndTime() {
        return mendTime;
    }

    public void setEndTime(String endTime) {
        mendTime = endTime;
    }
}
