package com.example.toolstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RemoveFromCartRequest extends StringRequest {

    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreDeleteFromCart.php";
    private Map<String, String> map;

    public RemoveFromCartRequest(String ID, String toolName, String toolMaker, String toolSize, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", ID);
        map.put("toolName", toolName);
        map.put("toolMaker", toolMaker);
        map.put("toolSize", toolSize);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}