package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
<<<<<<< HEAD
=======

>>>>>>> 4214a16d9340f7ccaea578e563e822bbd880aa24
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
<<<<<<< HEAD
=======

>>>>>>> 4214a16d9340f7ccaea578e563e822bbd880aa24
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulo=findViewById(R.id.txtTitulo2);
        titulo.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK);

    }
    public void ocInicio (View view){
        Intent x =  new Intent(this,ACT_Login.class);
        startActivity(x);
    }
}