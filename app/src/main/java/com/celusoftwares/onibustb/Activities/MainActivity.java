package com.celusoftwares.onibustb.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.celusoftwares.onibustb.R;

/**
 * Created by jhona on 27/12/2016.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(0);
        }
        setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sobre, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //inicia a tela regioes
    public void telaRegioes(View view){
        if (view.getId() == R.id.horarioUrbano){
            Intent  intent = new Intent(this, BairrosActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.horarioKlabin){
            Intent  intent = new Intent(this, BairrosActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.horarioIndustrial){
            Intent  intent = new Intent(this, BairrosActivity.class);
            startActivity(intent);
        }

    }
}
