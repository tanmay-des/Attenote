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

public class NoteListAdapter extends ArrayAdapter<NotesItem> {


    private final Context context;

    private final ArrayList<NotesItem> itemsArrayList;

    public NoteListAdapter(Context context, ArrayList<NotesItem> itemsArrayList) {

        super(context, R.layout.note_single_list_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.note_single_list_item, parent, false);

        // 3. Get the two text view from the rowView
        TextView title =(TextView) rowView.findViewById(R.id.note_list_text_title);
        TextView note = (TextView) rowView.findViewById(R.id.note_list_text_view_note);
        TextView date = (TextView) rowView.findViewById(R.id.note_list_text_date);

        // 4. Set the text for textView
        title.setText(itemsArrayList.get(position).getTitle());
        note.setText(itemsArrayList.get(position).getNote());
        date.setText(itemsArrayList.get(position).getDate());

        if(position%2==0){
            rowView.setBackgroundColor(0xFFF0F0F0);
        }
        else
            rowView.setBackgroundColor(0xFFFFFFFF);



        // 5. retrn rowView
        return rowView;
    }
}