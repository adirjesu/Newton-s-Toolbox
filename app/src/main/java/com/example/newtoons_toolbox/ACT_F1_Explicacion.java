package com.example.newtoons_toolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ACT_F1_LH_explicacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_f1_lh_explicacion);
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
            Intent w =  new Intent(this,ACT_F1_Leydehooke.class);
            startActivity(w);
        }



        return super.onOptionsItemSelected(item);
    }
}