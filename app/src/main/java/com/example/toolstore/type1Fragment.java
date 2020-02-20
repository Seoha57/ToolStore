package com.example.toolstore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class type1Fragment extends Fragment {
    private RecyclerView itemRecyclerView;
    private RecyclerView.Adapter itemAdapter;
    private RecyclerView.LayoutManager itemLayoutManager;
    private ArrayList<Items> itemArrayList;

    public type1Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_type1_fragment, container, false);
        itemRecyclerView = view.findViewById(R.id.recyclerView_BNInfo); //connect Id
        itemLayoutManager = new LinearLayoutManager(getActivity());
        itemRecyclerView.setLayoutManager(itemLayoutManager);
        itemArrayList = new ArrayList<>(); //user info array list (to adapter)
        String tableName = "B&N_TABLE";
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("anyText", response);
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
                    String toolName = null, toolMaker = null, toolSize = null, toolAmount = null;

                    for(int i = 1; i < jsonArray.length(); ++i) {
                        switch (i%5)
                        {
                            case 1:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolName = jsonObject.getString("name");
                                break;
                            case 2:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolMaker = jsonObject.getString("maker");
                                break;
                            case 3:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolSize = jsonObject.getString("size");
                                break;
                            case 4:
                                jsonObject = jsonArray.getJSONObject(i);
                                toolAmount = jsonObject.getString("amount");
                                break;

                            default:
                                jsonObject = jsonArray.getJSONObject(i);
                                String toolPrice = jsonObject.getString("price");
                                Items items = new Items();
                                items.setItemName(toolName);
                                items.setItemMaker(toolMaker);
                                items.setItemSize(toolSize);
                                items.setItemAmount(Integer.parseInt(toolAmount));
                                items.setItemPrice(Integer.parseInt(toolPrice));
                                itemArrayList.add(items);
                        }
                    }
                    itemAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ItemInfoRequest itemInfoRequest = new ItemInfoRequest(tableName, responseListener);
        RequestQueue queue = Volley.newRequestQueue(this.getActivity());
        queue.add(itemInfoRequest);

        itemAdapter = new ItemInfoAdapter(getContext(), itemArrayList);
        itemRecyclerView.setAdapter(itemAdapter);

        return view;
    }
}
