package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ACT_F1_banco_formulas extends AppCompatActivity {
    private Button f1;
    private Button f2;
    private Button f3;
    private Button f4;
    private Button f5;
    private Button f6;
    private Button f7;
    private Button f8;
    private Button f9;
    private Button f10;
    private Button f11;
    public static int tema_elegido=0;

    private TextView[][] tfArrays;

    public static String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_banco_formulas);
        f1          =findViewById(R.id.btnf1);
        tfArrays = new TextView[16][5];

        Bundle recibeDatos = getIntent().getExtras();
        tema_elegido = recibeDatos.getInt("tema");
        //OPIO SON 15 ELEMENTOS



        for (int a = 1; a < 16; a++) {
            for (int i = 1; i < 5; i++) {
                String idName = "txtf" + a + "_" + i;
                int resID = getResources().getIdentifier(idName, "id", getPackageName());
                tfArrays[a][i] = findViewById(resID);
            }
        }
        for (int a = 1; a < 12; a++) {
            for (int i = 1; i < 5; i++) {
                tfArrays[a][i].setVisibility(View.GONE);
            }
        }


    }//main

    public void ocAbrirf1(View view){
        abrir("tf1");
    }
    public void ocAbrirf2(View view){
        abrir("tf2");
    }
    public void ocAbrirf3(View view){
        abrir("tf3");
    }
    public void ocAbrirf4(View view){
        abrir("tf4");
    }
    public void ocAbrirf5(View view){
        abrir("tf5");
    }
    public void ocAbrirf6(View view){
        abrir("tf6");
    }
    public void ocAbrirf7(View view){
        abrir("tf7");
    }
    public void ocAbrirf8(View view){
        abrir("tf8");
    }
    public void ocAbrirf9(View view){
        abrir("tf9");
    }
    public void ocAbrirf10(View view){
        abrir("tf10");
    }
    public void ocAbrirf11(View view){
        abrir("tf11");
    }
    public void abrir(String nombre) {
        // Ocultar todos los TextViews antes de mostrar el seleccionado
        for (int a = 1; a < 16; a++) {
            for (int i = 1; i < 5; i++) {
                tfArrays[a][i].setVisibility(View.GONE);
            }
        }
        // Mostrar el TextView correspondiente basado en el nombre
        switch (nombre) {
            case "tf1":
                if(tfArrays[1][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[1][i].setVisibility(View.GONE);
                    }
                }
                else{
                    for (int i = 1; i < 5; i++) {
                        tfArrays[1][i].setVisibility(View.VISIBLE);
                    }
                }

                break;

            case "tf2":
                if(tfArrays[2][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[2][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[2][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf3":
                if(tfArrays[3][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[3][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[3][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf4":
                if(tfArrays[4][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[4][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[4][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf5":
                if(tfArrays[5][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[5][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[5][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf6":
                if(tfArrays[6][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[6][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[6][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf7":
                if(tfArrays[7][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[7][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[7][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf8":
                if(tfArrays[8][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[8][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[8][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf9":
                if(tfArrays[9][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[9][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[9][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf10":
                if(tfArrays[10][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[10][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[10][i].setVisibility(View.VISIBLE);
                    }
                }
                break;

            case "tf11":
                if(tfArrays[11][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[11][i].setVisibility(View.GONE);
                    }
                }
                else {
                    for (int i = 1; i < 5; i++) {
                        tfArrays[11][i].setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "tf12":
                if(tfArrays[12][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[12][i].setVisibility(View.GONE);
                    }
                }
                else{
                    for (int i = 1; i < 5; i++) {
                        tfArrays[12][i].setVisibility(View.VISIBLE);
                    }
                }

                break;
            case "tf13":
                if(tfArrays[13][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[13][i].setVisibility(View.GONE);
                    }
                }
                else{
                    for (int i = 1; i < 5; i++) {
                        tfArrays[13][i].setVisibility(View.VISIBLE);
                    }
                }

                break;
            case "tf14":
                if(tfArrays[14][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[14][i].setVisibility(View.GONE);
                    }
                }
                else{
                    for (int i = 1; i < 5; i++) {
                        tfArrays[14][i].setVisibility(View.VISIBLE);
                    }
                }

                break;
            case "tf15":
                if(tfArrays[15][1].getVisibility()==View.VISIBLE){
                    for (int i = 1; i < 5; i++) {
                        tfArrays[15][i].setVisibility(View.GONE);
                    }
                }
                else{
                    for (int i = 1; i < 5; i++) {
                        tfArrays[15][i].setVisibility(View.VISIBLE);
                    }
                }

                break;
            default:
                // Manejar el caso en que no se encuentra el nombre
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.regresar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id=item.getItemId();
        if(id==R.id.regresar){
            Toast.makeText(this, "Regresando",
                    Toast.LENGTH_SHORT).show();
            Bundle enviarDatos = new Bundle();
            enviarDatos.putInt("tema",tema_elegido);
            Intent w =  new Intent(this, ACT_F1_TemaLobby.class);
            w.putExtras(enviarDatos);
            startActivity(w);
        }



        return super.onOptionsItemSelected(item);
    }
}