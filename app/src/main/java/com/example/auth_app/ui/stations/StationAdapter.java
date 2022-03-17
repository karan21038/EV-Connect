package com.example.auth_app.ui.stations;

import android.annotation.SuppressLint;
import android.util.Log;
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
    private ItemClickListener clickListener;

    public StationAdapter(List<StationListItem> stationListItems,StationFragment stationFragment, ItemClickListener clickListener) {
        this.stationListItems = stationListItems;
        this.clickListener = clickListener;
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
        holder.stn_name.setText(String.valueOf(stationListItems.get(position).getStation_name()));
        holder.stn_rating.setText("Rating - " + String.valueOf(stationListItems.get(position).getRatings()));
        holder.stn_price.setText("Price - " + String.valueOf(stationListItems.get(position).getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("","In bindViewHolder ######");
                clickListener.onItemClick(stationListItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationListItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView stn_name, stn_price, stn_rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stn_name = itemView.findViewById(R.id.station_name);
            stn_price = itemView.findViewById(R.id.station_price);
            stn_rating = itemView.findViewById(R.id.station_rating);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(StationListItem stationListItem);
    }
}
