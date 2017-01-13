package com.celusoftwares.onibustb.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.celusoftwares.onibustb.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import BancoDeDados.DataBase;
import Models.Regioes;
import adapter.AdapterRegioes;

/**
 * Created by jhona on 28/12/2016.
 * Essa atividade é Responsavel por carregar e listar os bairros/regiões de telemaco
 * que possuam horarios disponiveis
 */

public class BairrosActivity extends Activity implements AdapterView.OnItemClickListener {
    private DataBase dataBase;
    private AdapterRegioes adapterRegioes;
    private List<Regioes> listRegioes;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regioes);

        dataBase = new DataBase(this);
        listView = (ListView) findViewById(R.id.lista_regioes);
        // verificar se ja existe banco
        /*File databaseChecker = getApplicationContext().getDatabasePath(dataBase.DBNOME);
        if (true == databaseChecker.exists()) {
            dataBase.getReadableDatabase();
            //copia banco
            if (copiarDatabase(this)) {
                Toast.makeText(this, "Banco copiado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ops, isso é contrangedor, mas ocorreu um erro 2 :(", Toast.LENGTH_SHORT).show();
                return;
            }
        }*/

        //Iniciar procedimento para carregar lista

        listRegioes = dataBase.listarRegioes();

        adapterRegioes = new AdapterRegioes(this, listRegioes);

        listView.setAdapter(adapterRegioes);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Regioes regioes = (Regioes) listView.getItemAtPosition(position);
        Intent intent = new Intent(this, HorariosActivity.class);
        intent.putExtra("id_regiao", String.valueOf(regioes.getId()));
        startActivity(intent);
    }

   /* private boolean copiarDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(dataBase.DBNOME);
            String outFileName = dataBase.DBLOCATION + dataBase.DBNOME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int tamanho = 0;

            while ((tamanho = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, tamanho); // copiando banco de dados ...
            }
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Ops, isso é contrangedor, mas ocorreu um erro 1 :(", Toast.LENGTH_SHORT).show();
            return false;
        }
    }*/
}
