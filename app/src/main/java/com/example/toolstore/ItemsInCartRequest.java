package com.example.toolstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ItemsInCartRequest extends StringRequest {
    //set server URL (connect with PHP file)
    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreListInCart.php";
    private Map<String, String> map;

    public ItemsInCartRequest(String userID, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
