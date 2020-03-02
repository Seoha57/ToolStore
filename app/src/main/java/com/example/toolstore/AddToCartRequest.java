package com.example.toolstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddToCartRequest extends StringRequest {

    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreAddToCart.php";
    private Map<String, String> map;

    public AddToCartRequest(String ID, String toolName, String toolMaker, String toolSize, int toolAmount, int toolPrice, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("table", ID);
        map.put("toolName", toolName);
        map.put("toolMaker", toolMaker);
        map.put("toolSize", toolSize);
        map.put("amount", toolAmount + "");
        map.put("price", toolPrice + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
