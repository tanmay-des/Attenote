package com.infinitequarks.tgz.attenote;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by m on 10/21/2016.
 */

public class AttendanceListAdapter extends ArrayAdapter<ItemAttendance> {


    private final Context context;
    private final ArrayList<ItemAttendance> itemsArrayList;

    public AttendanceListAdapter(Context context, ArrayList<ItemAttendance> itemsArrayList) {

        super(context, R.layout.attendance_list_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.attendance_list_item, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.attendance_text_view);
        TextView valueView = (TextView) rowView.findViewById(R.id.attendance_text_view_subject_name);

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getTotalAttended());
        valueView.setText(itemsArrayList.get(position).getTotalClasses());
        if(position%2==0){
            rowView.setBackgroundColor(0xFFF0F0F0);
        }
        else
            rowView.setBackgroundColor(0xFFFFFFFF);



        // 5. retrn rowView
        return rowView;
    }
}