package com.example.newtoons_toolbox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
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
private int existe=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_logginadmin);

        usu  = findViewById(R.id.etusuario);
        pass = findViewById(R.id.etpass);
        conf = findViewById(R.id.botonEnvia);
        olv  = findViewById(R.id.btntonto);
        prefe1=getSharedPreferences("datosusuarios", Context.MODE_PRIVATE);

        //agregarlo
        Map<String,?> claves=prefe1.getAll();

        for(Map.Entry<String,?>ele:claves.entrySet()) {
            if(ele.getKey().equals("Admin")){
                existe++;
            }


        }
        if(existe==0){
            SharedPreferences.Editor elemento = prefe1.edit();
            elemento.putString("Admin", "12345");
            elemento.putString("Adminc", "soy tonto");
            elemento.commit();
        }

        //leerlo

    }
    public void ocEnvia(View view){

        usuario= String.valueOf(usu.getText());
        String pas= String.valueOf(pass.getText());
        Map<String,?> claves=prefe1.getAll();

        for(Map.Entry<String,?>elem:claves.entrySet()){
            if(usuario.equals(elem.getKey())){
                if(pas.equals(elem.getValue().toString())){
                    Toast.makeText(this, "Bienvenid@ "+elem.getKey(), Toast.LENGTH_LONG).show();
                    Intent w =  new Intent(this,ACT_LobbyAdmin.class);
                    startActivity(w);
                }
                else{
                    Toast.makeText(this, "Contraseña incorrecta"+ elem.getValue(), Toast.LENGTH_LONG).show();

                }

            }
            Toast.makeText(this, "Contraseña incorrecta"+ elem.getValue(), Toast.LENGTH_LONG).show();

        }
        Toast.makeText(this, "No hay usuarios que coincidan", Toast.LENGTH_LONG).show();
        // No hay clave usuarios en prefs, no hay usuarios registrados
    }
    public void ocLaperdi(View view) {
        Intent x =  new Intent(this,ACT_RecuperarPass.class);
        startActivity(x);
    }
//
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