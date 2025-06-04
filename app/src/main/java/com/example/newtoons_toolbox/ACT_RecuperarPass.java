package com.example.newtoons_toolbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class ACT_RecuperarPass extends AppCompatActivity {
    private EditText usu;

    private SharedPreferences prefe1;
    public static String usuario;

    public static Intent w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_recuperar_pass);
        prefe1=getSharedPreferences("datosusuarios", Context.MODE_PRIVATE);
        usu  = findViewById(R.id.etusuario2);

    }


    public void ocCambiar(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Introduce tu pin secreto");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Botón OK
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String pin = input.getText().toString();
                Map<String, ?> claves = prefe1.getAll();
                usuario= String.valueOf(usu.getText());
                String nose = usuario + "c";

                // Verificar si el pin ingresado es correcto
                if (claves.containsKey(nose) && pin.equals(claves.get(nose).toString())) {
                    // Si el pin es correcto, mostrar el siguiente diálogo
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ACT_RecuperarPass.this);
                    builder1.setTitle("Introduce tu nueva constraseña");

                    final EditText input1 = new EditText( ACT_RecuperarPass.this);
                    input1.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder1.setView(input1);

                    // Botón para aceptar el nuevo mensaje
                    builder1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String Nuevacontra = input1.getText().toString();
                            // Guardar el nuevo valor en SharedPreferences
                            SharedPreferences.Editor elemento = prefe1.edit();
                            elemento.putString(usuario, Nuevacontra);
                            elemento.commit();
                            Toast.makeText(getApplicationContext(), "Contraseña cambiada: ", Toast.LENGTH_SHORT).show();
                            cambio();
                        }
                    });

                    // Botón para cancelar
                    builder1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    // Mostrar el segundo diálogo
                    builder1.show();
                } else {
                    Toast.makeText(getApplicationContext(), "PIN incorrecto:" + claves.get(nose), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón Cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Mostrar el diálogo
        builder.show();
    }

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
            Intent w = new Intent(this,ACT_LogginAdmin.class);
            startActivity(w);
        }
        return super.onOptionsItemSelected(item);

    }
    public void cambio (){
        Intent w = new Intent(this,ACT_LogginAdmin.class);
        startActivity(w);
    }
}