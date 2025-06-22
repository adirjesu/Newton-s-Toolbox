package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ACT_AD_CRUD_maestro extends AppCompatActivity {
    EditText nombre,ap_p,ap_m,correo,usuario,pass;
    Button agregar, buscar, actualizar, eliminar,limpiar;
    RadioButton rb1,rb2,rb3;
    RequestQueue requestQueue;
    public static String materia;
    public static boolean usuarioexistente;
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
        agregar=findViewById(R.id.btnagregar);
        buscar=findViewById(R.id.btnbuscar);
        actualizar=findViewById(R.id.btnactualizar);
        eliminar=findViewById(R.id.btneliminar);
        limpiar=findViewById(R.id.btnlipiar);

        rb1=findViewById(R.id.rbalgebra);
        rb2=findViewById(R.id.rbfisica1);
        rb3=findViewById(R.id.rbfisica2);


        nombre.setFilters(new InputFilter[]{new LetterInputFilter()});
        ap_m.setFilters(new InputFilter[]{new LetterInputFilter()});
        ap_p.setFilters(new InputFilter[]{new LetterInputFilter()});




        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarformulario();
            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuariorepetido("http://192.168.0.120:8080/newtons/buscar_maestro.php?usuario="+usuario.getText()+"");
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!usuario.getText().toString().isEmpty()){
                    buscarmaestro("http://192.168.0.120:8080/newtons/buscar_maestro.php?usuario="+usuario.getText()+"");
                    agregar.setVisibility(View.GONE);
                    agregar.setEnabled(false);
                    actualizar.setVisibility(View.VISIBLE);
                    eliminar.setVisibility(View.VISIBLE);
                    actualizar.setEnabled(true);
                    eliminar.setEnabled(true);
                }
                else{
                    Toast.makeText(ACT_AD_CRUD_maestro.this, "Necesita ingresar un usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ejecutarServicio("http://192.168.0.120:8080/newtons/editar_maestro.php");

            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarprofesor("http://192.168.0.120:8080/newtons/eliminar_maestro.php");
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar();
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar();
            }
        });
    }//main

    private void ejecutarServicio(String URL){
        if(rb1.isChecked()){
            materia="algebra";
        }
            if(rb2.isChecked()){
                materia="fisica1";
            }
                if(rb3.isChecked()){
                    materia="fisica2";
                }
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_AD_CRUD_maestro.this, "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
                agregar.setEnabled(false);
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

                parametros.put("nombre",nombre.getText().toString());
                parametros.put("ap_p",ap_p.getText().toString());
                parametros.put("ap_m",ap_m.getText().toString());
                parametros.put("correo",correo.getText().toString());
                parametros.put("usuario",usuario.getText().toString());
                parametros.put("pass",pass.getText().toString());
                parametros.put("materia",materia);
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
                        materia=(jsonObject.getString("materia"));
                        if(materia.equals("algebra")){
                            rb1.setChecked(true);
                        }
                        if(materia.equals("fisica1")){
                            rb2.setChecked(true);
                        }
                        if(materia.equals("fisica2")){
                            rb3.setChecked(true);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(ACT_AD_CRUD_maestro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_AD_CRUD_maestro.this, "USUARIO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                actualizar.setVisibility(View.GONE);
                eliminar.setVisibility(View.GONE);
            }
        }
        );
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
/////////////////////
private void usuariorepetido(String URL){
    JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            JSONObject jsonObject = null;
            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = response.getJSONObject(i);
                    String us;
                    us=jsonObject.getString("usuario");
                    if(us.equals(usuario.getText().toString())){
                        Toast.makeText(ACT_AD_CRUD_maestro.this, "USUARIO YA EXISTENTE", Toast.LENGTH_SHORT).show();
                        usuario.setText("");
                    }else{
                        ejecutarServicio("http://192.168.0.170:8080/newtons/nuevo_maestro.php");
                    }
                } catch (JSONException e) {
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
    ////////////////////////
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
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        agregar.setText("");
        actualizar.setVisibility(View.GONE);
        eliminar.setVisibility(View.GONE);
        agregar.setVisibility(View.GONE);
    }
    private void verificar(){
        if(!usuario.getText().toString().isEmpty()){
            if(!pass.getText().toString().isEmpty()){
                if(!nombre.getText().toString().isEmpty()){
                    if(nombre.length()>3) {
                        if(!ap_p.getText().toString().isEmpty()){
                            if(ap_p.length()>3) {
                                if (!ap_m.getText().toString().isEmpty()) {
                                    if(ap_m.length()>3) {
                                        if (!correo.getText().toString().isEmpty()) {
                                            agregar.setEnabled(true);
                                            agregar.setVisibility(View.VISIBLE);
                                        } else {
                                            Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa un correo válido", Toast.LENGTH_SHORT).show();
                                        }//else correo
                                    }else{
                                        Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa un apellido válido", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa el apellido materno", Toast.LENGTH_SHORT).show();
                                }//else ap_m
                            }else{
                                Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa un apellido válido", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa el apellido paterno", Toast.LENGTH_SHORT).show();
                        }//else ap_p
                    }else{
                        Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa un nombre válido", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa el nombre", Toast.LENGTH_SHORT).show();
                }//nombre
            }else{
                Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa una contraseña 3-8 car.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(ACT_AD_CRUD_maestro.this, "Ingresa un usuario", Toast.LENGTH_SHORT).show();
        }
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
    public class LetterInputFilter implements InputFilter {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            // Validar cada caracter ingresado
            for (int i = start; i < end; i++) {
                char c = source.charAt(i);
                // Si NO es una letra (mayúscula o minúscula), rechazar
                if (!Character.isLetter(c)) {
                    return "";
                }
            }
            return null; // Permitir entrada válida (letras)
        }
    }
}