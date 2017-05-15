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


public class TeachersFragment extends Fragment {

    View view;
    ListView dataList;
    String str;
    String jsonUrl="http://bkeiprschedule.esy.es/Teachers.php";
    String  flag = "teacher";

    public TeachersFragment(String str) {

        this.str=str;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.teachers, container, false);
        dataList = (ListView) view.findViewById(R.id.teachersList);

        new Query(getActivity(),dataList,str,jsonUrl,flag);

        return view;
    }

}
