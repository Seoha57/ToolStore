package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    TextView tv_admin;
    Button btn_userInfo, btn_addTools, btn_checkOrders;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tv_admin = findViewById(R.id.tv_admin);

        btn_userInfo = findViewById(R.id.btn_userInfo);
        btn_addTools = findViewById(R.id.btn_addTools);
        btn_checkOrders = findViewById(R.id.btn_checkOrders);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        tv_admin.setText("Hi, " + userID);

        btn_userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(AdminActivity.this, UserInfoActivity.class);
                startActivity(change);
            }
        });

        btn_addTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(AdminActivity.this, AddTools.class);
                startActivity(change);
            }
        });

        btn_checkOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(AdminActivity.this, AdminCheckoutList.class);
                change.putExtra("userID", userID);
                startActivity(change);
            }
        });

        Toast.makeText(getApplicationContext(), "Hello " + tv_admin.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
