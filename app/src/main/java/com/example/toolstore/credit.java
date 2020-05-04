package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class credit extends AppCompatActivity {
    private ImageButton imageBtn_backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        imageBtn_backToMain = findViewById(R.id.imageBtn_backToMain);
        imageBtn_backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(credit.this, LoginActivity.class);
                startActivity(main);
            }
        });
    }
}
