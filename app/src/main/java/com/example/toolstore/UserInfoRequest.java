package com.example.toolstore;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class UserInfoRequest extends StringRequest {
    //set server URL (connect with PHP file)
    final static private String URL = "http://seoha57.dothome.co.kr/ToolStoreUserList.php";

    public UserInfoRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
    }
}