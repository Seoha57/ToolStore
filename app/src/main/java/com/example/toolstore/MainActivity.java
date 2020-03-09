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

public class MainActivity extends AppCompatActivity {

    private TextView tv_id;
    private ImageButton btn_refresh;
    private Button btn_1, btn_2, btn_3, btn_4;
    private String userID;

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

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        type1Fragment fragment1 = new type1Fragment();
        transaction.replace(R.id.frame, fragment1); // 조각 교체
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
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                type1Fragment fragment1 = new type1Fragment();
                transaction.replace(R.id.frame, fragment1); // 조각 교체
                transaction.commit(); // 새로고침 기능
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                type2Fragment fragment2 = new type2Fragment();
                transaction.replace(R.id.frame, fragment2); // 조각 교체
                transaction.commit(); // 새로고침 기능
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                type3Fragment fragment3 = new type3Fragment();
                transaction.replace(R.id.frame, fragment3); // 조각 교체
                transaction.commit(); // 새로고침 기능
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                type4Fragment fragment4 = new type4Fragment();
                transaction.replace(R.id.frame, fragment4); // 조각 교체
                transaction.commit(); // 새로고침 기능
            }
        });

        Toast.makeText(getApplicationContext(), tv_id.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public String getUserID()
    {
        return userID;
    }
}
