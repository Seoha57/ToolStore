package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminCheckoutList extends AppCompatActivity {
    private ArrayList<CheckoutList_admin> arrayList;
    private RecyclerView adminCheckoutListRecyclerView;
    private RecyclerView.Adapter adminCheckoutListAdapter;
    private RecyclerView.LayoutManager adminCheckoutListLayoutManager;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_checkout_list);

        adminCheckoutListRecyclerView = findViewById(R.id.recyclerView_checkoutList_admin);
        adminCheckoutListLayoutManager = new LinearLayoutManager(this);
        adminCheckoutListRecyclerView.setLayoutManager(adminCheckoutListLayoutManager);
        arrayList = new ArrayList<>();

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Checkout List", response);
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
                    String orderUserID = null, toolName = null, toolMaker = null, toolSize = null, amount = null, price = null, state = null;
                    for(int i = 1; i < jsonArray.length(); ++i) {
                        switch (i%8)
                        {
                            case 1:
                                jsonObject = jsonArray.getJSONObject(i);
                                orderUserID = jsonObject.getString("userID");
                                break;
                            case 2:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolName = jsonObject.getString("toolName");
                                break;
                            case 3:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolMaker = jsonObject.getString("toolMaker");
                                break;
                            case 4:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolSize = jsonObject.getString("toolSize");
                                break;
                            case 5:
                                jsonObject = jsonArray.getJSONObject(i);
                                amount = jsonObject.getString("amount");
                                break;
                            case 6:
                                jsonObject = jsonArray.getJSONObject(i);
                                price = jsonObject.getString("price");
                                break;
                            case 7:
                                jsonObject = jsonArray.getJSONObject(i);
                                state = jsonObject.getString("state");
                                break;
                            default:
                                jsonObject = jsonArray.getJSONObject(i);
                                String orderDate = jsonObject.getString("checkout");
                                CheckoutList_admin checkoutList = new CheckoutList_admin();
                                checkoutList.setUserID(orderUserID);
                                checkoutList.setItemName(toolName);
                                checkoutList.setItemMaker(toolMaker);
                                checkoutList.setItemSize(toolSize);
                                checkoutList.setItemAmount(Integer.parseInt(amount));
                                checkoutList.setItemPrice(Integer.parseInt(price));
                                checkoutList.setCheckoutState(state);
                                checkoutList.setCheckoutDate(orderDate);
                                arrayList.add(checkoutList);
                        }
                    }
                    adminCheckoutListAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CheckoutListRequest checkoutListRequest = new CheckoutListRequest(userID, listener);
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(checkoutListRequest);

        adminCheckoutListAdapter = new CheckoutListAdapter_admin(arrayList);
        adminCheckoutListRecyclerView.setAdapter(adminCheckoutListAdapter);
    }

    public void Refresh() { recreate(); }
}
