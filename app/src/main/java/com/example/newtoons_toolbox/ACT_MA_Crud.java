package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ACT_MA_Crud extends AppCompatActivity {
    private RadioButton[] radioButtons; // Array de RadioButtons
    private RadioButton t1,t2,t3;
    public static String materi,usuario,mat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ma_crud);

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



    }//main
    private void onRadioButtonClicked(RadioButton selectedButton) {
        for (RadioButton rb : radioButtons) {
            if (rb != selectedButton) {
                rb.setChecked(false); // Deseleccionar otros RadioButtons
            }
        }
    }
}