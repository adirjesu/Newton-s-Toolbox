package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ACT_AL_Calculadora_Poli extends AppCompatActivity {

    public static int tema_elegido;
    EditText a1, b1, c1, a2, b2, c2;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_al_calculadora_poli);

        a1 = findViewById(R.id.a1);
        b1 = findViewById(R.id.b1);
        c1 = findViewById(R.id.c1);

        a2 = findViewById(R.id.a2);
        b2 = findViewById(R.id.b2);
        c2 = findViewById(R.id.c2);

        tvResultado = findViewById(R.id.tvResultado);

        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");

        findViewById(R.id.btnSumar).setOnClickListener(v -> calcular(true));
        findViewById(R.id.btnRestar).setOnClickListener(v -> calcular(false));
        findViewById(R.id.btnMultiplicar).setOnClickListener(v -> multiplicarPolinomios());
        findViewById(R.id.btnDividir).setOnClickListener(v -> dividirPorMonomio());
    }


    private void calcular(boolean esSuma) {
        double A1 = Double.parseDouble(a1.getText().toString());
        double B1 = Double.parseDouble(b1.getText().toString());
        double C1 = Double.parseDouble(c1.getText().toString());

        double A2 = Double.parseDouble(a2.getText().toString());
        double B2 = Double.parseDouble(b2.getText().toString());
        double C2 = Double.parseDouble(c2.getText().toString());

        double a = esSuma ? A1 + A2 : A1 - A2;
        double b = esSuma ? B1 + B2 : B1 - B2;
        double c = esSuma ? C1 + C2 : C1 - C2;

        String resultado = String.format("Resultado: %.2fx² %s %.2fx %s %.2f", a, b >= 0 ? "+" : "-", Math.abs(b), c >= 0 ? "+" : "-", Math.abs(c));
        tvResultado.setText(resultado);
    }


    private void multiplicarPolinomios() {
        double A1 = Double.parseDouble(a1.getText().toString());
        double B1 = Double.parseDouble(b1.getText().toString());
        double C1 = Double.parseDouble(c1.getText().toString());

        double A2 = Double.parseDouble(a2.getText().toString());
        double B2 = Double.parseDouble(b2.getText().toString());
        double C2 = Double.parseDouble(c2.getText().toString());

        // (a1x² + b1x + c1)(a2x² + b2x + c2)
        double a = A1 * A2;
        double b = A1 * B2 + B1 * A2;
        double c = A1 * C2 + B1 * B2 + C1 * A2;
        double d = B1 * C2 + C1 * B2;
        double e = C1 * C2;

        String resultado = String.format("Resultado:\n%.2fx⁴ + %.2fx³ + %.2fx² + %.2fx + %.2f", a, b, c, d, e);
        tvResultado.setText(resultado);
    }


    private void dividirPorMonomio() {
        EditText etMonomio = findViewById(R.id.etMonomio);
        double monomio = Double.parseDouble(etMonomio.getText().toString());

        if (monomio == 0) {
            tvResultado.setText("Error: no se puede dividir entre cero.");
            return;
        }

        double A1 = Double.parseDouble(a1.getText().toString());
        double B1 = Double.parseDouble(b1.getText().toString());
        double C1 = Double.parseDouble(c1.getText().toString());

        String resultado = String.format("Resultado:\n%.2fx² + %.2fx + %.2f", A1 / monomio, B1 / monomio, C1 / monomio);
        tvResultado.setText(resultado);
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