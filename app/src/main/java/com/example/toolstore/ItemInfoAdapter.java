package com.example.toolstore;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
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

import java.math.BigDecimal;
import java.util.ArrayList;

public class ItemInfoAdapter extends RecyclerView.Adapter<ItemInfoAdapter.ItemInfoViewHolder> {

    private ArrayList<Items> itemsArrayList;
    private Context context;
    private String userID;

    public ItemInfoAdapter(Context context, ArrayList<Items> arrayList, String userID)
    {
        this.itemsArrayList = arrayList;
        this.context = context;
        this.userID = userID;
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
        holder.layer_addToCart.setVisibility(View.GONE);
        holder.tv_toolName.setText(String.valueOf(itemsArrayList.get(position).getItemName()));
        holder.tv_toolMaker.setText(String.valueOf(itemsArrayList.get(position).getItemMaker()));
        holder.tv_toolSize.setText(String.valueOf(itemsArrayList.get(position).getItemSize()));
        holder.tv_toolAmount.setText(String.valueOf(itemsArrayList.get(position).getItemAmount()));
        holder.tv_toolPrice.setText(String.valueOf(itemsArrayList.get(position).getItemPrice()));

        holder.cb_itemSelect.setChecked(itemsArrayList.get(position).isChecked());

        holder.cb_itemSelect.setTag(position);
        holder.cb_itemSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Integer pos = (Integer) holder.cb_itemSelect.getTag();
                Toast.makeText(context, itemsArrayList.get(pos).getItemName() + " clicked!", Toast.LENGTH_SHORT).show();
                if(itemsArrayList.get(position).isChecked()) {
                    holder.layer_addToCart.setVisibility(View.GONE);
                    itemsArrayList.get(position).setChecked(false);
                }
                else {
                    holder.layer_addToCart.setVisibility(View.VISIBLE);
                    itemsArrayList.get(position).setChecked(true);
                }
            }
        });

        holder.btn_addToCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean isNumber;
                try{
                    BigDecimal n = new BigDecimal(holder.et_amountItem.getText().toString());
                    isNumber = true;
                }catch (Exception e) {
                    isNumber = false;
                }
                if(!isNumber)
                {
                    Toast.makeText(context, "Input amount of item", Toast.LENGTH_SHORT).show();
                    return;
                }
                int requestAmount = Integer.parseInt(holder.et_amountItem.getText().toString());
                int leftAmount = itemsArrayList.get(position).getItemAmount();

                if(leftAmount < requestAmount || requestAmount <= 0)
                {
                    Toast.makeText(context, "We have only "+ itemsArrayList.get(position).getItemAmount() + " left in stock. Please check again.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String toolName = holder.tv_toolName.getText().toString();
                    String toolMaker = holder.tv_toolMaker.getText().toString();
                    String toolSize = holder.tv_toolSize.getText().toString();
                    int toolAmount = Integer.parseInt(holder.et_amountItem.getText().toString());
                    int toolPrice = Integer.parseInt(holder.tv_toolPrice.getText().toString());

                    Response.Listener<String> responseListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            Log.e("Add To Cart Response", "Active");
                            try
                            {
                                Log.e("anyText", response);
                                JSONObject jsonObject = new JSONObject(response); // to know success or not
                                boolean success = jsonObject.getBoolean("success");
                                if(success)
                                {
                                    Toast.makeText(context, holder.et_amountItem.getText() + "items are added to cart.", Toast.LENGTH_SHORT).show();

                                    holder.et_amountItem.getText().clear();
                                    itemsArrayList.get(position).setChecked(false);
                                    holder.cb_itemSelect.setChecked(itemsArrayList.get(position).isChecked());
                                }
                                else
                                {
                                    Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show();
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
                    AddToCartRequest addToCartRequest = new AddToCartRequest(userID, toolName, toolMaker, toolSize, toolAmount, toolPrice, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(context);
                    queue.add(addToCartRequest);
                }
            }
        });
    }

    @Override
    public int getItemCount() { return (itemsArrayList != null ? itemsArrayList.size() : 0); }

    public class ItemInfoViewHolder extends RecyclerView.ViewHolder{
        TextView tv_toolName, tv_toolMaker, tv_toolSize, tv_toolAmount, tv_toolPrice;
        CheckBox cb_itemSelect;
        LinearLayout layer_addToCart;
        EditText et_amountItem;
        Button btn_addToCart;
        public ItemInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_toolName = itemView.findViewById(R.id.tv_toolName);
            this.tv_toolMaker = itemView.findViewById(R.id.tv_toolMaker);
            this.tv_toolSize = itemView.findViewById(R.id.tv_toolSize);
            this.tv_toolAmount = itemView.findViewById(R.id.tv_toolAmount);
            this.tv_toolPrice = itemView.findViewById(R.id.tv_toolPrice);
            this.cb_itemSelect = itemView.findViewById(R.id.cb_itemSelect);
            this.layer_addToCart = itemView.findViewById(R.id.layer_addToCart);
            this.et_amountItem = itemView.findViewById(R.id.et_amountItem);
            this.btn_addToCart = itemView.findViewById(R.id.btn_addToCart);
        }
    }
}
