package com.example.toolstore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddTools extends AppCompatActivity {

    private Spinner spinner_category;
    private EditText et_toolName, et_toolMaker, et_toolSize, et_toolAmount, et_toolPrice;
    private Button btn_addToolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_tools);

        spinner_category = findViewById(R.id.spinner_category);
        et_toolName = findViewById(R.id.et_toolName);
        et_toolMaker = findViewById(R.id.et_toolMaker);
        et_toolSize = findViewById(R.id.et_toolSize);
        et_toolAmount = findViewById(R.id.et_toolAmount);
        et_toolPrice = findViewById(R.id.et_toolPrice);
        btn_addToolButton = findViewById(R.id.btn_addToolButton);

        btn_addToolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String category = spinner_category.getSelectedItem().toString();
                String toolName = et_toolName.getText().toString();
                String toolMaker = et_toolMaker.getText().toString();
                String toolSize = et_toolSize.getText().toString();
                int toolAmount = Integer.parseInt(et_toolAmount.getText().toString());
                int toolPrice = Integer.parseInt(et_toolPrice.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Add Tool Response", "Active");
                        try
                        {
                            Log.e("anyText", response);
                            JSONObject jsonObject = new JSONObject(response); // to know success or not
                            boolean success = jsonObject.getBoolean("success");
                            if(success)
                            {
                                Toast.makeText(getApplicationContext(), "Successful Added", Toast.LENGTH_SHORT).show();
                                et_toolName.getText().clear();
                                et_toolMaker.getText().clear();
                                et_toolSize.getText().clear();
                                et_toolAmount.getText().clear();
                                et_toolPrice.getText().clear();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Add Failed", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                };
                // Send request to server with Volley.
                AddToolRequest addToolRequest = new AddToolRequest(category, toolName, toolMaker, toolSize, toolAmount, toolPrice, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddTools.this);
                queue.add(addToolRequest);
            }
        });
    }
}
