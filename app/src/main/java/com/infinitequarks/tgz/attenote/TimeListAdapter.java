package com.infinitequarks.tgz.attenote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m on 10/19/2016.
 */

public class TimeListAdapter extends BaseAdapter {

    private String [] subjectList;
    private int day=0;
    private ArrayList<TimeData> timeDatas = new ArrayList<TimeData>();
    TimeData newTime = new TimeData();
    private static LayoutInflater sLayoutInflater = null;
   public TimeListAdapter(Context context, String[] subjectNames,int sday){
        subjectList = subjectNames;
        day = sday;
        sLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return subjectList.length;
    }

    @Override
    public Object getItem(int position) {
        return subjectList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView;
        Button editButton;
        Switch sSwitch;
        final TextView startTime;
        final TextView endTime;

        View rowView;
        rowView = sLayoutInflater.inflate(R.layout.time_data_list_item,null);

        textView = (TextView) rowView.findViewById(R.id.time_data_text_view);
        startTime = (TextView) rowView.findViewById(R.id.time_data_start);
        endTime = (TextView) rowView.findViewById(R.id.time_data_end);

        textView.setText(subjectList[position]);
        editButton = (Button) rowView.findViewById(R.id.time_data_set_time);

        sSwitch = (Switch) rowView.findViewById(R.id.time_data_switch);
            sSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){

                        newTime.setIsTrue(1);
                        newTime.setSubjectName(subjectList[position]);
//                        newTime.setStartTime(endTime.toString());
//                        newTime.setEndTime(startTime.toString());
                    }
                }
            });

        return rowView;

    }


}
