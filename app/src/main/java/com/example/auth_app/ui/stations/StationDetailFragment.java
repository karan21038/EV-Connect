package com.example.auth_app.ui.stations;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.auth_app.R;
import com.example.auth_app.ui.maps.StationMapActivity;

public class StationDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    public TextView sts_detail_num;
    public TextView stn_detail_name;
    public TextView stn_detail_addr;
    public TextView stn_detail_price;
    public TextView stn_detail_rating;
    public double latitude;
    public double longitude;
    public TextView stn_detail_status;
    public TextView stn_detail_opening_time;
    public TextView stn_detail_closing_time;
    public Button trackStationBtn;
    private int mParam1;
    //public StationListItem modelObject;
    public String result;
    public static  final String EXTRA_TEXT = "com.example.auth_app.ui.stations.EXTRA_TEXT";

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
        stn_detail_status = view.findViewById(R.id.station_status);
        stn_detail_opening_time = view.findViewById(R.id.station_opening_time);
        stn_detail_closing_time = view.findViewById(R.id.station_closing_time);

        //Splitting result
        String[] res_arr = result.split("\\$");


        Log.i("Result",res_arr[0]);
        stn_detail_name.setText(res_arr[1]);
        stn_detail_addr.setText("" + res_arr[2]);
        stn_detail_price.setText("Price: " + String.valueOf(res_arr[3]));
        stn_detail_rating.setText("Rating: " + String.valueOf(res_arr[4]));
        stn_detail_status.setText(""+res_arr[7]);
        stn_detail_opening_time.setText("Opens at : "+res_arr[8]);
        stn_detail_closing_time.setText("Closes at : "+res_arr[9]);

        longitude = Double.parseDouble(res_arr[5]);
        latitude = Double.parseDouble(res_arr[6]);

        String res = longitude+"$"+latitude;

        trackStationBtn = view.findViewById(R.id.station_track);

        trackStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent serviceIntent = new Intent(getActivity(), StationMapActivity.class);
                serviceIntent.putExtra(EXTRA_TEXT,res);
                startActivity(serviceIntent);

            }
        });

        Log.i("HEREREEEE",StationFragment.name);
        //sts_detail_num.setText(String.valueOf(sts_number));;
        return view;
    }
}