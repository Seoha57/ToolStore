package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id, et_password;
    private Button btn_register, btn_login, btn_credit;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        btn_credit = findViewById(R.id.btn_credit);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get input value
                String userID = et_id.getText().toString();
                String userPassword = et_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response); // to know success or not
                            boolean success = jsonObject.getBoolean("success");
                            if(success)
                            {
                                String userID = jsonObject.getString("userID");
                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                                switch (userID)
                                {
                                    case "admin":
                                        Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                                        adminIntent.putExtra("userID", userID);

                                        et_id.getText().clear();
                                        et_password.getText().clear();

                                        startActivity(adminIntent);
                                        break;

                                    default:
                                        Intent customerIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        customerIntent.putExtra("userID", userID);

                                        et_id.getText().clear();
                                        et_password.getText().clear();

                                        startActivity(customerIntent);
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        btn_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditScene = new Intent(LoginActivity.this, credit.class);
                startActivity(creditScene);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}