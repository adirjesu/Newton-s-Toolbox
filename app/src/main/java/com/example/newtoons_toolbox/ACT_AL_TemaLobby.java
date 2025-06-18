package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ACT_AL_TemaLobby extends AppCompatActivity {

    private TextView titulo;
    private Button cal;
    private Button cues;
    private Button form;
    private Button exp;
    public static int tema_elegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_al_tema_lobby);
        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");
        titulo = findViewById(R.id.txtTituloAlgLobby);
    }



}