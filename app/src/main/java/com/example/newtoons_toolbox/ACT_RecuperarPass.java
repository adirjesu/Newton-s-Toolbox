package com.example.newtoons_toolbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ACT_RecuperarPass extends AppCompatActivity {
    private EditText usu;

    private SharedPreferences prefe1;
    public static String usuario;

    public static Intent w;
    String correo;
    String contra1;

    EditText mensaje;
    Button enviar;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_recuperar_pass);
        prefe1=getSharedPreferences("datosusuarios", Context.MODE_PRIVATE);
       // usu  = findViewById(R.id.etusuario2);
        mensaje = findViewById(R.id.etmensaje);
        enviar=findViewById(R.id.btnenviar);


        correo="newtonstoolbox@gmail.com";
        contra1="icuu usyt bvvm vqmy";
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Properties properties= new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.port","465");

                try {
                    session=Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contra1);
                        }
                    });

                    if(session!=null){
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("espero que funcie");
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(""));
                        message.setContent(mensaje.getText().toString(),"text/html; charset=utf-8");
                        Transport.send(message);
                        Toast.makeText(ACT_RecuperarPass.this, "Correo Enviado", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        mensaje.setText("else");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


    }























    public void ocCambiar(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Introduce tu pin secreto");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Botón OK
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String pin = input.getText().toString();
                Map<String, ?> claves = prefe1.getAll();
                usuario= String.valueOf(usu.getText());
                String nose = usuario + "c";

                // Verificar si el pin ingresado es correcto
                if (claves.containsKey(nose) && pin.equals(claves.get(nose).toString())) {
                    // Si el pin es correcto, mostrar el siguiente diálogo
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ACT_RecuperarPass.this);
                    builder1.setTitle("Introduce tu nueva constraseña");

                    final EditText input1 = new EditText( ACT_RecuperarPass.this);
                    input1.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder1.setView(input1);

                    // Botón para aceptar el nuevo mensaje
                    builder1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String Nuevacontra = input1.getText().toString();
                            // Guardar el nuevo valor en SharedPreferences
                            SharedPreferences.Editor elemento = prefe1.edit();
                            elemento.putString(usuario, Nuevacontra);
                            elemento.commit();
                            Toast.makeText(getApplicationContext(), "Contraseña cambiada: ", Toast.LENGTH_SHORT).show();
                            cambio();
                        }
                    });

                    // Botón para cancelar
                    builder1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    // Mostrar el segundo diálogo
                    builder1.show();
                } else {
                    Toast.makeText(getApplicationContext(), "PIN incorrecto:" + claves.get(nose), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón Cancelar
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Mostrar el diálogo
        builder.show();
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