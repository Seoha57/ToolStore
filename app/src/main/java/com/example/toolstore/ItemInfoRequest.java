package com.example.toolstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ItemInfoRequest extends StringRequest {
    //set server URL (connect with PHP file)
    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreItemList.php";
    private Map<String, String> map;

    public ItemInfoRequest(String category, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("table", category);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
