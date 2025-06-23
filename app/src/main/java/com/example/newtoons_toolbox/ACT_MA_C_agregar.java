package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ACT_MA_C_agregar extends AppCompatActivity {
    private EditText pregunta,respuesta,retroalimentacion;
    private Button s1,s2,s3,s4,s5,s6,s7,s8,agregar;
    private RadioButton t1,t2,t3;
    public static String materi,usuario,mat;
    public static int t,m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ma_c_agregar);

        pregunta=findViewById(R.id.etpreguntar);
        respuesta=findViewById(R.id.etrespuestar);
        retroalimentacion=findViewById(R.id.etretro);

        s1      =findViewById(R.id.btns1a);
        s2      =findViewById(R.id.btns2a);
        s3      =findViewById(R.id.btns3a);
        s4      =findViewById(R.id.btns4a);
        s5      =findViewById(R.id.btns5a);
        s6      =findViewById(R.id.btns6a);
        s7      =findViewById(R.id.btns7a);
        s8      =findViewById(R.id.btns8a);

        t1      =findViewById(R.id.rbt1);
        t2      =findViewById(R.id.rbt2);
        t3      =findViewById(R.id.rbt3);

        agregar =findViewById(R.id.btnagregarreac);
        Intent intent= getIntent();
        usuario = intent.getStringExtra("usuario");
        materi  = intent.getStringExtra("materia");
        mat=materi;
        if (materi.equals("Álgebra")) {
            materi = "1";

        }
        if (materi.equals("Física I")) {
            materi = "2";
            t1.setText("Principio de Pascal");
            t2.setText("Ley de Hooke");
            t3.setText("Módulo de Young");
        }
        if (materi.equals("Física II")) {
            materi = "3";
        }
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!t1.isChecked() || !t2.isChecked() ||!t3.isChecked()){
                    Toast.makeText(ACT_MA_C_agregar.this, "Debe elegir un tema", Toast.LENGTH_SHORT).show();
                } else if(pregunta.length()<20) {
                    Toast.makeText(ACT_MA_C_agregar.this, "Debe ingresar una pregunta", Toast.LENGTH_SHORT).show();
                } else if (respuesta.length()<2) {
                    Toast.makeText(ACT_MA_C_agregar.this, "Debe ingresar una respuesta", Toast.LENGTH_SHORT).show();
                }else if(retroalimentacion.length()<2){
                    Toast.makeText(ACT_MA_C_agregar.this, "Debe ingresar una retroalimentación", Toast.LENGTH_SHORT).show();

                } else{
                    buscar_nopregunta("http://192.168.0.120:8080/newtons/buscar_nopreguntas.php?materia="+materi+"&tema="+t+"");

                }



            }
        });
    }//main

    ////////////////////////buscar los datos
    private void buscar_nopregunta(String URL) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Acceder al número total de usuarios
                    String totalpreguntas = response.getString("total");
                    Toast.makeText(ACT_MA_C_agregar.this, "Preguntas Guardadas: " + totalpreguntas, Toast.LENGTH_SHORT).show();
                    int preguntasg = Integer.parseInt(totalpreguntas); // Conversión a int
                    if(preguntasg<=20){
                        ejecutarServicio("http://192.168.0.120:8080/newtons/nueva_pregunta.php");
                    }else{
                        Toast.makeText(ACT_MA_C_agregar.this, "Ha alcanzado el límite de preguntas guardadas (20)", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(ACT_MA_C_agregar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_C_agregar.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
    private void ejecutarServicio(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_MA_C_agregar.this, "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
                pregunta.setText("");
                respuesta.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_C_agregar.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();

                parametros.put("materia",String.valueOf(materi));
                parametros.put("tema", String.valueOf(t));
                parametros.put("reactivo",pregunta.getText().toString());
                parametros.put("respuesta",respuesta.getText().toString());
                parametros.put("retroalimentacion",retroalimentacion.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    /////////////////////////////////////
    public void ocTemareac(View view){
        if (t1.isChecked()){
            t=1;
            pregunta.setText("");
            respuesta.setText("");

        }
        if (t2.isChecked()){
            t=2;
            pregunta.setText("");
            respuesta.setText("");
            s1.setText("N/m");
            s2.setText("N");
            s3.setText("m");
            s4.setText("J");
            s5.setText("-");
            s6.setText("×10^");
            s7.setText("^");
            s8.setText("Pa");
        }
        if (t3.isChecked()){
            t=3;
            pregunta.setText("");
            respuesta.setText("");

        }
    }
    ///////////////////////buscarlos datos;;;;;
    /////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void ocsimf1(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()) {
                                pregunta("N/m");
                            }else{
                                respuesta("N/m");
                            }
                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public void ocsimf2(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()) {
                                pregunta("N");

                            }else{
                                respuesta("N");
                            }
                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public void ocsimf3(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()) {
                                pregunta("m");

                            }else{
                                respuesta("m");
                            }
                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public void ocsimf4(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()) {
                                pregunta("J");
                            }else{
                                respuesta("J");
                            }
                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public void ocsimf5(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()) {
                                pregunta("-");
                            }else{
                                respuesta("-");
                            }
                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public void ocsimf6(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()) {
                                pregunta("×10^");

                            }else{
                                respuesta("×10^");
                            }
                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public void ocsimf7(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()) {
                                pregunta("^");

                            }else{
                                respuesta("^");
                            }
                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public void ocsimf8(View view){
        if(materi=="1" && t1.isChecked()){

            } else if (materi=="1" && t2.isChecked()) {

                }else if (materi=="1" && t3.isChecked()) {

                    }else if (materi=="2" && t1.isChecked()) {

                        }else if (materi=="2" && t2.isChecked()) {
                            if(pregunta.isFocused()){
                                pregunta("Pa");
                            }else{
                                respuesta("Pa");
                            }

                            }else if (materi=="2" && t3.isChecked()) {

                                }else if (materi=="3" && t1.isChecked()) {

                                    }else if (materi=="3" && t2.isChecked()) {

                                        }else if (materi=="3" && t3.isChecked()) {

                                            }
    }
    public String pregunta(String unidad) {
        String resbefore;
        if (pregunta == null) {
            return ""; // O lanzar una excepción si es crítico
        }
        else{
             resbefore=pregunta.getText().toString();
        }
        String nuevoTexto = resbefore + unidad;
        pregunta.setText(nuevoTexto);
        pregunta.setSelection(nuevoTexto.length());

        return nuevoTexto; // Retorna el texto actualizado
    }


    public String respuesta(String unidad) {
        String resbefore;
        if (respuesta == null) {
            return ""; // O lanzar una excepción si es crítico
        }
        else{
            resbefore=respuesta.getText().toString();
        }
        String nuevoTexto = resbefore + unidad;
        respuesta.setText(nuevoTexto);
        respuesta.setSelection(nuevoTexto.length());

        return nuevoTexto; // Retorna el texto actualizado
    }

/////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
            Intent w = new Intent(this,ACT_LobbyMaestro.class);
            startActivity(w);
        }
        return super.onOptionsItemSelected(item);

    }
}