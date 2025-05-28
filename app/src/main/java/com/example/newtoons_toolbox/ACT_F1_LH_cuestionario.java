package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ACT_F1_LH_cuestionario extends AppCompatActivity {
    public static int [] praleatorio= new int[39];
    private TextView preg;
    private TextView Res;
    private TextView nopre;
    private Button boton;
    private Button botonC;
    private TextView retro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_lh_cuestionario);
        String[][] matriz = new String[3][3];

        matriz[0] = getResources().getStringArray(R.array.f1_lhp);
        matriz[1] = getResources().getStringArray(R.array.f1_lhr);
        matriz[2] = getResources().getStringArray(R.array.f1_lha);
        preg=findViewById(R.id.txtPregunta);

// Mostrar en Logcat
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                Log.d("Matriz", "Elemento [" + i + "][" + j + "] = " + matriz[i][j]);
            }
        }//primer for

        for(int i=0;i<praleatorio.length;i++){
            praleatorio[i]=i;
        }
        shuffleArray(praleatorio);
        preg.setText(matriz[praleatorio[0]][0]);

    }//del main

    private static void shuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

}