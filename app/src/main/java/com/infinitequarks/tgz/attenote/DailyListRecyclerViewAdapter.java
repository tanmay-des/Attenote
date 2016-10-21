package com.infinitequarks.tgz.attenote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.infinitequarks.tgz.attenote.database.AttenoteDatabase;
import com.infinitequarks.tgz.attenote.database.AttenoteDbSchema;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by m on 10/20/2016.
 */

public class DailyListRecyclerViewAdapter extends RecyclerView
        .Adapter<DailyListRecyclerViewAdapter
        .DailyListObjectHolder> {

    private static String LOG_TAG ="dailylistadapter";
    private ArrayList<TimeData> mDataset;
    AttenoteDatabase mDatabase ;
    Date cDate = new Date();
    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(cDate);

//    private static MyClickListener myClickListener;

    public static class DailyListObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView subjectName;
        TextView startTime;
        TextView endTime;
        Button addNotes;
        Switch attended;
        ViewGroup myView;

        public DailyListObjectHolder(View itemView){
            super(itemView);
            myView = (ViewGroup ) itemView;
            subjectName = (TextView) itemView.findViewById(R.id.daily_list_item_subject);
            startTime = (TextView) itemView.findViewById(R.id.daily_list_item_start_time);
            endTime = (TextView) itemView.findViewById(R.id.daily_list_item_end_time);
            attended = (Switch) itemView.findViewById(R.id.daily_list_item_attended);
            addNotes = (Button) itemView.findViewById(R.id.daily_list_item_add_note);


        }

        @Override
        public void onClick(View v) {


//            myClickListener.onItemClick(getPosition(),v);
        }
    }

    public DailyListRecyclerViewAdapter(ArrayList<TimeData> myDataset, Context context,String dayno){
        mDataset = myDataset;
        mDatabase = new AttenoteDatabase(context);
        mDatabase.attendanceInitializer(fDate,dayno);
        mDatabase.viewTableData();
    }
    @Override
    public DailyListRecyclerViewAdapter.DailyListObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_list_item,parent,false);

        DailyListObjectHolder dailyListObjectHolder = new DailyListObjectHolder(view);


        return dailyListObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DailyListRecyclerViewAdapter.DailyListObjectHolder holder, final int position) {

        holder.subjectName.setText(mDataset.get(position).getSubjectName());
        holder.startTime.setText(mDataset.get(position).getStartTime());
        holder.endTime.setText(mDataset.get(position).getEndTime());
        boolean newbool = false;
        if(mDatabase.getAttendanceData(fDate,mDataset.get(position).getSubjectName()).equals("1")){
            newbool = true;
        }
        holder.attended.setChecked(newbool);
        holder.attended.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                updateAttendance(isChecked,fDate,mDataset.get(position).getSubjectName());
            }
        });
        if(position % 2==0){
            holder.myView.setBackgroundColor(0xFFF0F0F0);
        }
        else if(position %2 != 0)
            holder.myView.setBackgroundColor(0xFFFFFFFF);

        final String subjectName = mDataset.get(position).getSubjectName();
        holder.addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = NoteActivity.noteIntent(v.getContext(),subjectName,fDate);
                v.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateAttendance(boolean bool, String string1,String string2){
            mDatabase.updateSubjectAttendance(bool,string1,string2);
    }
}
