package com.example.newtoons_toolbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class ACT_AL_Cuestionario extends AppCompatActivity {

    public static int [] praleatorio= new int[10];
    private TextView nopreg;
    private TextView preg;
    private EditText res;
    private TextView sino;
    private Button calificar;
    private Button continuar;

    private Button s1;
    private Button s2;
    private Button s3;
    private Button s4;
    private Button s5;
    private Button s6;
    private Button s7;
    private Button s8;
    private Button s9;
    private TextView retro;
    public static  String[][] matriz = new String[10][3];
    public static int contador=0;
    public static int contadorotro=1;
    public static int puntuacion=0;
    public static int tema_elegido;
    public static String[] preguntas;
    public static String[] respuestas;
    public static String[] retroalimentaciones;
    public static String[] preguntassql= new String[15];
    public static String[] respuestassql= new String[15];
    public static String[] retroalimentacionessql= new String[15];
    public static String [] Preguntas;
    public static String [] Respuestas;
    public static String [] Retroalimentaciones;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_al_cuestionario);

        nopreg    =findViewById(R.id.txtNopreguntaAl);
        preg      =findViewById(R.id.txtPreguntaAl);
        res       =findViewById(R.id.etrespuestaAl);
        sino      =findViewById(R.id.txtsinoAl);
        retro     =findViewById(R.id.txtRetroAl);
        calificar =findViewById(R.id.btnCalificarAl);
        continuar =findViewById(R.id.btnContinuarAl);

        s1 = findViewById(R.id.btns1Al);
        s2 = findViewById(R.id.btns2Al);
        s3 = findViewById(R.id.btns3Al);
        s4 = findViewById(R.id.btns4Al);
        s5 = findViewById(R.id.btns5Al);
        s6 = findViewById(R.id.btns6Al);
        s7 = findViewById(R.id.btns7Al);
        s8 = findViewById(R.id.btns8Al);
        s9 = findViewById(R.id.btns9Al);


        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");

        if(tema_elegido==1){
            preguntas = getResources().getStringArray(R.array.al_poli_p);
            respuestas = getResources().getStringArray(R.array.al_poli_r);
            retroalimentaciones = getResources().getStringArray(R.array.al_poli_retro);
            buscarreactivos("http://192.168.0.250:8080/newtons/buscar_preguntas.php?materia=1&tema=1");

            s1.setText("+");
            s2.setText("-");
            s3.setText("(");
            s4.setText(")");
            s5.setText("÷");
            s6.setText("^");
            s7.setText("√");
            s8.setText("=");

            retroalimentaciones = getResources().getStringArray(R.array.al_poli_retro);
            s1.setText("x");
            s2.setText("+");
            s3.setText("-");
            s4.setText("(");
            s5.setText(")");
            s6.setText("÷");
            s7.setText("^");
            s8.setText("√");
            s9.setText("=");

        } else if (tema_elegido==2) {
            preguntas = getResources().getStringArray(R.array.al_ecua_p);
            respuestas = getResources().getStringArray(R.array.al_ecua_r);
            retroalimentaciones = getResources().getStringArray(R.array.al_ecua_retro);
            buscarreactivos("http://192.168.0.250:8080/newtons/buscar_preguntas.php?materia=1&tema=2");

            s1.setText("+");
            s2.setText("-");
            s3.setText("(");
            s4.setText(")");
            s5.setText("÷");
            s6.setText("^");
            s7.setText("√");
            s8.setText("=");

        } else if (tema_elegido==3) {
            preguntas = getResources().getStringArray(R.array.al_fact_p);
            respuestas = getResources().getStringArray(R.array.al_fact_r);
            retroalimentaciones = getResources().getStringArray(R.array.al_fact_retro);
            buscarreactivos("http://192.168.0.250:8080/newtons/buscar_preguntas.php?materia=1&tema=3");

            s1.setText("+");
            s2.setText("-");
            s3.setText("(");
            s4.setText(")");
            s5.setText("÷");
            s6.setText("^");
            s7.setText("√");
            s8.setText("=");
        }


