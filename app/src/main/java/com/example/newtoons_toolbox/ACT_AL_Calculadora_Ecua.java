package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ACT_AL_Calculadora_Ecua extends AppCompatActivity {

    public static int tema_elegido;
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

        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");

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



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.regresar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.regresar) {
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Bundle enviarDatos = new Bundle();
            enviarDatos.putInt("tema", tema_elegido);
            Intent w = new Intent(this, ACT_AL_TemaLobby.class);
            w.putExtras(enviarDatos);
            startActivity(w);
        }


        return super.onOptionsItemSelected(item);
    }
}