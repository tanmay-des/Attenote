package com.infinitequarks.tgz.attenote;

import java.sql.Time;
import java.util.UUID;

/**
 * Created by m on 10/18/2016.
 */

public class TimeData {

    private UUID mId;
    private String subjectName;
    private int isTrue;
    private Time startTime;
    private Time endTime;

    public TimeData() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(int isTrue) {
        this.isTrue = isTrue;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
