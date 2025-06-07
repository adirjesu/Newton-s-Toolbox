package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ACT_F1_Resultados extends AppCompatActivity {
public static int puntuacion=0;
private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_act_f1_cresultados);
        resultado=findViewById(R.id.txtResultado);

        Bundle recibeDatos = getIntent().getExtras();
        puntuacion = recibeDatos.getInt("respuestas");
        resultado.setText(String.valueOf(puntuacion) + "/10");

    }//main
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
            Intent w =  new Intent(this, ACT_F1_TemaLobby.class);
            startActivity(w);
        }



        return super.onOptionsItemSelected(item);
    }
}