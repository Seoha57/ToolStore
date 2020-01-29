package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        recyclerView = findViewById(R.id.recyclerView_userInfo); //connect Id
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //user info array list (to adapter)

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
                    Log.e("length", jsonArray.length()+"");
                    String userID = null, userName = null, userCity = null, userZIPCode = null;

                    for(int i = 1; i < jsonArray.length(); ++i) {
                        switch (i%5)
                        {
                            case 1:
                                jsonObject = jsonArray.getJSONObject(i);
                                userID = jsonObject.getString("userID");
                                break;
                            case 2:
                                jsonObject = jsonArray.getJSONObject(i);
                                userName = jsonObject.getString("userName");
                                break;
                            case 3:
                                jsonObject = jsonArray.getJSONObject(i);
                                userCity = jsonObject.getString("city");
                                break;
                            case 4:
                                jsonObject = jsonArray.getJSONObject(i);
                                userZIPCode = jsonObject.getString("ZIPCode");
                                break;

                                default:
                                    jsonObject = jsonArray.getJSONObject(i);
                                    String userContact = jsonObject.getString("contact");
                                    User user = new User();
                                    user.setUserId(userID);
                                    user.setUserName(userName);
                                    user.setUserCity(userCity);
                                    user.setUserZIPCode(Integer.parseInt(userZIPCode));
                                    user.setUserContact(Integer.parseInt(userContact));
                                    arrayList.add(user);

                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        UserInfoRequest userInfoRequest = new UserInfoRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(UserInfoActivity.this);
        queue.add(userInfoRequest);

        adapter = new UserInfoAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }
}
