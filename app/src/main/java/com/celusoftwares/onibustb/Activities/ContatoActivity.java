package com.celusoftwares.onibustb.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.celusoftwares.onibustb.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by jhona on 14/01/2017.
 */

public class ContatoActivity extends AppCompatActivity {
    private final String [] e_mail = {"jhonathanldp@outlook.com"};
    private final String subject = "Sugestões/Criticas";
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setElevation(0);
        }

        setTitle("Sobre");

        setContentView(R.layout.contato);

        shareDialog = new ShareDialog(this);
        CallbackManager callbackManager = CallbackManager.Factory.create();
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {
                Toast.makeText(ContatoActivity.this, "Aparentemente você não possui, o facebook instalado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(ContatoActivity.this, "Aparentemente você não possui, o facebook instalado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onImageClick(View view){
        if(view.getId() == R.id.botaoGit){
            String url = "https://github.com/jhonathanldp/OnibusTB";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
        else if (view.getId() == R.id.mailContato){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, e_mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
        else if (view.getId() == R.id.compartilheCoracao){
            Log.v("Facebook", "clickado");
            String url = "www.google.com.br";
            ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse(url)).build();
            shareDialog.show(shareLinkContent);
        }
    }
}
