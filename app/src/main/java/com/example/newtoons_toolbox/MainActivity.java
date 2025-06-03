package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulo=findViewById(R.id.txtTitulo);
        titulo.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK);

    }
    public void ocInicio (View view){
        Intent x =  new Intent(this,ACT_Login.class);
        startActivity(x);
    }
}