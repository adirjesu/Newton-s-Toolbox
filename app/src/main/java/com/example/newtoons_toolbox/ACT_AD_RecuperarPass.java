package com.example.newtoons_toolbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;


public class ACT_AD_RecuperarPass extends AppCompatActivity {
    public static Intent w;
    String correo,contra1;
    EditText usuario,n1,n2,n3,n4,n5,pass;
    TextView titulo,nuevacontra;
    Button enviar, confcodigo,cambiarpass;
    Session session;
    public static String correousu,codigo5,codig5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_ad_recuperar_pass);

        usuario = findViewById(R.id.etmensaje);
        enviar = findViewById(R.id.btnenviar);
        confcodigo =findViewById(R.id.btncodigo);

        titulo=findViewById(R.id.txtenviadocorreo);
        n1=findViewById(R.id.etn1);
        n2=findViewById(R.id.etn2);
        n3=findViewById(R.id.etn3);
        n4=findViewById(R.id.etn4);
        n5=findViewById(R.id.etn5);
        nuevacontra=findViewById(R.id.txtcontranueva);
        pass=findViewById(R.id.etnuevacontra);
        cambiarpass=findViewById(R.id.btncambiarpass);

        correo = "newtonstoolbox@gmail.com";
        contra1 = "icuu usyt bvvm vqmy";
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sala 1:   192.168.0.247
                //mi casa:  192.168.3.67
                buscar("http://192.168.0.247:8080/newtons/recuperar_pass.php?usuario="+usuario.getText()+"");
            }
        });
        confcodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmar();
            }
        });
        cambiarpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarpass("http://192.168.0.247:8080/newtons/editar_pass.php");

            }
        });
        //////



        n2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Detectar si se presionó la tecla retroceso (Backspace)
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        n2.setText("");
                        n1.requestFocus();
                        return true; // Indica que el evento fue manejado
                    }
                }
                return false; // Permite que otros eventos se procesen normalmente
            }
        });
        n3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Detectar si se presionó la tecla retroceso (Backspace)
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        n3.setText("");
                        n2.requestFocus();
                        return true; // Indica que el evento fue manejado
                    }
                }
                return false; // Permite que otros eventos se procesen normalmente
            }
        });
        n4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Detectar si se presionó la tecla retroceso (Backspace)
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        n4.setText("");
                        n3.requestFocus();
                        return true; // Indica que el evento fue manejado
                    }
                }
                return false; // Permite que otros eventos se procesen normalmente
            }
        });
        n5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Detectar si se presionó la tecla retroceso (Backspace)
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        n5.setText("");
                        n4.requestFocus();
                        return true; // Indica que el evento fue manejado
                    }

                }
                return false; // Permite que otros eventos se procesen normalmente
            }
        });
        ///////

        n1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Este método se llama antes de que el texto cambie
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!n1.getText().toString().isEmpty()){
                    n2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        n2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Este método se llama antes de que el texto cambie
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!n2.getText().toString().isEmpty()){
                    n3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        n3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Este método se llama antes de que el texto cambie
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!n3.getText().toString().isEmpty()){
                    n4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        n4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Este método se llama antes de que el texto cambie
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!n4.getText().toString().isEmpty()){
                    n5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        n5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Este método se llama antes de que el texto cambie
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        ///////
    }

    private void buscar(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        correousu = jsonObject.getString("correo");
                        //////////////////////////////
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        Properties properties = new Properties();
                        properties.put("mail.smtp.host", "smtp.googlemail.com");
                        properties.put("mail.smtp.socketFactory.port", "465");
                        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        properties.put("mail.smtp.auth", "true");
                        properties.put("mail.smtp.port", "465");

                        try {
                            session = Session.getDefaultInstance(properties, new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(correo, contra1);
                                }
                            });

                            if (session != null) {
                                Message message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(correo));
                                message.setSubject("Recuperar contraseña");
                                //aca la variable del codigo
                                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correousu));
                                //aca un codigo
                                codigo();
                                message.setContent(codigo5, "text/html; charset=utf-8");
                                Transport.send(message);
                                Toast.makeText(ACT_AD_RecuperarPass.this, "Correo Enviado", Toast.LENGTH_SHORT).show();
                                titulo.setVisibility(View.VISIBLE);
                                n1.setVisibility(View.VISIBLE);
                                n2.setVisibility(View.VISIBLE);
                                n3.setVisibility(View.VISIBLE);
                                n4.setVisibility(View.VISIBLE);
                                n5.setVisibility(View.VISIBLE);
                                confcodigo.setVisibility(View.VISIBLE);
                                /*
                                nuevacontra.setVisibility(View.VISIBLE);
                                pass.setVisibility(View.VISIBLE);
                                */

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ///////////////////////////
                    } catch (JSONException e) {
                        Toast.makeText(ACT_AD_RecuperarPass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//catch
                }//for
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_AD_RecuperarPass.this, "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    public void confirmar(){
        StringBuilder codig5 = new StringBuilder();
        codig5.append(n1.getText().toString());
        codig5.append(n2.getText().toString());
        codig5.append(n3.getText().toString());
        codig5.append(n4.getText().toString());
        codig5.append(n5.getText().toString());
        String cod5 = codig5.toString();
        if(cod5.equals(codigo5)){
            nuevacontra.setVisibility(View.VISIBLE);
            pass.setVisibility(View.VISIBLE);
            cambiarpass.setVisibility(View.VISIBLE);
            }else{
                Toast.makeText(ACT_AD_RecuperarPass.this, "CÓDIGO INCORRECTO", Toast.LENGTH_SHORT).show();
            }


    }
    public void cambiarpass(String URL) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ACT_AD_RecuperarPass.this, "CONTRASEÑA CAMBIADA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ACT_LobbyAdmin.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ACT_AD_RecuperarPass.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros= new HashMap<String,String>();
                parametros.put("usuario",usuario.getText().toString());
                parametros.put("pass",pass.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void codigo() {
        int[] numeros = new int[5];
        Random random = new Random();

        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = random.nextInt(10); // Genera número aleatorio del 0 al 9
        }

        StringBuilder resultado = new StringBuilder();
        for (int num : numeros) {
            resultado.append(num);
        }
        codigo5=resultado.toString();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuestudiantes,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.regresar) {
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Intent w = new Intent(this,ACT_LogginAdmin.class);
            startActivity(w);
        }
        return super.onOptionsItemSelected(item);

    }
    public void cambio (){
        Intent w = new Intent(this,ACT_LogginAdmin.class);
        startActivity(w);
    }
}