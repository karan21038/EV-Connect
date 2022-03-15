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
    public TextView stn_detail_name;
    public TextView stn_detail_addr;
    public TextView stn_detail_price;
    public TextView stn_detail_rating;

    private int mParam1;
    //public StationListItem modelObject;
    public String result;
    public StationDetailFragment(String result) {

        this.result = result;

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
        stn_detail_name  = view.findViewById(R.id.station_name);
        stn_detail_addr = view.findViewById(R.id.station_address);
        stn_detail_price = view.findViewById(R.id.station_price);
        stn_detail_rating = view.findViewById(R.id.station_rating);


        //Splitting result
        String[] res_arr = result.split("\\$");


        Log.i("REsult",res_arr[0]);
        stn_detail_name.setText(res_arr[1]);
        stn_detail_addr.setText(res_arr[2]);
        stn_detail_price.setText(String.valueOf(res_arr[3]));
        stn_detail_rating.setText(String.valueOf(res_arr[4]));

        Log.i("HEREREEEE",StationFragment.name);
        //sts_detail_num.setText(String.valueOf(sts_number));;
        return view;
    }
}