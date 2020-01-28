package com.example.toolstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private Button btn_register, btn_login;

    private long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = (EditText)findViewById(R.id.et_id);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);

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
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            backBtnTime = curTime;
            Toast.makeText(this, "Press one more times to close.", Toast.LENGTH_SHORT).show();
        }
    }
}