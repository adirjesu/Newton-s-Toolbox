package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ACT_F1_LH_C_Resultados extends AppCompatActivity {
public static int puntuacion=0;
private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_lh_cresultados);
        resultado=findViewById(R.id.txtResultado);

        Bundle recibeDatos = getIntent().getExtras();
        puntuacion = recibeDatos.getInt("respuestas");
        resultado.setText(String.valueOf(puntuacion) + "/10");

    }//main

}