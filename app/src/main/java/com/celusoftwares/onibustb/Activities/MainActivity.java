package com.celusoftwares.onibustb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.celusoftwares.onibustb.R;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Jhonathan on 27/12/2016.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
        setTitle("");
        setContentView(R.layout.tela_principal);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sobre, menu);
        return true;
    }

    public void clickContato(MenuItem menu) {
        startActivity(new Intent(this, ContatoActivity.class));
    }

    //inicia a tela regioes
    public void telaRegioes(View view) {
        if (view.getId() == R.id.horarioUrbano) {
            Intent intent = new Intent(this, BairrosActivity.class);
            intent.putExtra("favorito", "false");
            startActivity(intent);
        } else if (view.getId() == R.id.botaoFavoritos) {
            Intent intent = new Intent(this, HorariosActivity.class);
            intent.putExtra("favorito", "true");
            startActivity(intent);
        }

    }
}
