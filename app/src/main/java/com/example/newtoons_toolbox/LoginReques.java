package com.example.newtoons_toolbox;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class LoginReques extends StringRequest{
    private static final String LOGIN_REQUEST_URL = "http://192.168.3.67/log.php";
    private Map<String, String> params;
    public LoginReques(String usuario, String pass, Response.Listener<String> listener) {
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
