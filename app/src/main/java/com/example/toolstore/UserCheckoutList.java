package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserCheckoutList extends AppCompatActivity {

    private ArrayList<CheckoutList> arrayList;
    private RecyclerView checkoutListRecyclerView;
    private RecyclerView.Adapter checkoutListAdapter;
    private RecyclerView.LayoutManager checkoutListLayoutManager;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_list);

        checkoutListRecyclerView = findViewById(R.id.recyclerView_checkoutList);
        checkoutListLayoutManager = new LinearLayoutManager(this);
        checkoutListRecyclerView.setLayoutManager(checkoutListLayoutManager);

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
                    String toolName = null, toolMaker = null, toolSize = null, amount = null, price = null;
                    for(int i = 1; i < jsonArray.length(); ++i) {
                        switch (i%6)
                        {
                            case 1:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolName = jsonObject.getString("toolName");
                                break;
                            case 2:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolMaker = jsonObject.getString("toolMaker");
                                break;
                            case 3:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolSize = jsonObject.getString("toolSize");
                                break;
                            case 4:
                                jsonObject = jsonArray.getJSONObject(i);
                                amount = jsonObject.getString("amount");
                                break;
                            case 5:
                                jsonObject = jsonArray.getJSONObject(i);
                                price = jsonObject.getString("price");
                                break;
                            default:
                                jsonObject = jsonArray.getJSONObject(i);
                                String state = jsonObject.getString("state");
                                CheckoutList checkoutList = new CheckoutList();
                                checkoutList.setItemName(toolName);
                                checkoutList.setItemMaker(toolMaker);
                                checkoutList.setItemSize(toolSize);
                                checkoutList.setItemAmount(Integer.parseInt(amount));
                                checkoutList.setItemPrice(Integer.parseInt(price));
                                checkoutList.setCheckoutState(state);
                                arrayList.add(checkoutList);
                        }
                    }
                    checkoutListAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CheckoutListRequest checkoutRequest = new CheckoutListRequest(userID, listener);
        final RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(checkoutRequest);

        checkoutListAdapter = new CheckoutListAdapter(arrayList);
        checkoutListRecyclerView.setAdapter(checkoutListAdapter);
    }

    public void Refresh() { recreate(); }
    public String GetUserID()
    {
        return userID;
    }
}
