package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ACT_MA_Reportes extends AppCompatActivity {
    public static String materi,usuario,mat;
    private TextView uno,dos,tres;
    private RadioButton t1,t2,t3,r1,r2,r3;
    private Button gr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ma_reportes);
        uno=findViewById(R.id.txtr1);
        dos=findViewById(R.id.txtr2);
        tres=findViewById(R.id.txtr3);

        t1      =findViewById(R.id.rbrt1);
        t2      =findViewById(R.id.rbrt2);
        t3      =findViewById(R.id.rbrt3);
        r1      =findViewById(R.id.rbrr1);
        r2      =findViewById(R.id.rbrr2);
        r3      =findViewById(R.id.rbrr3);
        gr      =findViewById(R.id.btnreporte);
        Intent intent= getIntent();
        usuario = intent.getStringExtra("usuario");
        materi  = intent.getStringExtra("materia");
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
        gr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(r1.isChecked()){
                    if(t1.isChecked()){
                        buscarpromedios("http://192.168.0.247:8080/newtons/buscar_promedio.php?materia="+materi+"&tema=1");
                    } else if (t2.isChecked()) {
                        buscarveces("http://192.168.0.247:8080/newtons/buscar_veces.php?materia="+materi+"&tema=2");

                    } else if (t3.isChecked()) {
                        buscarporcentaje("http://192.168.0.247:8080/newtons/buscar_porcentaje.php?materia="+materi+"&tema=3");

                    }else{

                    }
                } else if (r2.isChecked()) {
                    if(t1.isChecked()){
                        buscarpromedios("http://192.168.0.247:8080/newtons/buscar_promedio.php?materia="+materi+"&tema=1");
                    } else if (t2.isChecked()) {
                        buscarveces("http://192.168.0.247:8080/newtons/buscar_veces.php?materia="+materi+"&tema=2");

                    } else if (t3.isChecked()) {
                        buscarporcentaje("http://192.168.0.247:8080/newtons/buscar_porcentaje.php?materia="+materi+"&tema=3");

                    }else{

                    }
                } else if (r3.isChecked()) {
                    if(t1.isChecked()){
                        buscarpromedios("http://192.168.0.247:8080/newtons/buscar_promedio.php?materia="+materi+"&tema=1");
                    } else if (t2.isChecked()) {
                        buscarpromedios("http://192.168.0.247:8080/newtons/buscar_veceso.php?materia="+materi+"&tema=2");

                    } else if (t3.isChecked()) {
                        buscarporcentaje("http://192.168.0.247:8080/newtons/buscar_porcentaje.php?materia="+materi+"&tema=3");

                    }else{

                    }
                }else{
                    Toast.makeText(ACT_MA_Reportes.this, "Necesita ingresar un tema", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//main
    private void buscarpromedios(String URL){
        int b=0;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                String u = null,d = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        u=jsonObject.getString("correctas");
                        d=jsonObject.getString("incorrectas");
                        uno.setText("Correctas totales:"+jsonObject.getString("correctas"));
                        dos.setText("Incorrectas totales:"+jsonObject.getString("incorrectas"));

                    } catch (JSONException e) {
                        Toast.makeText(ACT_MA_Reportes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
                int Uno,Dos,pro,res;
                Uno=Integer.parseInt(u);
                Dos=Integer.parseInt(d);
                res=Uno+Dos;
                pro=res/2;
                tres.setText("Promedio: "+String.valueOf(pro));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_Reportes.this, "NO SE ENCONTRARON REACTIVOS", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    private void buscarveces(String URL){
        int b=0;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                String u = null,d = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        uno.setText("Numero de veces usada:"+jsonObject.getString("vecess"));

                    } catch (JSONException e) {
                        Toast.makeText(ACT_MA_Reportes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_Reportes.this, "NO SE ENCONTRARON REACTIVOS", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void buscarporcentaje(String URL){
        int b=0;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                String u = null,d = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        u=jsonObject.getString("aprueban");
                        d=jsonObject.getString("reprueban");
                        uno.setText("Aprobados:"+jsonObject.getString("aprobados"));
                        dos.setText("Reprobados:"+jsonObject.getString("reprobados"));

                    } catch (JSONException e) {
                        Toast.makeText(ACT_MA_Reportes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
                int Uno,Dos,por1,por2,res;
                Uno=Integer.parseInt(u);
                Dos=Integer.parseInt(d);
                res=Uno+Dos;
                por1=(Uno/res)*100;
                por2=(Dos/res)*100;
                tres.setText("Aprobados: "+String.valueOf(por1)+"\n"+"Reprobados: "+String.valueOf(por2));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_MA_Reportes.this, "NO SE ENCONTRARON REACTIVOS", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
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
            Intent w = new Intent(this,ACT_LobbyMaestro.class);
            w.putExtra("usuario",usuario);
            w.putExtra("materia",materi);
            startActivity(w);
        }
        return super.onOptionsItemSelected(item);

    }
}