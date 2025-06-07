package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ACT_LobbyFisica1 extends AppCompatActivity {
    private Button hidro;
    private Button torricelli;
    private Button pascal;
    private Button hidrostatica;
    private Button hooke;
    private Button arqui;
    private Button young;
    public static int tema_elegido=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_lobby_fisica1);
        hidro=findViewById(R.id.btnhidrodinamica);
        hidro=findViewById(R.id.btntorricelli);
        hidro=findViewById(R.id.btnpascal);
        hidro=findViewById(R.id.btnhidrostatica);
        hidro=findViewById(R.id.btnhooke);
        hidro=findViewById(R.id.btnarquimidez);
        hidro=findViewById(R.id.btnyoung);

    }//main
    public void ocHidrodinamica (View view){
        tema_elegido=1;
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_TemaLobby.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }
    public void ocTorricelli (View view){
        tema_elegido=2;
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_TemaLobby.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }
    public void ocPascal (View view){
        tema_elegido=3;
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_TemaLobby.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }
    public void ocHidrostatica (View view){
        tema_elegido=4;
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_TemaLobby.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }
    public void ocHooke (View view){
        tema_elegido=5;
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_TemaLobby.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }    public void ocArquimidez (View view){
        tema_elegido=6;
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_TemaLobby.class);
        x.putExtras(enviarDatos);
        startActivity(x);
    }    public void ocYoung (View view){
        tema_elegido=7;
        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("tema",tema_elegido);
        Intent x =  new Intent(this,ACT_F1_TemaLobby.class);
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
            Intent w =  new Intent(this,ACT_LobbyEstudiante.class);
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