package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ACT_F1_TemaLobby extends AppCompatActivity {

    private TextView titulo;
    private Button cal;
    private Button cues;
    private Button form;
    private Button exp;
    public static int tema_elegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_temalobby);
        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");
        titulo=findViewById(R.id.txtTitulo5);
        if(tema_elegido==1){
            titulo.setText("Principio de Pascal");

        }
        if(tema_elegido==2){
        titulo.setText("Ley de Hooke");
        }

        if(tema_elegido==3){
            titulo.setText("Módulo de Young");

        }
    }//main

    public void ocCalf1lh (View view){
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_Calculadora_Hooke.class);
        x.putExtras(enviarDatos);
        startActivity(x);

    }
    public void ocCuesf1lh (View view){
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_Cuestionario.class);
        x.putExtras(enviarDatos);
        startActivity(x);


    }
    public void ocForf1lh (View view){
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_banco_formulas.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }
    public void ocExpf1lh (View view){
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_Explicacion.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuestudiantes,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id=item.getItemId();
        if(id==R.id.regresar){
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Intent w =  new Intent(this,ACT_LobbyFisica1.class);
            startActivity(w);
        }

        if(id==R.id.Algebra){
            Toast.makeText(this, "Accediendo a Algebra",
                    Toast.LENGTH_SHORT).show();
            Intent x =  new Intent(this,ACT_LobbyAlgebra.class);
            startActivity(x);
        }

        if(id==R.id.Fisica1){
            Toast.makeText(this, "Accediendo a Física I",
                    Toast.LENGTH_SHORT).show();
            Intent y =  new Intent(this,ACT_LobbyFisica1.class);
            startActivity(y);
        }
        if(id==R.id.Fisica2){
            Toast.makeText(this, "Accediendo a Física II",
                    Toast.LENGTH_SHORT).show();
            Intent z =  new Intent(this,ACT_LobbyFisica2.class);
            startActivity(z);
        }

        return super.onOptionsItemSelected(item);
    }


}