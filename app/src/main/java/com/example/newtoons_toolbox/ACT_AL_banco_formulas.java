package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ACT_AL_banco_formulas extends AppCompatActivity {

    public static int tema_elegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_al_banco_formulas);

        Button btnMonomio = findViewById(R.id.btnMonomio);
        Button btnBinomio = findViewById(R.id.btnBinomio);
        Button btnTrinomio = findViewById(R.id.btnTrinomio);
        Button btnPolinomio = findViewById(R.id.btnPolinomio);

        LinearLayout layoutMonomio = findViewById(R.id.layoutMonomio);
        LinearLayout layoutBinomio = findViewById(R.id.layoutBinomio);
        LinearLayout layoutTrinomio = findViewById(R.id.layoutTrinomio);
        LinearLayout layoutPolinomio = findViewById(R.id.layoutPolinomio);


        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");


        btnMonomio.setOnClickListener(v -> toggleVisibility(layoutMonomio));
        btnBinomio.setOnClickListener(v -> toggleVisibility(layoutBinomio));
        btnTrinomio.setOnClickListener(v -> toggleVisibility(layoutTrinomio));
        btnPolinomio.setOnClickListener(v -> toggleVisibility(layoutPolinomio));
    }



    private void toggleVisibility(LinearLayout layout) {
        layout.setVisibility(layout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
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