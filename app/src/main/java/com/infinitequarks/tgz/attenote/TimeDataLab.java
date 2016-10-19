package com.infinitequarks.tgz.attenote;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by m on 10/18/2016.
 */

public class TimeDataLab  {
    private static TimeDataLab sTimeDataLab;

    private List<TimeData> mTimeDatas;

    public static TimeDataLab get(Context context){
        if(sTimeDataLab == null){
            sTimeDataLab = new TimeDataLab(context);
        }
        return sTimeDataLab;

    }
    private TimeDataLab(Context context){
        mTimeDatas = new ArrayList<>();
    }
    public List<TimeData> getTimeDatas(){
        return mTimeDatas;
    }
    public TimeData getTimeData(UUID id){
        for(TimeData timeData: mTimeDatas){
            if(timeData.getId().equals(id)){
                return timeData;
            }
        }
        return null;
    }
}
