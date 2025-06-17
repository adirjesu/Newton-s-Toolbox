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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ACT_LogginAdmin extends AppCompatActivity {
    private EditText usu;
    private EditText contra;
    private Button conf;
    private Button log;
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
        contra = findViewById(R.id.etpass);
        log = findViewById(R.id.btnlogin);
        olv  = findViewById(R.id.btntonto);
        prefe1=getSharedPreferences("datosusuarios", Context.MODE_PRIVATE);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usuario= usu.getText().toString();
                final String pass=contra.getText().toString();

                Response.Listener<String> responseListener =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse= new JSONObject(response);
                            boolean sucess=jsonresponse.getBoolean("success");
                            if(sucess){
                                String name= jsonresponse.getString("nombres");
                                Intent intent = new Intent (ACT_LogginAdmin.this, ACT_LobbyAdmin.class);
                                intent.putExtra("nombres",name);
                                intent.putExtra("usuario",usuario);
                                ACT_LogginAdmin.this.startActivity(intent);


                            }else{
                                AlertDialog.Builder builder =new AlertDialog.Builder(ACT_LogginAdmin.this);
                                builder.setMessage("Error Login")
                                        .setNegativeButton("Retry",null)
                                        .create().show();

                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };

                LoginReques loginreques= new LoginReques(usuario,pass,responseListener);
                RequestQueue queue= Volley.newRequestQueue(ACT_LogginAdmin.this);
                queue.add(loginreques);
            }
        });

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