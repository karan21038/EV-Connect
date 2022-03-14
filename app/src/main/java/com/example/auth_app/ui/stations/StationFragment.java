package com.example.auth_app.ui.stations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auth_app.R;
import com.example.auth_app.databinding.FragmentStationBinding;

import java.util.ArrayList;
import java.util.List;

public class StationFragment extends Fragment {

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
        for(int i = 0; i <= 20; i++) {
            StationListItem stationlistItem = new StationListItem(i, false);
            stationListItems.add(stationlistItem);
        }

        adapter = new StationAdapter(stationListItems, this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}