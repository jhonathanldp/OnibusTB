package com.celusoftwares.onibustb.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.celusoftwares.onibustb.R;

/**
 * Created by jhona on 27/12/2016.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
    }

    //inicia a tela regioes
    public void telaRegioes(View view){
        Intent  intent = new Intent(this, BairrosActivity.class);
        startActivity(intent);
    }
}
