package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id;
    private ImageButton btn_refresh;
    private Button btn_1, btn_2, btn_3, btn_4;
    private String userID;

    private ArrayList<Fragment> fragments;
    private Integer fragPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        btn_refresh = findViewById(R.id.btn_refresh);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);

        type1Fragment fragment1 = new type1Fragment();
        type2Fragment fragment2 = new type2Fragment();
        type3Fragment fragment3 = new type3Fragment();
        type4Fragment fragment4 = new type4Fragment();

        fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragPos = 0;

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragments.get(fragPos)); // 조각 교체
        transaction.commit();

        tv_id.setText("Hello, " + userID);

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MainActivity.super.onRestart();
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("userID", userID);
                startActivity(i);
                finish();
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragPos = 0;
                refreshFragment();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragPos = 1;
                refreshFragment();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragPos = 2;
                refreshFragment();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragPos = 3;
                refreshFragment();
            }
        });

        Toast.makeText(getApplicationContext(), tv_id.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public String getUserID()
    {
        return userID;
    }

    public void refreshFragment()
    {
        Log.e("refresh check", fragPos.toString());
        Fragment currFragment = fragments.get(fragPos);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.detach(currFragment);
        transaction.attach(currFragment);
        transaction.replace(R.id.frame, currFragment);
        transaction.commit();
    }
}
