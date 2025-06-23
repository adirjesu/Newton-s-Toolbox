package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ACT_AL_Calculadora_Fact extends AppCompatActivity {

    Spinner spTipo;
    EditText etA, etB;
    EditText etBinA, etBinB, etTriA, etTriB, etTriC;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_al_calculadora_fact);

        spTipo = findViewById(R.id.spTipo);
        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        etBinA = findViewById(R.id.etBinA);
        etBinB = findViewById(R.id.etBinB);
        etTriA = findViewById(R.id.etTriA);
        etTriB = findViewById(R.id.etTriB);
        etTriC = findViewById(R.id.etTriC);
        tvResultado = findViewById(R.id.tvResultado);

        findViewById(R.id.btnFactorizar).setOnClickListener(v -> {
            switch (spTipo.getSelectedItemPosition()) {
                case 1:
                    factorComun();
                    break;

                case 2:
                    diferenciaCuadrados();
                    break;

                case 3:
                    trinomioCuadrado();
                    break;
            }
        });
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualizarCamposSegunTipo(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                actualizarCamposSegunTipo(-1);  // Ninguna selección
            }
        });
    }


    private void actualizarCamposSegunTipo(int tipo) {
        etA.setText("");
        etB.setText("");
        etBinA.setText("");
        etBinB.setText("");
        etTriA.setText("");
        etTriB.setText("");
        etTriC.setText("");

        if (tipo <= 0) { // Desactiva todos
            etA.setEnabled(false);
            etB.setEnabled(false);
            etBinA.setEnabled(false);
            etBinB.setEnabled(false);
            etTriA.setEnabled(false);
            etTriB.setEnabled(false);
            etTriC.setEnabled(false);
        } /* Activa todos los necesarios */ else if (tipo == 1) { // Factor común
            etA.setEnabled(true);
            etB.setEnabled(true);
            etBinA.setEnabled(false);
            etBinB.setEnabled(false);
            etTriA.setEnabled(false);
            etTriB.setEnabled(false);
            etTriC.setEnabled(false);
        } else if (tipo == 2) { // Diferencia de cuadrados
            etA.setEnabled(false);
            etB.setEnabled(false);
            etBinA.setEnabled(true);
            etBinB.setEnabled(true);
            etTriA.setEnabled(false);
            etTriB.setEnabled(false);
            etTriC.setEnabled(false);
        } else if (tipo == 3) { // Trinomio cuadrado
            etA.setEnabled(false);
            etB.setEnabled(false);
            etBinA.setEnabled(false);
            etBinB.setEnabled(false);
            etTriA.setEnabled(true);
            etTriB.setEnabled(true);
            etTriC.setEnabled(true);
        }
    }



    private void factorComun() {
        int a = Integer.parseInt(etA.getText().toString());
        int b = Integer.parseInt(etB.getText().toString());

        int mcd = gcd(Math.abs(a), Math.abs(b));  // Máximo común divisor

        int a1 = a / mcd;
        int b1 = b / mcd;

        tvResultado.setText("Factor común:\n" + mcd + "( " + a1 + "x + " + b1 + " )");
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }




    private void diferenciaCuadrados() {
        double a2 = Double.parseDouble(etBinA.getText().toString());
        double b2 = Double.parseDouble(etBinB.getText().toString());

        if (a2 < 0 || b2 < 0) {
            tvResultado.setText("No es una diferencia de cuadrados.");
            return;
        }

        double a = Math.sqrt(a2);
        double b = Math.sqrt(b2);

        String strA = String.format("%.2f", a);
        String strB = String.format("%.2f", b);

        tvResultado.setText("Diferencia de cuadrados:\n(" + strA + "x + " + strB + ")(" + strA + "x - " + strB + ")");
    }



    private void trinomioCuadrado() {
        int a = Integer.parseInt(etTriA.getText().toString());
        int b = Integer.parseInt(etTriB.getText().toString());
        int c = Integer.parseInt(etTriC.getText().toString());

        if (a != 1) {
            tvResultado.setText("Solo se acepta a = 1 por ahora.");
            return;
        }

        // Buscar dos números que sumen b y multipliquen c
        boolean factorizado = false;
        for (int i = -Math.abs(c); i <= Math.abs(c); i++) {
            for (int j = -Math.abs(c); j <= Math.abs(c); j++) {
                if (i + j == b && i * j == c) {
                    tvResultado.setText("Trinomio factorizado:\n(x + " + i + ")(x + " + j + ")");
                    factorizado = true;
                    break;
                }
            }
            if (factorizado) break;
        }

        if (!factorizado)
            tvResultado.setText("No se puede factorizar con números enteros.");
    }
}