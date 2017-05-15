package com.bkeipr.shedulebkeipr.Fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bkeipr.shedulebkeipr.R;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;


public class ScheduleChangeFragment extends Fragment {

    View view;
    public TextView headerschedule;
    public TextView mainschedule;
    String name_group;

    public ScheduleChangeFragment(String str)
    {
        name_group=str;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.change_schedule, container, false);

        headerschedule = (TextView) view.findViewById(R.id.headershedule);
        mainschedule = (TextView) view.findViewById(R.id.mainschedule);

         btnClick();

        return view;
    }

    public void btnClick()
    {
        try {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath() ;
            File file = new File(path+"/Schedule.doc") ;
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            HWPFDocument doc = new HWPFDocument(fis);
            WordExtractor we = new WordExtractor(doc);

            String paragraphs = we.getText();

            String df="";
            for (String para : paragraphs.split("ауд")) {
                df += para;
            }
            String fd="";
            for (String para : df.split("Четвер")) {
                fd += para;
                }
            String name_end_g="";
            if(name_group.equals("КН-13")){
                name_end_g="КН-16";
            }
            else if(name_group.equals("КН-14")){
                name_end_g="КН-15";
            }
            else if(name_group.equals("КН-15"))
            {
                name_end_g="5.03060101 «Організація виробництва»";
            }
            else if(name_group.equals("КН-16")){
                name_end_g="КН-14";
            }
            int a=0,b=0,c;
            a=fd.indexOf(name_group);
            b=fd.lastIndexOf(name_end_g);
            c=fd.lastIndexOf("РОКУ");

            headerschedule.setText(fd.substring(0,c));
            mainschedule.setText(fd.substring(a+5,b));

            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

