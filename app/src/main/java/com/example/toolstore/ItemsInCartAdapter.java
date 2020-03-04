package com.example.toolstore;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsInCartAdapter extends RecyclerView.Adapter<ItemsInCartAdapter.ItemsInCartViewHolder> {
    private ArrayList<ItemsInCart> itemsInCartArrayList;
    private Context context;
    private String userID;

    public ItemsInCartAdapter(Context context, ArrayList<ItemsInCart> arrayList, String userID)
    {
        this.itemsInCartArrayList = arrayList;
        this.context = context;
        this.userID = userID;
    }

    @NonNull
    @Override
    public ItemsInCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        ItemsInCartViewHolder itemsInCartViewHolder = new ItemsInCartViewHolder(view);
        return itemsInCartViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemsInCartViewHolder holder, final int position) {
        holder.layer_deleteFromCart.setVisibility(View.GONE);
        holder.tv_toolOrderName.setText(String.valueOf(itemsInCartArrayList.get(position).getItemName()));
        holder.tv_toolOrderMaker.setText(String.valueOf(itemsInCartArrayList.get(position).getItemMaker()));
        holder.tv_toolOrderSize.setText(String.valueOf(itemsInCartArrayList.get(position).getItemSize()));
        holder.tv_toolOrderAmount.setText(String.valueOf(itemsInCartArrayList.get(position).getAmount()));
        holder.tv_toolTotalPrice.setText(String.valueOf(itemsInCartArrayList.get(position).getTotalPrice()));

        holder.cb_deleteItemSelect.setChecked(itemsInCartArrayList.get(position).isChecked());

        holder.cb_deleteItemSelect.setTag(position);
    }

    @Override
    public int getItemCount() { return (itemsInCartArrayList != null ? itemsInCartArrayList.size() : 0); }

    public class ItemsInCartViewHolder extends RecyclerView.ViewHolder {
        TextView tv_toolOrderName, tv_toolOrderMaker, tv_toolOrderSize, tv_toolOrderAmount, tv_toolTotalPrice;
        CheckBox cb_deleteItemSelect;
        LinearLayout layer_deleteFromCart;
        Button btn_deleteFromCart;
        public ItemsInCartViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_toolOrderName = itemView.findViewById(R.id.tv_toolOrderName);
            this.tv_toolOrderMaker = itemView.findViewById(R.id.tv_toolOrderMaker);
            this.tv_toolOrderSize = itemView.findViewById(R.id.tv_toolOrderSize);
            this.tv_toolOrderAmount = itemView.findViewById(R.id.tv_toolOrderAmount);
            this.tv_toolTotalPrice = itemView.findViewById(R.id.tv_toolTotalPrice);
            this.cb_deleteItemSelect = itemView.findViewById(R.id.cb_deleteItemSelect);
            this.layer_deleteFromCart = itemView.findViewById(R.id.layer_deleteFromCart);
            this.btn_deleteFromCart = itemView.findViewById(R.id.btn_deleteFromCart);
        }
    }
}
