package com.example.toolstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemInfoAdapter extends RecyclerView.Adapter<ItemInfoAdapter.ItemInfoViewHolder> {

    private ArrayList<Items> itemsArrayList;

    public ItemInfoAdapter(ArrayList<Items> arrayList)
    {
        this.itemsArrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ItemInfoViewHolder itemInfoViewHolder = new ItemInfoViewHolder(view);
        return itemInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemInfoViewHolder holder, int position) {
        holder.tv_toolName.setText(itemsArrayList.get(position).getItemName());
        holder.tv_toolMaker.setText(itemsArrayList.get(position).getItemMaker());
        holder.tv_toolSize.setText(itemsArrayList.get(position).getItemSize());
        holder.tv_toolAmount.setText(String.valueOf(itemsArrayList.get(position).getItemAmount()));
        holder.tv_toolPrice.setText(String.valueOf(itemsArrayList.get(position).getItemPrice()));
    }

    @Override
    public int getItemCount() { return (itemsArrayList != null ? itemsArrayList.size() : 0); }

    public class ItemInfoViewHolder extends RecyclerView.ViewHolder{
        TextView tv_toolName, tv_toolMaker, tv_toolSize, tv_toolAmount, tv_toolPrice;
        public ItemInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_toolName = itemView.findViewById(R.id.tv_toolName);
            this.tv_toolMaker = itemView.findViewById(R.id.tv_toolMaker);
            this.tv_toolSize = itemView.findViewById(R.id.tv_toolSize);
            this.tv_toolAmount = itemView.findViewById(R.id.tv_toolAmount);
            this.tv_toolPrice = itemView.findViewById(R.id.tv_toolPrice);
        }
    }
}
