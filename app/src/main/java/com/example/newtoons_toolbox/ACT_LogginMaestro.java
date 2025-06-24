package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ACT_LogginMaestro extends AppCompatActivity {
    private EditText usu;
    private EditText contra;
    private Button log;
    public static String materi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_loggin_maestro);

        usu  = findViewById(R.id.etusuario2);
        contra = findViewById(R.id.etpass2);
        log = findViewById(R.id.btnlogin2);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("http://192.168.0.247:8080/newtons/login_maestro.php");
            }
        });

    }//main
    private void login(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    buscarmaestro("http://192.168.0.247:8080/newtons/buscar_maestro.php?usuario=" + usu.getText() + "");
                } else {
                    Toast.makeText(ACT_LogginMaestro.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_LogginMaestro.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("usuario", usu.getText().toString());
                parametros.put("pass", contra.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void buscarmaestro(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        materi = jsonObject.getString("materia");
                        if (materi.equals("algebra")) {
                            materi = "Álgebra";
                        }
                        if (materi.equals("fisica1")) {
                            materi = "Física I";
                        }
                        if (materi.equals("fisica2")) {
                            materi = "Física II";
                        }
                    } catch (JSONException e) {
                        Toast.makeText(ACT_LogginMaestro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                // Iniciar la nueva Activity después de obtener el valor de 'materi'
                Intent intent = new Intent(getApplicationContext(), ACT_LobbyMaestro.class);
                intent.putExtra("usuario", usu.getText().toString());
                intent.putExtra("materia", materi);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_LogginMaestro.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    ////////////////
    public void ocLaperdimaestro(View view) {
        Intent x =  new Intent(this, ACT_MA_RecuperarPass.class);
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