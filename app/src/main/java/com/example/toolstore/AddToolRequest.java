package com.example.toolstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddToolRequest extends StringRequest {
    //set server URL (connect with PHP file)
    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreAddTool.php";
    private Map<String, String> map;

    public AddToolRequest(String category, String toolName, String toolMaker, String toolSize, int toolAmount, int toolPrice, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("table", category);
        map.put("name", toolName);
        map.put("maker", toolMaker);
        map.put("size", toolSize);
        map.put("amount", toolAmount + "");
        map.put("price", toolPrice + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
