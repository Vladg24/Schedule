package com.bkeipr.shedulebkeipr.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bkeipr.shedulebkeipr.R;
import com.bkeipr.shedulebkeipr.m_Model.Ring;

import java.util.ArrayList;

import static com.bkeipr.shedulebkeipr.R.id.name_disciplines;

public class CustomAdapterRing  extends BaseAdapter {

    Context c;
    ArrayList<Ring> rings;

    public CustomAdapterRing(Context c, ArrayList<Ring> rings) {
        this.c = c;
        this.rings = rings;
    }

    @Override
    public int getCount() {
        return rings.size();
    }

    @Override
    public Object getItem(int i) {
        return rings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.modelring,viewGroup,false);
        }

        TextView name_dTxt = (TextView) view.findViewById(name_disciplines);
        TextView startTxt= (TextView) view.findViewById(R.id.startLess);
        TextView endTxt= (TextView) view.findViewById(R.id.endLess);

       Ring ring= (Ring) this.getItem(i);

        final String num_lessons=ring.getNum_lessons();
        final String start_less=ring.getStart_less();
        final String end_less=ring.getEnd_less();

        name_dTxt.setText(num_lessons);
        startTxt.setText(start_less);
        endTxt.setText(end_less);


        return view;
    }

}