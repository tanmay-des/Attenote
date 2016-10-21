package com.infinitequarks.tgz.attenote;

/**
 * Created by m on 10/21/2016.
 */

public class ItemAttendance {
    private String mTotalAttended;
    private String mTotalClasses;
    private String mSubjectName;

    public ItemAttendance(String title, String description) {
        super();
        mTotalAttended = title;
        mTotalClasses = description;
    }

    public String getTotalAttended() {
        return mTotalAttended;
    }

    public void setTotalAttended(String totalAttended) {
        mTotalAttended = totalAttended;
    }

    public String getTotalClasses() {
        return mTotalClasses;
    }

    public void setTotalClasses(String totalClasses) {
        mTotalClasses = totalClasses;
    }

    public String getSubjectName() {
        return mSubjectName;
    }

    public void setSubjectName(String subjectName) {
        mSubjectName = subjectName;
    }
}
