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

public class RingFragment extends Fragment {

    View view;
    ListView dataList;
    String jsonUrl="http://bkeiprschedule.esy.es/Ring.php";
    String  flag = "ring";
    String str = "КН-13";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ring, container, false);

        dataList = (ListView) view.findViewById(R.id.ringList);

        new Query(getActivity(),dataList,str,jsonUrl,flag);

        return view;
    }

}
