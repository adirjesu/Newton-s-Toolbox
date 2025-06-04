package com.example.newtoons_toolbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class ACT_LobbyAdmin extends AppCompatActivity {
    private EditText pass;
    private Button conf;
    private Button olv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_lobby_admin);

        pass = findViewById(R.id.editTextCorreo);
        conf = findViewById(R.id.botonEnviar);
        olv  = findViewById(R.id.btntonto);
    }

}