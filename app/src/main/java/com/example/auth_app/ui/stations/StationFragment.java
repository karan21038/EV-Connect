package com.example.auth_app.ui.stations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auth_app.R;
import com.example.auth_app.databinding.FragmentStationBinding;

import java.util.ArrayList;
import java.util.List;

public class StationFragment extends Fragment implements StationAdapter.ItemClickListener{

    private FragmentStationBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<StationListItem> stationListItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StationViewModel stationViewModel =
                new ViewModelProvider(this).get(StationViewModel.class);

        binding = FragmentStationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = (RecyclerView) root.findViewById(R.id.stationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        stationListItems = new ArrayList<>();
        for(int i = 0; i < 1; i++) {
            StationListItem stationlistItem = new StationListItem(1,"PlugNgo Charging Station",28.621734973925065,77.29355392625062,20.10,"9:00 am","18:00 pm",4,"PlugNgo Charging Station, Mother Dairy Marg, I.P.Extension, Mandawali, New Delhi, Delhi, India");
            stationListItems.add(stationlistItem);
        }

        adapter = new StationAdapter(stationListItems,this,this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(StationListItem modelObject) {

//        Fragment fragment = StationDetailFragment.newInstance(modelObject.getStation_num());
//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        Log.i("before-replace", String.valueOf(modelObject.getStation_num()));
//        fragmentTransaction.replace(R.id.station_frame,fragment,"station_detail_frag");
//        Log.i("after-replace","No");
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//        Log.i("after-commit","No");

        FragmentManager fragmentTransaction = getActivity().getSupportFragmentManager();
        Fragment f3 = fragmentTransaction.findFragmentById(R.id.station_container); //replacing the news fragment on news click
        if(f3!=null){
            Log.i("","fragment not found");
            f3 = new StationDetailFragment(modelObject);
            Log.i("after-commit","No");
            fragmentTransaction.beginTransaction()
                    .replace(R.id.station_container,f3)
                    .addToBackStack(null)
                    .commit();
        }


    }

//    @Override
//    public void onNewsClick(NewsModel newsModel) { //implementing the function from NewsClick interface
//
//        FragmentManager fragmentTransaction = getActivity().getSupportFragmentManager();
//        Fragment f3 = fragmentTransaction.findFragmentById(R.id.frame2); //replacing the news fragment on news click
//        if(f3!=null){
//            Log.i(TAG,"fragment not found");
//            f3 = new Fragment3(newsModel);
//            fragmentTransaction.beginTransaction()
//                    .replace(R.id.frame2,f3)
//                    .addToBackStack(null)
//                    .commit();
//        }
}