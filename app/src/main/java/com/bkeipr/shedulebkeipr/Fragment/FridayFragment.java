package com.bkeipr.shedulebkeipr.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bkeipr.shedulebkeipr.Query;
import com.bkeipr.shedulebkeipr.R;

public class FridayFragment extends Fragment {

    String str;

    public FridayFragment (String str)
    {
        this.str = str;
    }

    String jsonUrl="http://bkeiprschedule.esy.es/Friday.php";
    ListView lv;
    View view;
    String  flag = "sched";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.monday, container, false);

        lv = (ListView) view.findViewById(R.id.lv);

        new Query(getActivity(),lv,str,jsonUrl,flag);

        return view;
    }
}
