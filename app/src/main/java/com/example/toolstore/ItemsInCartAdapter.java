package com.example.toolstore;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemsInCartAdapter extends RecyclerView.Adapter<ItemsInCartAdapter.ItemsInCartViewHolder> {
    private ArrayList<ItemsInCart> itemsInCartArrayList;
    private Context context;
    private String userID;
    private MainActivity mainActivity;

    public ItemsInCartAdapter(Context context, ArrayList<ItemsInCart> arrayList, String userID, MainActivity mainActivity)
    {
        this.itemsInCartArrayList = arrayList;
        this.context = context;
        this.userID = userID;
        this.mainActivity = mainActivity;
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
        holder.cb_deleteItemSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Integer pos = (Integer) holder.cb_deleteItemSelect.getTag();
                Toast.makeText(context, itemsInCartArrayList.get(pos).getItemName() + " clicked!", Toast.LENGTH_SHORT).show();
                if(itemsInCartArrayList.get(position).isChecked()) {
                    holder.layer_deleteFromCart.setVisibility(View.GONE);
                    itemsInCartArrayList.get(position).setChecked(false);
                }
                else {
                    holder.layer_deleteFromCart.setVisibility(View.VISIBLE);
                    itemsInCartArrayList.get(position).setChecked(true);
                }
            }
        });

        holder.btn_deleteFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toolName = holder.tv_toolOrderName.getText().toString();
                String toolMaker = holder.tv_toolOrderMaker.getText().toString();
                String toolSize = holder.tv_toolOrderSize.getText().toString();

                Response.Listener<String> responsListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Remove From Cart", "Active");
                        try {
                            Log.e("anyText", response);
                            JSONObject jsonObject = new JSONObject(response); // to know success or not
                            boolean success = jsonObject.getBoolean("success");
                            if(success)
                            {
                                Toast.makeText(context, "Item Deleted.", Toast.LENGTH_SHORT).show();

                                itemsInCartArrayList.get(position).setChecked(false);
                                holder.cb_deleteItemSelect.setChecked(itemsInCartArrayList.get(position).isChecked());
                                holder.layer_deleteFromCart.setVisibility(View.GONE);
                            }
                            else
                            {
                                Toast.makeText(context, "Remove Failed", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                // Send request to server with Volley.
                RemoveFromCartRequest removeFromCartRequest = new RemoveFromCartRequest(userID, toolName, toolMaker, toolSize, responsListener);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(removeFromCartRequest);

                // Refresh fragment
                mainActivity.refreshFragment();
            }
        });
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
