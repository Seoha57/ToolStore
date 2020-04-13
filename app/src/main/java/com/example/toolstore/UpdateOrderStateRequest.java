package com.example.toolstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateOrderStateRequest extends StringRequest {
    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreUpdateOrderState.php";
    private Map<String, String> map;

    public UpdateOrderStateRequest(String updateState, String userID, String orderName, String orderMaker, String orderSize, int orderAmount, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("state", updateState);
        map.put("userID", userID);
        map.put("name", orderName);
        map.put("maker", orderMaker);
        map.put("size", orderSize);
        map.put("amount", orderAmount + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
