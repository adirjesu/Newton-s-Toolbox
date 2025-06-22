package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class ACT_MA_Crud extends AppCompatActivity {
    private RadioButton[] radioButtons; // Array de RadioButtons
    private TextView[] preguntas = new TextView[15]; // Cambia el tamaño según sea necesario
    private TextView[] respuestas = new TextView[15]; // Cambia el tamaño según sea necesario

    private RadioButton t1,t2,t3;
    private Button eliminar,editar;
    public static String materi,usuario,mat,id_reac;
    RequestQueue requestQueue;
    public static int t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ma_crud);

        eliminar=findViewById(R.id.btneliminareac);
        editar=findViewById(R.id.btneditareac);
        t1=findViewById(R.id.rbtc1);
        t2=findViewById(R.id.rbtc2);
        t3=findViewById(R.id.rbtc3);
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
        //inicializar el array de Pregutas;;;
        preguntas = new TextView[]{
                findViewById(R.id.txtpr1),
                findViewById(R.id.txtpr2),
                findViewById(R.id.txtpr3),
                findViewById(R.id.txtpr4),
                findViewById(R.id.txtpr5),
                findViewById(R.id.txtpr6),
                findViewById(R.id.txtpr7),
                findViewById(R.id.txtpr8),
                findViewById(R.id.txtpr9),
                findViewById(R.id.txtpr10),
                findViewById(R.id.txtpr11),
                findViewById(R.id.txtpr12),
                findViewById(R.id.txtpr13),
                findViewById(R.id.txtpr14),
                findViewById(R.id.txtpr15)
        };
        //inicializar el array de Pregutas;;;
        respuestas = new TextView[]{
                findViewById(R.id.txtre1),
                findViewById(R.id.txtre2),
                findViewById(R.id.txtre3),
                findViewById(R.id.txtre4),
                findViewById(R.id.txtre5),
                findViewById(R.id.txtre6),
                findViewById(R.id.txtre7),
                findViewById(R.id.txtre8),
                findViewById(R.id.txtre9),
                findViewById(R.id.txtre10),
                findViewById(R.id.txtre11),
                findViewById(R.id.txtre12),
                findViewById(R.id.txtre13),
                findViewById(R.id.txtre14),
                findViewById(R.id.txtre15)

        };
        // Inicializar el array de RadioButtons
        radioButtons = new RadioButton[] {
                findViewById(R.id.rbp1),
                findViewById(R.id.rbp2),
                findViewById(R.id.rbp3),
                findViewById(R.id.rbp4),
                findViewById(R.id.rbp5),
                findViewById(R.id.rbp6),
                findViewById(R.id.rbp7),
                findViewById(R.id.rbp8),
                findViewById(R.id.rbp9),
                findViewById(R.id.rbp10),
                findViewById(R.id.rbp11),
                findViewById(R.id.rbp12),
                findViewById(R.id.rbp13),
                findViewById(R.id.rbp14),
                findViewById(R.id.rbp15)
        };
        // Establecer un listener para cada RadioButton
        for (RadioButton radioButton : radioButtons) {
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRadioButtonClicked((RadioButton) v);
                }
            });
        }


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarreactivo("http://192.168.0.120:8080/newtons/eliminar_reactivo.php");
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ACT_MA_C_Editar.class);
                intent.putExtra("id_reactiv", id_reac);
                startActivity(intent);
            }
        });
        ocultarTODO();
    }///main
//////////////////////
private void buscarreactivos(String URL){
        int b=0;
    JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            JSONObject jsonObject = null;
            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = response.getJSONObject(i);
                    radioButtons[i].setText(jsonObject.getString("id_reactivo"));
                    preguntas[i].setText(jsonObject.getString("reactivo"));
                    respuestas[i].setText(jsonObject.getString("reactivo"));
                    radioButtons[i].setVisibility(View.VISIBLE);
                    preguntas[i].setVisibility(View.VISIBLE);
                    respuestas[i].setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    Toast.makeText(ACT_MA_Crud.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }//catch
            }//for
            for(int a = response.length(); a<15; a++){
                radioButtons[a].setVisibility(View.GONE);
                preguntas[a].setVisibility(View.GONE);
                respuestas[a].setVisibility(View.GONE);
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(ACT_MA_Crud.this, "NO SE ENCONTRARON REACTIVOS", Toast.LENGTH_SHORT).show();
            ocultarTODO();

        }
    }
    );
    requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(jsonArrayRequest);
}

    private void borrarreactivo(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_MA_Crud.this, "EL REACTIVO SE ELIMINÓ CON ÉXITO", Toast.LENGTH_SHORT).show();
                ocultarTODO();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_Crud.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("id_reactivo",id_reac);
                return parametros;
            }
        };
         requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    ////////////////////////

    public void ocTemareact(View view){
        if (t1.isChecked()){
            t=1;
            buscarreactivos("http://192.168.0.120:8080/newtons/buscar_preguntas.php?materia="+materi+"&tema="+t+"");

        }
        if (t2.isChecked()){
            t=2;
            buscarreactivos("http://192.168.0.120:8080/newtons/buscar_preguntas.php?materia="+materi+"&tema="+t+"");

        }
        if (t3.isChecked()){
            t=3;
            buscarreactivos("http://192.168.0.120:8080/newtons/buscar_preguntas.php?materia="+materi+"&tema="+t+"");

        }
    }


    private void onRadioButtonClicked(RadioButton selectedButton) {
        // Mostrar el texto del RadioButton seleccionado
        id_reac = selectedButton.getText().toString();
        // Desseleccionar  otros RadioButtons
        for (RadioButton rb : radioButtons) {
            if (rb != selectedButton) {
                rb.setChecked(false);
            }
        }

        // Mostrar botones de editar y eliminar
        editar.setVisibility(View.VISIBLE);
        eliminar.setVisibility(View.VISIBLE);
    }
    private void ocultarTODO(){
        for(int a=0;a<15;a++){
            radioButtons[a].setVisibility(View.GONE);
            preguntas[a].setVisibility(View.GONE);
            respuestas[a].setVisibility(View.GONE);
        }
        eliminar.setVisibility(View.GONE);
        editar.setVisibility(View.GONE);
        t1.setChecked(false);
        t2.setChecked(false);
        t3.setChecked(false);
    }

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