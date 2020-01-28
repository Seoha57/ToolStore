package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private TextView tv_ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        recyclerView = findViewById(R.id.recyclerView_userInfo); //connect Id
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //user info array list (to adapter)

        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success)
                    {
                        String userID = jsonObject.getString("userID");
                        String userName = jsonObject.getString("userName");
                        String userCity = jsonObject.getString("city");
                        String userZIPCode = jsonObject.getString("ZIPCode");
                        String userContact = jsonObject.getString("contact");

                        User user = new User();
                        user.setUserId(userID);
                        user.setUserName(userName);
                        user.setUserCity(userCity);
                        user.setUserZIPCode(Integer.parseInt(userZIPCode));
                        user.setUserContact(Integer.parseInt(userContact));
                        arrayList.add(user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        UserInfoRequest userInfoRequest = new UserInfoRequest(responseListner);
        RequestQueue queue = Volley.newRequestQueue(UserInfoActivity.this);
        queue.add(userInfoRequest);
    }
}
