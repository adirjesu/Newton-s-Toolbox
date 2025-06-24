package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ACT_AL_Calculadora_Ecua extends AppCompatActivity {

    EditText etA, etB, etC;
    TextView tvResultado;
    Button btnResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_al_calculadora_ecua);

        etA = findViewById(R.id.etA);
        etB = findViewById(R.id.etB);
        etC = findViewById(R.id.etC);
        tvResultado = findViewById(R.id.tvResultado);
        btnResolver = findViewById(R.id.btnResolver);

        btnResolver.setOnClickListener(v -> resolverEcuacion());
    }


    private void resolverEcuacion() {
        String sa = etA.getText().toString();
        String sb = etB.getText().toString();
        String sc = etC.getText().toString();

        if (sa.isEmpty() || sb.isEmpty() || sc.isEmpty()) {
            tvResultado.setText("Por favor completa todos los campos.");
            return;
        }

        double a = Double.parseDouble(sa);
        double b = Double.parseDouble(sb);
        double c = Double.parseDouble(sc);

        if (a == 0) {
            // Ecuación de primer grado: bx + c = 0
            if (b == 0) {
                tvResultado.setText(c == 0 ? "Tiene infinitas soluciones." : "No tiene solución.");
            } else {
                double x = -c / b;
                tvResultado.setText("Solución de primer grado:\nx = " + String.format("%.2f", x));
            }
        } else {
            // Ecuación de segundo grado: ax² + bx + c = 0
            double discriminante = b * b - 4 * a * c;

            if (discriminante > 0) {
                double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
                double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);
                tvResultado.setText("Soluciones reales distintas:\nx₁ = " + String.format("%.2f", x1) + "\nx₂ = " + String.format("%.2f", x2));
            } else if (discriminante == 0) {
                double x = -b / (2 * a);
                tvResultado.setText("Solución real doble:\nx = " + String.format("%.2f", x));
            } else {
                // Soluciones complejas
                double real = -b / (2 * a);
                double imag = Math.sqrt(-discriminante) / (2 * a);
                tvResultado.setText("Soluciones complejas:\n" + "x₁ = " + String.format("%.2f", real) + " + " + String.format("%.2f", imag) + "i" + "\nx₂ = " + String.format("%.2f", real) + " - " + String.format("%.2f", imag) + "i");
            }
        }
    }
}