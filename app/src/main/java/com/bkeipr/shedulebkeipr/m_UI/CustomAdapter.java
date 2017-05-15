package com.bkeipr.shedulebkeipr.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bkeipr.shedulebkeipr.R;
import com.bkeipr.shedulebkeipr.m_Model.Schedules;

import java.util.ArrayList;

import static com.bkeipr.shedulebkeipr.R.id.name_disciplines;
import static com.bkeipr.shedulebkeipr.R.id.teacher;

public class CustomAdapter  extends BaseAdapter {

    Context c;
    ArrayList<Schedules> schedules;

    public CustomAdapter(Context c, ArrayList<Schedules> users) {
        this.c = c;
        this.schedules = users;
    }

    @Override
    public int getCount() {
        return schedules.size();
    }

    @Override
    public Object getItem(int i) {
        return schedules.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        TextView name_dTxt = (TextView) view.findViewById(name_disciplines);
        TextView teacherTxt = (TextView) view.findViewById(teacher);
        TextView num_audTxt= (TextView) view.findViewById(R.id.name_Aud);
        TextView startTxt= (TextView) view.findViewById(R.id.startLess);
        TextView endTxt= (TextView) view.findViewById(R.id.endLess);

        Schedules schedule = (Schedules) this.getItem(i);

        final String name_disciplines=schedule.getName_disciplines();
        final String teacher=schedule.getTeacher();
        final String num_aud=schedule.getNum_aud();
        final String start_less=schedule.getStart_less();
        final String end_less=schedule.getEnd_less();

        name_dTxt.setText(name_disciplines);
        teacherTxt.setText(teacher);
        num_audTxt.setText(num_aud);
        startTxt.setText(start_less);
        endTxt.setText(end_less);


        return view;
    }

}