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
import android.widget.EditText;
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

import java.util.Random;

public class ACT_F1_Cuestionario extends AppCompatActivity {
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
        setContentView(R.layout.activity_act_f1_cuestionario);
        nopreg    =findViewById(R.id.txtNopregunta);
        preg      =findViewById(R.id.txtPregunta);
        res       =findViewById(R.id.etrespuesta);
        sino      =findViewById(R.id.txtsino);
        retro     =findViewById(R.id.txtRetro);
        calificar =findViewById(R.id.btnCalificar);
        continuar =findViewById(R.id.btnContinuar);

        s1        =findViewById(R.id.btns1);
        s2         =findViewById(R.id.btns2);
        s3         =findViewById(R.id.btns3);
        s4         =findViewById(R.id.btns4);
        s5     =findViewById(R.id.btns5);
        s6     =findViewById(R.id.btns6);
        s7       =findViewById(R.id.btns7);
        s8        =findViewById(R.id.btns8);


        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");
        if(tema_elegido==1){
            buscarreactivos("http://192.168.0.247:8080/newtons/buscar_preguntas.php?materia=2&tema=1");

        } else if (tema_elegido==2) {
            preguntas = getResources().getStringArray(R.array.f1_lhp);
            respuestas = getResources().getStringArray(R.array.f1_lhr);
            retroalimentaciones = getResources().getStringArray(R.array.f1_lha);
            s1.setText("N/m");
            s2.setText("N");
            s3.setText("m");
            s4.setText("J");
            s5.setText("-");
            s6.setText("×10^");
            s7.setText("^");
            s8.setText("Pa");
            buscarreactivos("http://192.168.0.247:8080/newtons/buscar_preguntas.php?materia=2&tema=2");
        } else if (tema_elegido==3) {
            buscarreactivos("http://192.168.0.247:8080/newtons/buscar_preguntas.php?materia=2&tema=3");

        }


// Inicializa la matriz con el tamaño adecuado




        mensaje(ACT_F1_Cuestionario.this);
        nopreg.setText("Pregunta : " + (contadorotro));

    }//del main

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
            Intent x =  new Intent(this, ACT_F1_Resultados.class);
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
//////////////////////////* LOS DESTOS PA'  CONECTAR *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /////////////
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
                        Toast.makeText(ACT_F1_Cuestionario.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ACT_F1_Cuestionario.this, "NO SE ENCONTRARON REACTIVOS", Toast.LENGTH_SHORT).show();

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    ///////////////////
    /////////////////////////////////////////////////////////


/////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void sim1(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("N/m");
        }else if (tema_elegido==3) {

        }
    }
    public void sim2(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("N");
        }else if (tema_elegido==3) {

        }
    }
    public void sim3(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("m");
        }else if (tema_elegido==3) {

        }
    }
    public void sim4(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("J");
        }else if (tema_elegido==3) {

        }
    }
    public void sim5(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("-");
        }else if (tema_elegido==3) {

        }
    }
    public void sim6(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("×10^");
        }else if (tema_elegido==3) {

        }
    }
    public void sim7(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("^");
        }else if (tema_elegido==3) {

        }
    }
    public void sim8(View view){
        if(tema_elegido==1){

        } else if (tema_elegido==2) {
            respuesta("Pa");
        }else if (tema_elegido==3) {

        }
    }


/////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

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