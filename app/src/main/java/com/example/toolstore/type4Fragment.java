package com.example.toolstore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class type4Fragment extends Fragment {
    private RecyclerView itemsInCartRecyclerView;
    private RecyclerView.Adapter itemsInCartAdapter;
    private RecyclerView.LayoutManager itemsInCartLayoutManager;
    private ArrayList<ItemsInCart> itemsInCartArrayList;
    private Button btn_checkout;

    public type4Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_type4_fragment, container, false);
        itemsInCartRecyclerView = view.findViewById(R.id.recyclerView_cart);
        itemsInCartLayoutManager = new LinearLayoutManager(getActivity());
        itemsInCartRecyclerView.setLayoutManager(itemsInCartLayoutManager);
        itemsInCartArrayList = new ArrayList<>();
        btn_checkout = view.findViewById(R.id.btn_checkout);

        final MainActivity mainActivity = (MainActivity)getActivity();
        final String userID = mainActivity.getUserID();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Cart", response);
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
                    String toolName = null, toolMaker = null, toolSize = null, amount = null;
                    for(int i = 1; i < jsonArray.length(); ++i) {
                        switch (i%5)
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
                            default:
                                jsonObject = jsonArray.getJSONObject(i);
                                String totalPrice = jsonObject.getString("totalPrice");
                                ItemsInCart itemsInCart = new ItemsInCart();
                                itemsInCart.setItemName(toolName);
                                itemsInCart.setItemMaker(toolMaker);
                                itemsInCart.setItemSize(toolSize);
                                itemsInCart.setAmount(Integer.parseInt(amount));
                                itemsInCart.setTotalPrice(Integer.parseInt(totalPrice));
                                itemsInCartArrayList.add(itemsInCart);
                        }
                    }
                    itemsInCartAdapter.notifyDataSetChanged();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        ItemsInCartRequest itemsInCartRequest = new ItemsInCartRequest(userID, responseListener);
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(itemsInCartRequest);

        itemsInCartAdapter = new ItemsInCartAdapter(getContext(), itemsInCartArrayList, userID, mainActivity);
        itemsInCartRecyclerView.setAdapter(itemsInCartAdapter);

         btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> stringListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Checkout", "Active");
                        try {
                            JSONObject jsonObject = new JSONObject(response); // to know success or not
                            Log.e("Checkout", response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getContext(), "Successfully Checkout", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Failed Checkout", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CheckoutRequest checkoutRequest = new CheckoutRequest(userID, stringListener);
                queue.add(checkoutRequest);
                mainActivity.refreshFragment();
            }
         });
        return view;
    }
}
