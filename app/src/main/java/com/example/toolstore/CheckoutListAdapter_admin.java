package com.example.toolstore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

public class CheckoutListAdapter_admin extends RecyclerView.Adapter<CheckoutListAdapter_admin.CheckList_admin_ViewHolder> {
    private ArrayList<CheckoutList_admin> arrayList;

    public CheckoutListAdapter_admin(ArrayList<CheckoutList_admin> arrayList)
    {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CheckList_admin_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_items_admin, parent, false);
        CheckList_admin_ViewHolder checkList_admin_viewHolder = new CheckList_admin_ViewHolder(view);
        return checkList_admin_viewHolder;
    }

    @Override
    public void onBindViewHolder(final CheckList_admin_ViewHolder holder, final int position) {
        holder.layer_updateCurrState.setVisibility(View.GONE);
        holder.tv_orderUserID.setText(String.valueOf(arrayList.get(position).getUserID()));
        holder.tv_toolOrderName.setText(String.valueOf(arrayList.get(position).getItemName()));
        holder.tv_toolOrderMaker.setText(String.valueOf(arrayList.get(position).getItemMaker()));
        holder.tv_toolOrderSize.setText(String.valueOf(arrayList.get(position).getItemSize()));
        holder.tv_toolOrderAmount.setText(String.valueOf(arrayList.get(position).getItemAmount()));
        holder.tv_toolTotalPrice.setText(String.valueOf(arrayList.get(position).getItemPrice()));
        holder.tv_itemState.setText(String.valueOf(arrayList.get(position).getCheckoutState()));
        holder.tv_orderDate.setText(String.valueOf(arrayList.get(position).getCheckoutDate()));

        String checkState = holder.tv_itemState.getText().toString();
        if(checkState.equals("Canceled")) {
            holder.cb_updateItemSelect.setVisibility(View.GONE);
        }
        else {
            holder.cb_updateItemSelect.setChecked(arrayList.get(position).isChecked());
            holder.cb_updateItemSelect.setTag(position);
            holder.cb_updateItemSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer pos = (Integer) holder.cb_updateItemSelect.getTag();
                    Toast.makeText(v.getContext(), arrayList.get(pos).getItemName() + " clicked!", Toast.LENGTH_SHORT).show();
                    if (arrayList.get(position).isChecked()) {
                        holder.layer_updateCurrState.setVisibility(View.GONE);
                        arrayList.get(position).setChecked(false);
                    } else {
                        holder.layer_updateCurrState.setVisibility(View.VISIBLE);
                        arrayList.get(position).setChecked(true);
                    }
                }
            });

            holder.btn_updateCurrState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String updateState = holder.spinner_currState.getSelectedItem().toString();
                    String userID = holder.tv_orderUserID.getText().toString();
                    String orderName = holder.tv_toolOrderName.getText().toString();
                    String orderMaker = holder.tv_toolOrderMaker.getText().toString();
                    String orderSize = holder.tv_toolOrderSize.getText().toString();
                    int orderAmount = Integer.parseInt(holder.tv_toolOrderAmount.getText().toString());

                    Response.Listener<String> stringListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("Update order state", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    Toast.makeText(v.getContext(), "Order State Updated.", Toast.LENGTH_SHORT).show();
                                    arrayList.get(position).setChecked(false);
                                    holder.cb_updateItemSelect.setChecked(arrayList.get(position).isChecked());
                                    holder.layer_updateCurrState.setVisibility(View.GONE);
                                    ((AdminCheckoutList) v.getContext()).Refresh();
                                } else {
                                    Toast.makeText(v.getContext(), "Update Fail.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    UpdateOrderStateRequest updateOrderStateRequest = new UpdateOrderStateRequest(updateState, userID, orderName, orderMaker, orderSize, orderAmount, stringListener);
                    RequestQueue queue = Volley.newRequestQueue(v.getContext());
                    queue.add(updateOrderStateRequest);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CheckList_admin_ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_orderUserID, tv_toolOrderName, tv_toolOrderMaker, tv_toolOrderSize, tv_toolOrderAmount, tv_toolTotalPrice, tv_itemState, tv_orderDate;
        CheckBox cb_updateItemSelect;
        LinearLayout layer_updateCurrState;
        Button btn_updateCurrState;
        Spinner spinner_currState;
        public CheckList_admin_ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_orderUserID = itemView.findViewById(R.id.tv_orderUserID);
            this.tv_toolOrderName = itemView.findViewById(R.id.tv_toolOrderName);
            this.tv_toolOrderMaker = itemView.findViewById(R.id.tv_toolOrderMaker);
            this.tv_toolOrderSize = itemView.findViewById(R.id.tv_toolOrderSize);
            this.tv_toolOrderAmount = itemView.findViewById(R.id.tv_toolOrderAmount);
            this.tv_toolTotalPrice = itemView.findViewById(R.id.tv_toolTotalPrice);
            this.tv_itemState = itemView.findViewById(R.id.tv_itemState);
            this.tv_orderDate = itemView.findViewById(R.id.tv_orderDate);
            this.cb_updateItemSelect = itemView.findViewById(R.id.cb_updateItemSelect);
            this.layer_updateCurrState = itemView.findViewById(R.id.layer_updateCurrState);
            this.btn_updateCurrState = itemView.findViewById(R.id.btn_updateCurrState);
            this.spinner_currState = itemView.findViewById(R.id.spinner_currState);
        }
    }
}
