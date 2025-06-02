package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ACT_F1_LH_banco_formulas extends AppCompatActivity {
    private Button f1;
    private Button f2;
    private Button f3;
    private Button f4;
    private Button f5;
    private Button f6;
    private TextView tf1;
    private TextView tf2;
    private TextView tf3;
    private TextView tf4;
    private TextView tf5;
    private TextView tf6;
    private TextView tf0;
    public static String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_lh_banco_formulas);
        f1          =findViewById(R.id.btnf1);
        f2          =findViewById(R.id.btnf2);
        f3          =findViewById(R.id.btnf3);

        tf1         =findViewById(R.id.txtf1);
        tf2         =findViewById(R.id.txtf2);
        tf3         =findViewById(R.id.txtf3);


    }//main

    public void ocAbrirf1(View view){
        abrir("tf1");
    }
    public void ocAbrirf2(View view){
        abrir("tf2");
    }
    public void ocAbrirf3(View view){
        abrir("tf3");
    }
    public void abrir(String nombre) {
        // Ocultar todos los TextViews antes de mostrar el seleccionado
        tf1.setVisibility(View.GONE);
        tf2.setVisibility(View.GONE);
        tf3.setVisibility(View.GONE);
        tf4.setVisibility(View.GONE);
        tf5.setVisibility(View.GONE);
        tf6.setVisibility(View.GONE);
        // Mostrar el TextView correspondiente basado en el nombre
        switch (nombre) {
            case "tf1":
                tf1.setVisibility(View.VISIBLE);
                break;
            case "tf2":
                tf2.setVisibility(View.VISIBLE);
                break;
            case "tf3":
                tf3.setVisibility(View.VISIBLE);
                break;
            case "tf4":
                tf4.setVisibility(View.VISIBLE);
                break;
            case "tf5":
                tf5.setVisibility(View.VISIBLE);
                break;
            case "tf6":
                tf6.setVisibility(View.VISIBLE);
                break;
            default:
                // Manejar el caso en que no se encuentra el nombre
                break;
        }
    }
}