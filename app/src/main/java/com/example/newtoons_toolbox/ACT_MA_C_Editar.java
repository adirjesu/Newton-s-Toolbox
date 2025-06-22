package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ACT_MA_C_Editar extends AppCompatActivity {
    public static String id_reactivo,materia,tema;
    RequestQueue requestQueue;
    private EditText pregunta,respuesta;
    private Button editar,s1,s2,s3,s4,s5,s6,s7,s8;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ma_ceditar);
        Intent intent= getIntent();
        id_reactivo= intent.getStringExtra("id_reactiv");

        pregunta=findViewById(R.id.etpreguntareac);
        respuesta=findViewById(R.id.etrespuestareac);
        editar=findViewById(R.id.btneditareactivo);

        s1      =findViewById(R.id.btns1r);
        s2      =findViewById(R.id.btns2r);
        s3      =findViewById(R.id.btns3r);
        s4      =findViewById(R.id.btns4r);
        s5      =findViewById(R.id.btns5r);
        s6      =findViewById(R.id.btns6r);
        s7      =findViewById(R.id.btns7r);
        s8      =findViewById(R.id.btns8r);

        buscarreactivos("http://192.168.0.120:8080/newtons/buscar_pregunta.php?id_reactivo="+id_reactivo+"");
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutarServicio("http://192.168.0.120:8080/newtons/editar_reactivo.php");
            }
        });

    }//main

    ////////////////////
    private void buscarreactivos(String URL){
        int b=0;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pregunta.setText(jsonObject.getString("reactivo"));
                        respuesta.setText(jsonObject.getString("respuesta"));
                        materia=jsonObject.getString("materia");
                        tema=jsonObject.getString("tema");
                    } catch (JSONException e) {
                        Toast.makeText(ACT_MA_C_Editar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
                if(materia.equals("1") && tema.equals("1")){

                } else if (materia.equals("1") && tema.equals("2")) {

                }else if (materia.equals("1") && tema.equals("3")) {

                }else if (materia.equals("2") && tema.equals("1")) {

                }else if (materia.equals("2") && tema.equals("2")) {
                    s1.setText("N/m");
                    s2.setText("N");
                    s3.setText("m");
                    s4.setText("J");
                    s5.setText("-");
                    s6.setText("×10^");
                    s7.setText("^");
                    s8.setText("Pa");
                }else if (materia.equals("2") && tema.equals("3")) {

                }else if (materia.equals("3") && tema.equals("1")) {

                }else if (materia.equals("3") && tema.equals("2")) {

                }else if (materia.equals("3") && tema.equals("3")) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_C_Editar.this, "NO SE ENCONTRARON REACTIVOS", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void ejecutarServicio(String URL){


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_MA_C_Editar.this, "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ACT_MA_C_agregar.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_C_Editar.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("id_reactivo",id_reactivo);
                parametros.put("reactivo",pregunta.toString());
                parametros.put("respuesta",respuesta.toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    ////////////////////

    /////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void ocsimf1(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("N/m");
            }else{
                respuesta("N/m");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

        }
    }
    public void ocsimf2(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("N");
            }else{
                respuesta("N");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

        }
    }
    public void ocsimf3(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("m");
            }else{
                respuesta("m");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

        }
    }
    public void ocsimf4(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("J");
            }else{
                respuesta("J");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

        }
    }
    public void ocsimf5(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("-");
            }else{
                respuesta("-");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

        }
    }
    public void ocsimf6(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("×10^");
            }else{
                respuesta("×10^");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

        }
    }
    public void ocsimf7(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("^");
            }else{
                respuesta("^");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

        }
    }
    public void ocsimf8(View view){
        if(materia.equals("1") && tema.equals("1")){

        } else if (materia.equals("1") && tema.equals("2")) {

        }else if (materia.equals("1") && tema.equals("3")) {

        }else if (materia.equals("2") && tema.equals("1")) {

        }else if (materia.equals("2") && tema.equals("2")) {
            if(pregunta.isFocused()) {
                pregunta("Pa");
            }else{
                respuesta("Pa");
            }
        }else if (materia.equals("2") && tema.equals("3")) {

        }else if (materia.equals("3") && tema.equals("1")) {

        }else if (materia.equals("3") && tema.equals("2")) {

        }else if (materia.equals("3") && tema.equals("3")) {

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
}