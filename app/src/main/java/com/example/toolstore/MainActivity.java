package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id;
    private Button btn_1, btn_2, btn_3, btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = (TextView)findViewById(R.id.tv_id);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        tv_id.setText(userID);

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

        Toast.makeText(getApplicationContext(), "Hello " + tv_id.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
