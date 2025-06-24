package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ACT_F1_Resultados extends AppCompatActivity {

    public static int puntuacion=0,incorrecta=10;
    private TextView resultado;
    public static int tema_elegido=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_act_f1_cresultados);
        resultado=findViewById(R.id.txtResultado);
/*
        Bundle recibeDatos = getIntent().getExtras();
        puntuacion = recibeDatos.getInt("respuestas");
        resultado.setText(String.valueOf(puntuacion) + "/10");
        tema_elegido = recibeDatos.getInt("tema");
        incorrecta=incorrecta-puntuacion;
    */

        puntuacion=7;
        incorrecta=3;
        tema_elegido=2;
        ejecutarServicio("http://192.168.0.247:8080/newtons/up_promedio.php");
        ejecutarServicio2("http://192.168.0.247:8080/newtons/up_porcentaje.php");
    }//main
    private void ejecutarServicio(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_F1_Resultados.this, "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_F1_Resultados.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();

                parametros.put("correctas",String.valueOf(puntuacion));
                parametros.put("incorrectas",String.valueOf(incorrecta));
                parametros.put("materia","2");
                parametros.put("tema",String.valueOf(tema_elegido));
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void ejecutarServicio2(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_F1_Resultados.this, "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_F1_Resultados.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();

                parametros.put("correctas",String.valueOf(puntuacion));
                parametros.put("incorrectas",String.valueOf(incorrecta));
                parametros.put("materia","2");
                parametros.put("tema",String.valueOf(tema_elegido));
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    ////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.regresar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id=item.getItemId();
        if(id==R.id.regresar){
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Bundle enviarDatos = new Bundle();
            enviarDatos.putInt("tema",tema_elegido);
            Intent w =  new Intent(this, ACT_F1_TemaLobby.class);
            w.putExtras(enviarDatos);
            startActivity(w);

        }



        return super.onOptionsItemSelected(item);
    }
}