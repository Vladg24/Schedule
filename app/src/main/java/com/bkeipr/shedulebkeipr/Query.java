package com.bkeipr.shedulebkeipr;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.bkeipr.shedulebkeipr.m_JSON.JSONParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Query extends Thread
{
    String name_group ;
    InputStream is = null;
    String result = null;
    String line = null;
    Context c;
    ListView lv;
    String jsonUrl;
    String flag;

    public Query (Context c,ListView lv,String name_group,String jsonUrl,String flag)
    {
        this.lv = lv;
        this.c = c;
        this.name_group = name_group;
        this.jsonUrl = jsonUrl;
        this.flag = flag;
        start();
    }

    public void run()
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("name_group", name_group));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(jsonUrl);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
        }

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
                new JSONParser(c, result, lv,flag).execute();


            Log.e("pass 2", "connection success" + result);
        } catch (Exception e)
        {
            Log.e("Fail 2", e.toString());
        }
    }
}

