package com.example.auth_app.ui.stations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auth_app.R;
import com.example.auth_app.databinding.FragmentStationBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StationFragment extends Fragment implements StationAdapter.ItemClickListener{

    private FragmentStationBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public ArrayList<StationListItem> stationListItems;
    public static  final String EXTRA_TEXT = "com.example.auth_app.ui.stations.EXTRA_TEXT";
    public static String name;
    public static double price ;
    public static double lati ;
    public static double longi ;
    public static String address ;
    public static String strtTime ;
    public static String closeTime ;
    public static double rating;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StationViewModel stationViewModel =
                new ViewModelProvider(this).get(StationViewModel.class);

        binding = FragmentStationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = (RecyclerView) root.findViewById(R.id.stationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        stationListItems = new ArrayList<>();

        //Database work

        for(int i=1;i<=5;i++) {

            int finalI = i;
            FirebaseDatabase.getInstance().getReference().child("Stations").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                private static final String TAG = "StationFragment";

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
//
                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        Log.i("TAG", "map>>>>>: " + map);
                        name = (String) map.get("Name");
                        price = (double) map.get("Price");
                        lati = (double) map.get("Latitude");
                        longi = (double) map.get("Longitude");
                        address = (String) map.get("Address");
                        strtTime = (String) map.get("Starting Time");
                        closeTime = (String) map.get("Closing Time");
                        rating = (double) map.get("Rating");
//               Log.i("TAG", "MAPPPPPP>>>>>: "+map);
                        Log.i("TAG", "KRKRKRKRK>>>>>: " + rating);

                        stationListItems.add(new StationListItem(finalI, name, lati, longi, price, strtTime, closeTime, rating, address));
//                    String data = snapshot.getValue().toString();
                        Log.i("TAG", "JJKKK>>>>>: " + stationListItems.get(0).getStation_name());

                    }
                    adapter = new StationAdapter(stationListItems, StationFragment.this, StationFragment.this);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
//        for(int i = 1; i <= 5; i++) {
//            StationListItem stationlistItem = new StationListItem(i,"PlugNgo Charging Station "+i,28.621734973925065,77.29355392625062,20.10,"9:00 am","18:00 pm",4,"PlugNgo Charging Station, Mother Dairy Marg, I.P.Extension, Mandawali, New Delhi, Delhi, India");
//            stationListItems.add(stationlistItem);
//        }

        //Log.i("TAG", "JJKKK>>>>>: "+stationlistItem.getStation_name());

//        Log.i("TAG", "JJKKK>>>>>: "+stationListItems.get(0));
//        adapter = new StationAdapter(stationListItems,this,this);
//        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(StationListItem modelObject) {

        int station_num = modelObject.getStation_num();
        String station_name = modelObject.getStation_name();
        String address = modelObject.getAddress();
        double price = modelObject.getPrice();
        double rating = modelObject.getRatings();
        double latitude = modelObject.getLati();
        double longitude = modelObject.getLongi();
        String res = station_num+"$"+station_name+"$"+address+"$"+price+"$"+rating+"$"+latitude+"$"+longitude;

        Intent serviceIntent = new Intent(getActivity(), StationDetailsActivity.class);
        serviceIntent.putExtra(EXTRA_TEXT,res);
        startActivity(serviceIntent);
    }
}