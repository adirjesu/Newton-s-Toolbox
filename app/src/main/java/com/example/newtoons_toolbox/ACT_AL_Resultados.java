package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ACT_AL_Resultados extends AppCompatActivity {

    public static int puntuacion=0;
    private TextView resultado;
    public static int tema_elegido=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_al_resultados);
        resultado = findViewById(R.id.txtResultadoAl);

        Bundle recibeDatos = getIntent().getExtras();
        puntuacion = recibeDatos.getInt("respuestas");
        resultado.setText(String.valueOf(puntuacion) + "/10");
        tema_elegido = recibeDatos.getInt("tema");

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