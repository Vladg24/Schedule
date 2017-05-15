package com.bkeipr.shedulebkeipr.m_JSON;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.bkeipr.shedulebkeipr.MainActivity;
import com.bkeipr.shedulebkeipr.adapter.Enum_Fields;
import com.bkeipr.shedulebkeipr.m_Model.Groups;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ParserRing extends AsyncTask<Void,Void,Boolean> {

    ArrayList<Groups> group = new ArrayList<>();
    Context c;
    String jsonData;
    String str;

    public ParserRing(Context c, String jsonData,String str) {
        this.c = c;
        this.jsonData = jsonData;
        this.str = str;
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

        if (!isParsed) {
            Toast.makeText(c, "Unable To Parse,Check Your Log output", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parse() {

        try {
            String nameGroup="";
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;
            group.clear();
            Groups groups;

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);

                nameGroup = jo.getString("name_group");

                groups = new Groups();

                groups.setName_group(nameGroup);

                group.add(groups);


            }
if(nameGroup == str){
    MainActivity mainActivity = new MainActivity();
    mainActivity.checkGroup(nameGroup);
}
            return true;

        } catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    }

