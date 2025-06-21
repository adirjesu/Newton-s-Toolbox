package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ACT_LobbyMaestro extends AppCompatActivity {
    TextView usu,mat;
    Button reactivos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_lobby_maestro);
        Intent intent= getIntent();
        String usuario = intent.getStringExtra("usuario");
        String materia = intent.getStringExtra("materia");

        usu=findViewById(R.id.txttitulomaestro);
        mat=findViewById(R.id.txtmateriamaestro);
        reactivos=findViewById(R.id.btnmodificarreac);

        usu.setText("Bienvenido "+ usuario);
        mat.setText("Materia: "+ materia);

        reactivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    //////////////////menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuestudiantes,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.regresar) {
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Intent w = new Intent(this,ACT_Login.class);
            startActivity(w);
        }
        return super.onOptionsItemSelected(item);

    }

}