package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ACT_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);
    }
    public void ocEstudiante (View view){
        Intent x =  new Intent(this,ACT_LobbyEstudiante.class);
        startActivity(x);
    }
    public void ocAdmin (View view){
        Intent x =  new Intent(this,ACT_LogginAdmin.class);
        startActivity(x);
    }
}