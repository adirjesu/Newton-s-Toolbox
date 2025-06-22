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

import java.util.Random;

public class ACT_AL_Cuestionario extends AppCompatActivity {

    public static int [] praleatorio= new int[10];
    private TextView nopreg;
    private TextView preg;
    private TextView res;
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

        s1        =findViewById(R.id.btns1Al);
        s2         =findViewById(R.id.btns2Al);
        s3         =findViewById(R.id.btns3Al);
        s4         =findViewById(R.id.btns4Al);
        s5     =findViewById(R.id.btns5Al);
        s6     =findViewById(R.id.btns6Al);
        s7       =findViewById(R.id.btns7Al);
        s8        =findViewById(R.id.btns8Al);


        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");

        if(tema_elegido==1){
            /*
            preguntas = getResources().getStringArray(R.array.al_poli);
            respuestas = getResources().getStringArray(R.array.al_poli);
            retroalimentaciones = getResources().getStringArray(R.array.al_poli);
            s1.setText("+");
            s2.setText("-");
            s3.setText("(");
            s4.setText(")");
            s5.setText("÷");
            s6.setText("^");
            s7.setText("√");
            s8.setText("=");
            */
        } else if (tema_elegido==2) {
            /*
            preguntas = getResources().getStringArray(R.array.al_ecua);
            respuestas = getResources().getStringArray(R.array.al_ecua);
            retroalimentaciones = getResources().getStringArray(R.array.al_ecua);
            s1.setText("+");
            s2.setText("-");
            s3.setText("(");
            s4.setText(")");
            s5.setText("÷");
            s6.setText("^");
            s7.setText("√");
            s8.setText("=");
            */
        } else if (tema_elegido==3) {
            /*
            preguntas = getResources().getStringArray(R.array.al_fact);
            respuestas = getResources().getStringArray(R.array.al_fact);
            retroalimentaciones = getResources().getStringArray(R.array.al_fact);
            s1.setText("+");
            s2.setText("-");
            s3.setText("(");
            s4.setText(")");
            s5.setText("÷");
            s6.setText("^");
            s7.setText("√");
            s8.setText("=");
            */
        }


// Inicializa la matriz con el tamaño adecuado

// Llenar la matriz con preguntas, respuestas y retroalimentaciones
        for (int i = 0; i < preguntas.length; i++) {
            matriz[i][0] = preguntas[i]; // Pregunta
            matriz[i][1] = respuestas[i]; // Respuesta
            matriz[i][2] = retroalimentaciones[i]; // Retroalimentación
        }

        for(int i=0;i<praleatorio.length;i++){
            praleatorio[i]=i;
        }
        shuffleArray(praleatorio);
        preg.setText(matriz[praleatorio[0]][0]);


        mensaje(ACT_AL_Cuestionario.this);
        nopreg.setText("Pregunta : " + (contadorotro));

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


/////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void sim1(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"+");
    }
    public void sim2(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"-");
    }
    public void sim3(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"(");
    }
    public void sim4(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+")");
    }
    public void sim5(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"÷");
    }
    public void sim6(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"^");
    }
    public void sim7(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"√");
    }
    public void sim8(){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"=");
    }


    /////////////////////////*     SÍMBOLOS   *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void ocMas(View view){ sim1(); }
    public void ocMenos(View view){ sim2(); }
    public void ocAbrir(View view){ sim3(); }
    public void ocCerrar(View view){ sim4(); }
    public void ocEntre(View view){ sim5(); }
    public void ocPow(View view){ sim6(); }
    public void ocSqrt(View view){ sim7(); }
    public void ocIgual(View view){ sim8(); }



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