package com.example.toolstore;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemInfoAdapter extends RecyclerView.Adapter<ItemInfoAdapter.ItemInfoViewHolder> {

    private ArrayList<Items> itemsArrayList;
    private Context context;

    public ItemInfoAdapter(Context context, ArrayList<Items> arrayList)
    {
        this.itemsArrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ItemInfoViewHolder itemInfoViewHolder = new ItemInfoViewHolder(view);
        return itemInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemInfoViewHolder holder, final int position) {
        holder.tv_toolName.setText(String.valueOf(itemsArrayList.get(position).getItemName()));
        holder.tv_toolMaker.setText(String.valueOf(itemsArrayList.get(position).getItemMaker()));
        holder.tv_toolSize.setText(String.valueOf(itemsArrayList.get(position).getItemSize()));
        holder.tv_toolAmount.setText(String.valueOf(itemsArrayList.get(position).getItemAmount()));
        holder.tv_toolPrice.setText(String.valueOf(itemsArrayList.get(position).getItemPrice()));

        holder.cb_itemSelect.setText(String.valueOf(position));
        holder.cb_itemSelect.setChecked(itemsArrayList.get(position).isChecked());

        holder.cb_itemSelect.setTag(position);
        holder.cb_itemSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Integer pos = (Integer) holder.cb_itemSelect.getTag();
                Toast.makeText(context, itemsArrayList.get(pos).getItemName() + " clicked!", Toast.LENGTH_SHORT).show();
                if(itemsArrayList.get(position).isChecked())
                    itemsArrayList.get(position).setChecked(false);
                else
                    itemsArrayList.get(position).setChecked(true);
            }
        });
    }

    @Override
    public int getItemCount() { return (itemsArrayList != null ? itemsArrayList.size() : 0); }

    public class ItemInfoViewHolder extends RecyclerView.ViewHolder{
        TextView tv_toolName, tv_toolMaker, tv_toolSize, tv_toolAmount, tv_toolPrice;
        CheckBox cb_itemSelect;
        public ItemInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_toolName = itemView.findViewById(R.id.tv_toolName);
            this.tv_toolMaker = itemView.findViewById(R.id.tv_toolMaker);
            this.tv_toolSize = itemView.findViewById(R.id.tv_toolSize);
            this.tv_toolAmount = itemView.findViewById(R.id.tv_toolAmount);
            this.tv_toolPrice = itemView.findViewById(R.id.tv_toolPrice);
            this.cb_itemSelect = itemView.findViewById(R.id.cb_itemSelect);
        }
    }
}
