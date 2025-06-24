package com.example.newtoons_toolbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ACT_F1_Calculadora_Hooke extends AppCompatActivity {

    private EditText etk, etf, etx, etw, etm, etg, etlf, etli, etΔL, etE, etA;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_calculadora_hooke);

        // Inicializar los EditText y el TextView
        etk = findViewById(R.id.etk);
        etf = findViewById(R.id.etf);
        etx = findViewById(R.id.etx);
        etw = findViewById(R.id.etw);
        etm = findViewById(R.id.etm);
        etg = findViewById(R.id.etg);
        etlf = findViewById(R.id.etlf);
        etli = findViewById(R.id.etli);
        etΔL = findViewById(R.id.etΔL);
        etE = findViewById(R.id.etE);
        etA = findViewById(R.id.etA);
        resultTextView = findViewById(R.id.txtResultadoHooke);

        Button btnCalcular = findViewById(R.id.btncalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
        ejecutarServicio("http://192.168.3.67:8080/newtons/up_calculadora.php");

    }
    private void ejecutarServicio(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_F1_Calculadora_Hooke.this, "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_F1_Calculadora_Hooke.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("materia","2");
                parametros.put("tema","2");
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void calcular() {
        // Limpiar el resultado anterior
        resultTextView.setText("");

        // Obtener los valores de los EditText
        String kStr = etk.getText().toString();
        String fStr = etf.getText().toString();
        String xStr = etx.getText().toString();
        String wStr = etw.getText().toString();
        String mStr = etm.getText().toString();
        String gStr = etg.getText().toString();
        String lfStr = etlf.getText().toString();
        String liStr = etli.getText().toString();
        String deltaLStr = etΔL.getText().toString();
        String eStr = etE.getText().toString();
        String aStr = etA.getText().toString();

        // Convertir a números
        double k = parseDouble(kStr);
        double f = parseDouble(fStr);
        double x = parseDouble(xStr);
        double w = parseDouble(wStr);
        double m = parseDouble(mStr);
        double g = parseDouble(gStr);
        double lf = parseDouble(lfStr);
        double li = parseDouble(liStr);
        double deltaL = parseDouble(deltaLStr);
        double e = parseDouble(eStr);
        double a = parseDouble(aStr);

        // Calcular según los datos ingresados
        if (!kStr.isEmpty() && !xStr.isEmpty()) {
            // Calcular F = k * x
            double fuerza = k * x;
            resultTextView.append("Fuerza (F) = " + fuerza + " N\n");
        }



        if (!fStr.isEmpty() && !xStr.isEmpty()) {
            // Calcular k = F / x
            double constante = f / x;
            resultTextView.append("Constante del resorte (k) = " + constante + " N/m\n");
        }

        if (!mStr.isEmpty() && !gStr.isEmpty()) {
            // Calcular w = m * g
            double peso = m * g;
            resultTextView.append("Peso (w) = " + peso + " N\n");
        }

        if (!lfStr.isEmpty() && !liStr.isEmpty()) {
            // Calcular x = Lf - Lo
            double deformacion = lf - li;
            resultTextView.append("Deformación (x) = " + deformacion + " m\n");
        }

        if (!kStr.isEmpty() && !deltaLStr.isEmpty()) {
            // Calcular w = (1/2) * k * ΔL²
            double energia = 0.5 * k * deltaL * deltaL;
            resultTextView.append("Energía potencial elástica (w) = " + energia + " J\n");
        }

        if (!fStr.isEmpty() && !aStr.isEmpty()) {
            // Calcular E = F / A
            double esfuerzo = f / a;
            resultTextView.append("Esfuerzo (E) = " + esfuerzo + " Pa\n");
        }

        if (!aStr.isEmpty() && !deltaLStr.isEmpty()) {
            // Calcular Le = Fm / A
            double alargamiento = f / a;
            resultTextView.append("Alargamiento específico (Le) = " + alargamiento + " m\n");
        }

        if (!aStr.isEmpty()) {
            // Calcular área de un círculo (radio)
            double areaRadio = Math.PI * Math.pow(deltaL, 2);
            resultTextView.append("Área (A) con radio = " + areaRadio + " m²\n");
        }

        if (!deltaLStr.isEmpty()) {
            // Calcular área de un círculo (diámetro)
            double areaDiametro = (Math.PI * Math.pow(deltaL, 2)) / 4;
            resultTextView.append("Área (A) con diámetro = " + areaDiametro + " m²\n");
        }
    }

    private double parseDouble(String str) {
        if (str.isEmpty()) {
            return 0; // Retorna 0 si el campo está vacío
        }
        return Double.parseDouble(str);
    }
}
