package com.example.newtoons_toolbox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class ACT_LogginAdmin extends AppCompatActivity {
    private EditText usu;
    private EditText pass;
    private Button conf;
    private Button olv;
    private static final String PREFS_NAME = "UsuariosPrefs";
    private static final String KEY_USUARIOS = "usuarios";
    public static String usuario;
    private SharedPreferences prefe1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_logginadmin);

        usu  = findViewById(R.id.etusuario);
        pass = findViewById(R.id.etpass);
        conf = findViewById(R.id.botonEnviar);
        olv  = findViewById(R.id.btntonto);
        prefe1=getSharedPreferences("datosTelefonos", Context.MODE_PRIVATE);

        //agregarlo
        SharedPreferences.Editor elemento =prefe1.edit();
        elemento.putString("Admin","12345");
        elemento.putString("Adminc","soy tonto");
        elemento.commit();

        //leerlo

    }
    public void ocEnviar(View view){
         usuario= String.valueOf(usu.getText());
        String pas= String.valueOf(pass.getText());
        Map<String,?> claves=prefe1.getAll();

        for(Map.Entry<String,?>ele:claves.entrySet()){
            if(usuario.equals(ele.getKey())){
                if(pas.equals(ele.getValue().toString())){
                    Intent w =  new Intent(this,ACT_LobbyAdmin.class);
                    startActivity(w);
                }
                else{
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();

                }
            }
            Toast.makeText(this, "Bienvenid@ "+ele.getKey(), Toast.LENGTH_LONG).show();

        }
            // No hay clave usuarios en prefs, no hay usuarios registrados
    }
    public void ocLaperdi(View view) {
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
                String nose = usuario + "c";

                // Verificar si el pin ingresado es correcto
                if (claves.containsKey(nose) && pin.equals(claves.get(nose).toString())) {
                    // Si el pin es correcto, mostrar el siguiente diálogo
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ACT_LogginAdmin.this);
                    builder1.setTitle("Introduce tu nueva constraseña");

                    final EditText input1 = new EditText( ACT_LogginAdmin.this);
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
                            Toast.makeText(getApplicationContext(), "Texto ingresado: " + Nuevacontra, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(), "PIN incorrecto:" + nose, Toast.LENGTH_SHORT).show();
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

}