package com.example.newtoons_toolbox;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.3.67/login_admins.php";
    private Map<String, String> params;
    public LoginRequest(String usuario, String pass, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("usuario",usuario);
        params.put("pass",pass);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}














