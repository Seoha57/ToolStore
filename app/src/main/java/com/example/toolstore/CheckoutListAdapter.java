package com.example.toolstore;

import android.content.Context;
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

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.CheckoutListViewHolder> {
    private ArrayList<CheckoutList> checkoutListArrayList;

    public CheckoutListAdapter(ArrayList<CheckoutList> arrayList)
    {
        this.checkoutListArrayList = arrayList;
    }

    @NonNull
    @Override
    public CheckoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_items, parent, false);
        CheckoutListViewHolder checkoutListViewHolder = new CheckoutListViewHolder(view);
        return checkoutListViewHolder;
    }

    @Override
    public void onBindViewHolder(final CheckoutListViewHolder holder, final int position) {
        holder.layer_cancelOrder.setVisibility(View.GONE);
        holder.tv_toolOrderName.setText(String.valueOf(checkoutListArrayList.get(position).getItemName()));
        holder.tv_toolOrderMaker.setText(String.valueOf(checkoutListArrayList.get(position).getItemMaker()));
        holder.tv_toolOrderSize.setText(String.valueOf(checkoutListArrayList.get(position).getItemSize()));
        holder.tv_toolOrderAmount.setText(String.valueOf(checkoutListArrayList.get(position).getItemAmount()));
        holder.tv_toolTotalPrice.setText(String.valueOf(checkoutListArrayList.get(position).getItemPrice()));
        holder.tv_itemState.setText(String.valueOf(checkoutListArrayList.get(position).getCheckoutState()));

        String state = holder.tv_itemState.getText().toString();
        if(!state.equals("Not Checked") && !state.equals("Checked"))
        {
            holder.cb_deleteItemSelect.setVisibility(View.GONE);
        }
        else
        {
            holder.cb_deleteItemSelect.setChecked(checkoutListArrayList.get(position).isChecked());
            holder.cb_deleteItemSelect.setTag(position);
            holder.cb_deleteItemSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos = (Integer) holder.cb_deleteItemSelect.getTag();
                    Toast.makeText(v.getContext(), checkoutListArrayList.get(pos).getItemName() + " clicked!", Toast.LENGTH_SHORT).show();
                    if(checkoutListArrayList.get(position).isChecked()) {
                        holder.layer_cancelOrder.setVisibility(View.GONE);
                        checkoutListArrayList.get(position).setChecked(false);
                    }
                    else {
                        holder.layer_cancelOrder.setVisibility(View.VISIBLE);
                        checkoutListArrayList.get(position).setChecked(true);
                    }
                }
            });

            holder.btn_cancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String cancelOrder = "Canceled";
                    String userID = ((UserCheckoutList)v.getContext()).GetUserID();
                    String orderName = holder.tv_toolOrderName.getText().toString();
                    String orderMaker = holder.tv_toolOrderMaker.getText().toString();
                    String orderSize = holder.tv_toolOrderSize.getText().toString();
                    int orderAmount = Integer.parseInt(holder.tv_toolOrderAmount.getText().toString());
                    Response.Listener<String> stringListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("cancel order", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if(success)
                                {
                                    Toast.makeText(v.getContext(), "Order State Updated.", Toast.LENGTH_SHORT).show();
                                    checkoutListArrayList.get(position).setChecked(false);
                                    holder.cb_deleteItemSelect.setChecked(checkoutListArrayList.get(position).isChecked());
                                    holder.layer_cancelOrder.setVisibility(View.GONE);
                                    ((UserCheckoutList)v.getContext()).Refresh();
                                }
                                else
                                {
                                    Toast.makeText(v.getContext(), "Update Fail.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    UpdateOrderStateRequest updateOrderStateRequest = new UpdateOrderStateRequest(cancelOrder, userID, orderName, orderMaker, orderSize, orderAmount, stringListener);
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    queue.add(updateOrderStateRequest);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (null != checkoutListArrayList ? checkoutListArrayList.size() : 0);
    }

    public class CheckoutListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_toolOrderName, tv_toolOrderMaker, tv_toolOrderSize, tv_toolOrderAmount, tv_toolTotalPrice, tv_itemState;
        CheckBox cb_deleteItemSelect;
        LinearLayout layer_cancelOrder;
        Button btn_cancelOrder;
        public CheckoutListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_toolOrderName = itemView.findViewById(R.id.tv_toolOrderName);
            this.tv_toolOrderMaker = itemView.findViewById(R.id.tv_toolOrderMaker);
            this.tv_toolOrderSize = itemView.findViewById(R.id.tv_toolOrderSize);
            this.tv_toolOrderAmount = itemView.findViewById(R.id.tv_toolOrderAmount);
            this.tv_toolTotalPrice = itemView.findViewById(R.id.tv_toolTotalPrice);
            this.tv_itemState = itemView.findViewById(R.id.tv_itemState);
            this.cb_deleteItemSelect = itemView.findViewById(R.id.cb_deleteItemSelect);
            this.layer_cancelOrder = itemView.findViewById(R.id.layer_cancelOrder);
            this.btn_cancelOrder = itemView.findViewById(R.id.btn_cancelOrder);
        }
    }
}
