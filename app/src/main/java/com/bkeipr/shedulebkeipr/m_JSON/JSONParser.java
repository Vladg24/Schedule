package com.bkeipr.shedulebkeipr.m_JSON;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;


import com.bkeipr.shedulebkeipr.m_Model.Ring;
import com.bkeipr.shedulebkeipr.m_Model.Schedules;
import com.bkeipr.shedulebkeipr.m_Model.Teachers;
import com.bkeipr.shedulebkeipr.m_UI.CustomAdapter;
import com.bkeipr.shedulebkeipr.m_UI.CustomAdapterRing;
import com.bkeipr.shedulebkeipr.m_UI.CustomerAdapterTeachers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser extends AsyncTask<Void,Void,Boolean> {

    Context c;
    String jsonData;
    ListView lv;
    String flag;

    ArrayList<Schedules> schedules = new ArrayList<>();
    ArrayList<Ring> rings = new ArrayList<>();
    ArrayList<Teachers> teachers = new ArrayList<>();


    public JSONParser(Context c, String jsonData, ListView lv, String flag) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        if(isParsed)
        {
            if(flag == "sched") {
                lv.setAdapter(new CustomAdapter(c, schedules));
            }
            else if(flag == "ring"){
                lv.setAdapter(new CustomAdapterRing(c, rings));
            }
            else if(flag == "teacher"){
                lv.setAdapter(new CustomerAdapterTeachers(c, teachers));
            }

        }else
        {
            Toast.makeText(c, "Unable To Parse,Check Your Log output", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;

            if(flag == "sched") {
                schedules.clear();
                Schedules schedule;

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);

                    String name_disciplines = jo.getString("name_disciplines");
                    String teacher = jo.getString("name_teacher");
                    String num = jo.getString("num");
                    String start_less = jo.getString("start_less");
                    String end_less = jo.getString("end_less");

                    schedule = new Schedules();

                    schedule.setName_disciplines(name_disciplines);
                    schedule.setTeacher(teacher);
                    schedule.setNum_aud(num);
                    schedule.setStart_less(start_less);
                    schedule.setEnd_less(end_less);

                    this.schedules.add(schedule);
                }
            }
            else if(flag == "ring"){
                rings.clear();
                Ring ring;

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);

                    String num_lessons = jo.getString("num_lessons");
                    String start_less = jo.getString("start_less");
                    String end_less = jo.getString("end_less");

                    ring = new Ring();

                    ring.setNum_lessons(num_lessons);
                    ring.setStart_less(start_less);
                    ring.setEnd_less(end_less);

                    rings.add(ring);
                }
            }
            else if(flag == "teacher"){
                teachers.clear();
                Teachers teacher;

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);

                    String teachere = jo.getString("name_teacher");
                    String num = jo.getString("num");

                    teacher = new Teachers();

                    teacher.setTeacher(teachere);
                    teacher.setNum_aud(num);

                    teachers.add(teacher);
                }
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}