// Inicializa la matriz con el tamaño adecuado




        mensaje(ACT_AL_Cuestionario.this);
        nopreg.setText("Pregunta : " + (contadorotro));

    }

    private void buscarreactivos(String URL){
        int b=0;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        //radioButtons[i].setText(jsonObject.getString("id_reactivo"));
                        preguntassql[i]=jsonObject.getString("reactivo");
                        respuestassql[i]=jsonObject.getString("respuesta");
                        retroalimentacionessql[i]=jsonObject.getString("retroalimentacion");

                    } catch (JSONException e) {
                        Toast.makeText(ACT_AL_Cuestionario.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
                Preguntas = new String[preguntas.length + preguntassql.length];
                System.arraycopy(preguntas, 0, Preguntas, 0, preguntas.length);
                System.arraycopy(preguntassql, 0, Preguntas, preguntas.length, preguntassql.length);


                Respuestas = new String[respuestas.length + respuestassql.length];
                System.arraycopy(respuestas, 0, Respuestas, 0, respuestas.length);
                System.arraycopy(respuestassql, 0, Respuestas, respuestas.length, respuestassql.length);


                Retroalimentaciones = new String[retroalimentaciones.length + retroalimentacionessql.length];
                System.arraycopy(retroalimentaciones, 0, Retroalimentaciones, 0, retroalimentaciones.length);
                System.arraycopy(retroalimentacionessql, 0, Retroalimentaciones, retroalimentaciones.length, retroalimentacionessql.length);
// Llenar la matriz con preguntas, respuestas y retroalimentaciones
                for (int i = 0; i < preguntas.length; i++) {
                    matriz[i][0] = Preguntas[i]; // Pregunta
                    matriz[i][1] = Respuestas[i]; // Respuesta
                    matriz[i][2] = Retroalimentaciones[i]; // Retroalimentación
                }

                for(int i=0;i<praleatorio.length;i++){
                    praleatorio[i]=i;
                }
                shuffleArray(praleatorio);
                preg.setText(matriz[praleatorio[0]][0]);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_AL_Cuestionario.this, "NO SE ENCONTRARON REACTIVOS", Toast.LENGTH_SHORT).show();

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    public void ocCalificar(View view){

        calificar.setEnabled(false);
        calificar.setVisibility(View.INVISIBLE);
        continuar.setEnabled(true);
        continuar.setVisibility(View.VISIBLE);

        retro.setText(String.valueOf(matriz[praleatorio[contador]][2]));

        String respuesta = String.valueOf(res.getText());
        if(respuesta.equals(String.valueOf(matriz[praleatorio[contador]][1]))){
            sino.setText("corecto");
            sino.setTextColor(Color.rgb(76,175,80));
            continuar.setBackgroundColor(Color.rgb(76,175,80));
            puntuacion++;
        } else {
            sino.setText("Incorrecto");
            sino.setTextColor(Color.rgb(255,0,0));
            continuar.setBackgroundColor(Color.rgb(255,0,0));
        }
        contador++;
        contadorotro++;
    }


    public void ocContinuar(View view){
        if(contador<=9){
            nopreg.setText("Pregunta : " + (contadorotro));
            //quitamos el texto
            res.setText("");
            retro.setText("");
            sino.setText("");
            //activamos y hacemos visible el boton calificar
            calificar.setEnabled(true);
            calificar.setVisibility(View.VISIBLE);
            //desactivamos y ocultamos el botón continuar
            continuar.setEnabled(false);
            continuar.setVisibility(View.INVISIBLE);
            preg.setText(matriz[praleatorio[contador]][0]);

        }
        else{
            Bundle enviarDatos = new Bundle();
            enviarDatos.putInt("respuestas",puntuacion);
            Intent x =  new Intent(this, ACT_AL_Resultados.class);
            x.putExtras(enviarDatos);
            startActivity(x);
        }
    }


    public void mensaje (Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Antes de comenzar...");
        builder.setMessage("recuerda que las unidades deben corresponer al resultado final");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }



    /*//////////////EMPIEZAN LAS VARIABLES\\\\\\\\\\\\\\*/


/////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void Sim1(){
        respuesta("+");
    }
    public void Sim2(){
        respuesta("-");
    }
    public void Sim3(){
        respuesta("(");
    }
    public void Sim4(){
        respuesta(")");
    }
    public void Sim5(){
        respuesta("÷");
    }
    public void Sim6(){
        respuesta("^");
    }
    public void Sim7(){
        respuesta("√");
    }
    public void Sim8(){
        respuesta("=");
    }


    /*//////////////TERMINAN LAS VARIABLES\\\\\\\\\\\\\\*/



    private static void shuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public String respuesta(String unidad) {
        String resbefore;
        if (res == null) {
            return ""; // O lanzar una excepción si es crítico
        }
        else{
            resbefore=res.getText().toString();
        }
        String nuevoTexto = resbefore + unidad;
        res.setText(nuevoTexto);
        res.setSelection(nuevoTexto.length());

        return nuevoTexto; // Retorna el texto actualizado
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
            Bundle enviarDatos = new Bundle();
            enviarDatos.putInt("tema", tema_elegido);
            Intent w = new Intent(this, ACT_AL_TemaLobby.class);
            w.putExtras(enviarDatos);
            startActivity(w);
        }


        return super.onOptionsItemSelected(item);
    }

}