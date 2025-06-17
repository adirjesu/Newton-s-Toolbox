package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ACT_AD_CRUD_maestro extends AppCompatActivity {
    EditText nombre,ap_p,ap_m,correo,usuario,pass,materia;
    Button agregar, buscar, actualizar, eliminar;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ad_crud_maestro);

        nombre= findViewById(R.id.etnombre);
        ap_p= findViewById(R.id.etap_p);
        ap_m= findViewById(R.id.etap_m);
        correo= findViewById(R.id.etcorreo);
        usuario= findViewById(R.id.etusuaiomaes);
        pass= findViewById(R.id.etcontramaes);
        materia= findViewById(R.id.etmateria);
        agregar=findViewById(R.id.btnagregar);
        buscar=findViewById(R.id.btnbuscar);
        actualizar=findViewById(R.id.btnactualizar);
        eliminar=findViewById(R.id.btneliminar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.3.67:8080/newtons/nuevo_maestro.php");
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarmaestro("http://192.168.3.67:8080/newtons/buscar_maestro.php?usuario="+usuario.getText()+"");
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.3.67:8080/newtons/editar_maestro.php");
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarprofesor("http://192.168.3.67:8080/newtons/eliminar_maestro.php");
            }
        });
    }//main

    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_AD_CRUD_maestro.this, "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
                agregar.setEnabled(false);
                nombre.setText("");
                ap_p.setText("");
                ap_m.setText("");
                correo.setText("");
                usuario.setText("");
                pass.setText("");
                materia.setText("");
                agregar.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_AD_CRUD_maestro.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("nombre",nombre.getText().toString());
                parametros.put("ap_p",ap_p.getText().toString());
                parametros.put("ap_m",ap_m.getText().toString());
                parametros.put("correo",correo.getText().toString());
                parametros.put("usuario",usuario.getText().toString());
                parametros.put("pass",pass.getText().toString());
                parametros.put("materia",materia.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void buscarmaestro(String URL){
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombre.setText(jsonObject.getString("nombres"));
                        ap_p.setText(jsonObject.getString("ap_p"));
                        ap_m.setText(jsonObject.getString("ap_m"));
                        correo.setText(jsonObject.getString("correo"));
                        pass.setText(jsonObject.getString("pass"));
                        materia.setText(jsonObject.getString("materia"));

                    } catch (JSONException e) {
                        Toast.makeText(ACT_AD_CRUD_maestro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_AD_CRUD_maestro.this, "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void eliminarprofesor(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_AD_CRUD_maestro.this, "EL DOCENTE SE ELIMINÓ CON ÉXITO", Toast.LENGTH_SHORT).show();
                limpiarformulario();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_AD_CRUD_maestro.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("usuario",usuario.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void limpiarformulario(){
        nombre.setText("");
        ap_p.setText("");
        ap_m.setText("");
        correo.setText("");
        usuario.setText("");
        pass.setText("");
        materia.setText("");
        agregar.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.regresar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.regresar) {
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Intent w = new Intent(this,ACT_LobbyAdmin.class);
            startActivity(w);
        }
        return super.onOptionsItemSelected(item);

    }
}
