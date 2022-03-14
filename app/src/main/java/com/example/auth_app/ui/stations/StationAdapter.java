package com.example.auth_app.ui.stations;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auth_app.R;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {
    private StationFragment stationFragment;
    private List<StationListItem> stationListItems;
//    private ItemClickListener clickListener;

    public StationAdapter(List<StationListItem> stationListItems, StationFragment stationFragment) {
        this.stationListItems = stationListItems;
        this.stationFragment = stationFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.station_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.stationNumber.setText("Station No. - " + String.valueOf(stationListItems.get(position).getIndex()));
        holder.stationStatus.setText("Station Status - " + String.valueOf(stationListItems.get(position).getStatus()));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("","In bindViewHolder ######");
//                clickListener.onItemClick(stationListItems.get(position));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return stationListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView stationNumber, stationStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stationNumber = itemView.findViewById(R.id.station_number);
            stationStatus = itemView.findViewById(R.id.station_status);
        }
        @Override
        public void onClick(View view) {
        }
    }

    public interface ItemClickListener {
        public void onItemClick(StationListItem stationListItem);
    }
}
