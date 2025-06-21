package com.example.newtoons_toolbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ACT_LogginAdmin extends AppCompatActivity {
    private EditText usu;
    private EditText contra;
    private Button log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_logginadmin);

        usu  = findViewById(R.id.etusuario);
        contra = findViewById(R.id.etpass);
        log = findViewById(R.id.btnlogin);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("http://192.168.0.122:8080/newtons/login_admin.php");
            }
        });

    }
    private void login (String URL){
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){

                    Intent intent = new Intent(getApplicationContext(),ACT_LobbyAdmin.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ACT_LogginAdmin.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_LogginAdmin.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("usuario",usu.getText().toString());
                parametros.put("pass",contra.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    public void ocLaperdi(View view) {
        Intent x =  new Intent(this, ACT_AD_RecuperarPass.class);
        startActivity(x);
    }
//
@Override
public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.menuestudiantes,menu);
    return true;
}
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.regresar) {
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Intent w = new Intent(this,ACT_Login.class);
            startActivity(w);
        }
        return super.onOptionsItemSelected(item);

    }
}