package com.infinitequarks.tgz.attenote;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by m on 10/19/2016.
 */

public class TimeListAdapter extends BaseAdapter {

    private String [] subjectList;
    private int mDay=0;
    private ArrayList<TimeData> timeDatas = new ArrayList<TimeData>();
    TimeData newTime = new TimeData();
    private static LayoutInflater sLayoutInflater = null;

    public class DataSaver{
        public DataSaver(int id, String subjectName, int startHour, int startMinute, int endHour, int endMinute){
            mId = id;
            mSubjectName = subjectName;
            mStartHour = startHour;
            mEndHour = endHour;
            mStartMinute = startMinute;
            mEndMinute = endMinute;
            isOn = false;
        }
        public int mId;
        boolean isOn;
        String mSubjectName;
        int mStartHour;
        int mStartMinute;
        int mEndHour;
        int mEndMinute;
    }

    public HashMap<Integer, DataSaver> mDataSaver;


    public TimeListAdapter(Context context, String[] subjectNames,int day){
        subjectList = subjectNames;
        mDay =day;
        sLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            mDataSaver = new HashMap<Integer, DataSaver>();
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

    public static class ViewHolder{
        public TextView textView;
        public Button editButton;
        public  Switch sSwitch;
        public  TextView startTime;
        public  TextView endTime;
        DataSaver data;
    }

    private DataSaver getDataSaver(int pos){
        if(!mDataSaver.containsKey(pos))
            mDataSaver.put(pos, new DataSaver(pos, subjectList[pos], 8, 40, 9, 40));
        return mDataSaver.get(pos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("helloaaa",Integer.toString(position));

        if (convertView == null) {
            Log.d("hello",Integer.toString(position));
//            Log.d("hello2",convertView.toString());

            final View rowView = sLayoutInflater.inflate(R.layout.time_data_list_item,null);
            final ViewHolder holder = new ViewHolder();

            holder.data = getDataSaver(position);

            rowView.setBackgroundColor(0xFFF0F0F0);
            holder.textView = (TextView) rowView.findViewById(R.id.time_data_text_view);

            holder.startTime = (TextView) rowView.findViewById(R.id.time_data_start);
            holder.startTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(rowView.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            holder.startTime.setText( selectedHour + ":" + selectedMinute);
                            holder.data.mStartHour = selectedHour;
                            holder.data.mStartMinute = selectedMinute;

                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });

            holder.endTime = (TextView) rowView.findViewById(R.id.time_data_end);


            holder.endTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            holder.endTime.setText( selectedHour + ":" + selectedMinute);
                            holder.data.mEndHour = selectedHour;
                            holder.data.mEndMinute = selectedMinute;
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            });

            holder.textView.setText(holder.data.mSubjectName);



            holder.sSwitch = (Switch) rowView.findViewById(R.id.time_data_switch);
            holder.sSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    holder.data.isOn = isChecked;
                    if(isChecked){
                        rowView.setBackgroundColor(0xFFFFFFFF);
                        holder.sSwitch.setChecked(true);

//                        newTime.setIsTrue(1);
//                        newTime.setSubjectName(subjectList[position]);
//                        newTime.setStartTime(endTime.toString());
//                        newTime.setEndTime(startTime.toString());
                    }
                    else if(!isChecked) {
                        holder.sSwitch.setChecked(false);

                        rowView.setBackgroundColor(0xFFF0F0F0);
                    }
                }
            });
            rowView.setTag(holder);
            return rowView;

        }
        else {
            ViewHolder holder =  (ViewHolder)convertView.getTag();
            holder.data = getDataSaver(position);
            holder.textView.setText(holder.data.mSubjectName);
            holder.sSwitch.setChecked(holder.data.isOn);
            holder.startTime.setText(holder.data.mStartHour + ":" + holder.data.mStartMinute);
            holder.endTime.setText(holder.data.mEndHour + ":" + holder.data.mEndMinute);

            return convertView;
        }
//        if(position%2 == 0){
//            rowView.setBackgroundColor(0xFFF5F5F5);
//        }
//
//            rowView.setBackgroundColor(0xFFFFFFFF);



    }


}
