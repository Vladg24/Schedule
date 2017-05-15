package com.bkeipr.shedulebkeipr.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bkeipr.shedulebkeipr.R;
import com.bkeipr.shedulebkeipr.m_Model.Teachers;

import java.util.ArrayList;

import static com.bkeipr.shedulebkeipr.R.id.teacher;

public class CustomerAdapterTeachers  extends BaseAdapter {

    Context c;
    ArrayList<Teachers> teachers;

    public CustomerAdapterTeachers(Context c, ArrayList<Teachers> teachers) {
        this.c = c;
        this.teachers = teachers;
    }

    @Override
    public int getCount() {
        return teachers.size();
    }

    @Override
    public Object getItem(int i) {
        return teachers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.modelteachers,viewGroup,false);
        }

        TextView teacherTxt = (TextView) view.findViewById(teacher);
        TextView num_audTxt= (TextView) view.findViewById(R.id.name_Aud);

        Teachers teacher1 = (Teachers ) this.getItem(i);
        final String teacher=teacher1.getTeacher();
        final String num_aud=teacher1.getNum_aud();

        teacherTxt.setText(teacher);
        num_audTxt.setText(num_aud);

        return view;
    }
}
