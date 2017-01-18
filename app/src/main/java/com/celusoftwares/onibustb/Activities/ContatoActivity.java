package com.celusoftwares.onibustb.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.celusoftwares.onibustb.R;

/**
 * Created by jhona on 14/01/2017.
 */

public class ContatoActivity extends AppCompatActivity {
    private TextView textView;
    private Typeface typeface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contato);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Sobre");

        textView = (TextView) findViewById(R.id.descricaoContato);
        textView.setTypeface(getFontFromAssets());

        textView = (TextView) findViewById(R.id.descricaoCriador);
        textView.setTypeface(getFontFromAssets());

        textView = (TextView) findViewById(R.id.descricaoCompartilhe);
        textView.setTypeface(getFontFromAssets());

        textView = (TextView) findViewById(R.id.descricaoFeedback);
        textView.setTypeface(getFontFromAssets());
    }

    public Typeface getFontFromAssets(){
        typeface = Typeface.createFromAsset(getAssets(), "ComingSoon.ttf");

        return typeface;
    }
}
