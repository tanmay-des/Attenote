package com.infinitequarks.tgz.attenote;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by m on 10/20/2016.
 */

public class DailyListRecyclerViewAdapter extends RecyclerView
        .Adapter<DailyListRecyclerViewAdapter
        .DailyListObjectHolder> {

      private static String LOG_TAG ="dailylistadapter";
    private ArrayList<TimeData> mDataset;
//    private static MyClickListener myClickListener;

    public static class DailyListObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView subjectName;
        TextView startTime;
        TextView endTime;
        Button addNotes;
        Switch attended;

        public DailyListObjectHolder(View itemView){
            super(itemView);
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

    public DailyListRecyclerViewAdapter(ArrayList<TimeData> myDataset){
        mDataset = myDataset;
    }
    @Override
    public DailyListRecyclerViewAdapter.DailyListObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_list_item,parent,false);

        DailyListObjectHolder dailyListObjectHolder = new DailyListObjectHolder(view);

        return dailyListObjectHolder;
    }

    @Override
    public void onBindViewHolder(DailyListRecyclerViewAdapter.DailyListObjectHolder holder, int position) {
        holder.subjectName.setText(mDataset.get(position).getSubjectName());
        holder.startTime.setText(mDataset.get(position).getStartTime());
        holder.endTime.setText(mDataset.get(position).getEndTime());
        holder.attended.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
