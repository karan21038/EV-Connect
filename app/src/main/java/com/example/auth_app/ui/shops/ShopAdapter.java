package com.example.auth_app.ui.shops;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auth_app.R;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<com.example.auth_app.ui.shops.ShopAdapter.ViewHolder> {
    private ShopFragment shopFragment;
    private List<ShopListItem> shopListItems;
//    private ItemClickListener clickListener;

    public ShopAdapter(List<ShopListItem> shopListItems, ShopFragment shopFragment) {
        this.shopListItems = shopListItems;
        this.shopFragment = shopFragment;
    }

    @NonNull
    @Override
    public com.example.auth_app.ui.shops.ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_list_item, parent, false);
        return new com.example.auth_app.ui.shops.ShopAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.auth_app.ui.shops.ShopAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.shopNumber.setText("Shop No. - " + String.valueOf(shopListItems.get(position).getIndex()));
        holder.shopStatus.setText("Shop Status - " + String.valueOf(shopListItems.get(position).getStatus()));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("","In bindViewHolder ######");
//                clickListener.onItemClick(shopListItems.get(position));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return shopListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView shopNumber, shopStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopNumber = itemView.findViewById(R.id.shop_number);
            shopStatus = itemView.findViewById(R.id.shop_status);
        }
        @Override
        public void onClick(View view) {
        }
    }

    public interface ItemClickListener {
        public void onItemClick(ShopListItem shopListItem);
    }
}
