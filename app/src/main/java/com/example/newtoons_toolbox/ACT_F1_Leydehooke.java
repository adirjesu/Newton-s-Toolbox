package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ACT_F1_Leydehooke extends AppCompatActivity {

    private Button cal;
    private Button cues;
    private Button form;
    private Button exp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_leydehooke);




    }//main
    public void ocCalf1lh (View view){
        Intent x =  new Intent(this,ACT_LobbyEstudiante.class);
        startActivity(x);
    }
    public void ocCuesf1lh (View view){
        Intent x =  new Intent(this,ACT_F1_LH_cuestionario.class);
        startActivity(x);
    }
    public void ocForf1lh (View view){
        Intent x =  new Intent(this,ACT_LobbyEstudiante.class);
        startActivity(x);
    }
    public void ocExpf1lh (View view){
        Intent x =  new Intent(this,ACT_LobbyEstudiante.class);
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