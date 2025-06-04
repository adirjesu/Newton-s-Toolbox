package com.example.newtoons_toolbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ACT_F1_LH_cuestionario extends AppCompatActivity {
    public static int [] praleatorio= new int[10];
    private TextView nopreg;
    private TextView preg;
    private TextView res;
    private TextView sino;
    private Button calificar;
    private Button continuar;

    private Button nm;
    private Button n;
    private Button m;
    private Button j;
    private Button menos;
    private Button por10;
    private Button ala;
    private Button pa;

    private TextView retro;
    public static  String[][] matriz = new String[10][3];
    public static int contador=0;
    public static int contadorotro=1;
    public static int puntuacion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_lh_cuestionario);
        nopreg    =findViewById(R.id.txtNopregunta);
        preg      =findViewById(R.id.txtPregunta);
        res       =findViewById(R.id.etrespuesta);
        sino      =findViewById(R.id.txtsino);
        retro     =findViewById(R.id.txtRetro);
        calificar =findViewById(R.id.btnCalificar);
        continuar =findViewById(R.id.btnContinuar);

        nm        =findViewById(R.id.btnNm);
        n         =findViewById(R.id.btnN);
        m         =findViewById(R.id.btnm);
        j         =findViewById(R.id.btnJ);
        menos     =findViewById(R.id.m);
        por10     =findViewById(R.id.btnx10);
        ala       =findViewById(R.id.btnal);
        pa        =findViewById(R.id.btnPa);
        String[] preguntas = getResources().getStringArray(R.array.f1_lhp);
        String[] respuestas = getResources().getStringArray(R.array.f1_lhr);
        String[] retroalimentaciones = getResources().getStringArray(R.array.f1_lha);
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


        mensaje(ACT_F1_LH_cuestionario.this);
        nopreg.setText("Pregunta : " + (contadorotro));

    }//del main

    public void ocCalificar(View view){

        calificar.setEnabled(false);
        calificar.setVisibility(View.INVISIBLE);
        continuar.setEnabled(true);
        continuar.setVisibility(View.VISIBLE);

        retro.setText(String.valueOf(matriz[praleatorio[contador]][2]));

        String respuesta= String.valueOf(res.getText());
        if(respuesta.equals(String.valueOf(matriz[praleatorio[contador]][1]))){
            sino.setText("corecto");
            sino.setTextColor(Color.rgb(76,175,80));
            continuar.setBackgroundColor(Color.rgb(76,175,80));
            puntuacion++;
            }
            else{
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
                Intent x =  new Intent(this,ACT_F1_LH_C_Resultados.class);
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
    public void ocNm(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"N/m");
    }
    public void ocN(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"N");
    }
    public void ocm(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"m");
    }
    public void ocJ(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"J");
    }
    public void ocmenos(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"-");
    }
    public void ocpor10(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"×10^");
    }
    public void ocala(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"^");
    }
    public void ocpa(View view){
        String resbefore= String.valueOf(res.getText());
        res.setText(resbefore+"Pa");
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

}