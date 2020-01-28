package com.example.toolstore;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //set server URL (connect with PHP file)
    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreRegister.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userPassword, String userName, String city, int ZIPCode, int contact, Response .Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("city", city);
        map.put("ZIPCode", ZIPCode + ""); //fake as string
        map.put("contact", contact + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
