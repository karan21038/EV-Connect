package com.example.auth_app.ui.stations;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.auth_app.R;

public class StationDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    public TextView sts_detail_num;

    private int mParam1;
    public StationListItem modelObject;

    public StationDetailFragment(StationListItem modelObject) {

        this.modelObject = modelObject;

    }



//    public static StationDetailFragment newInstance(int param1) {
//        StationDetailFragment fragment = new StationDetailFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_PARAM1, param1);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_station_detail, container, false);
        sts_detail_num  = view.findViewById(R.id.station_num_detail);
        Log.i("",String.valueOf(modelObject.getStation_num()));
        sts_detail_num.setText(String.valueOf(modelObject.getStation_num()));
        return view;
    }
}