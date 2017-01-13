package com.celusoftwares.onibustb.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.celusoftwares.onibustb.R;

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
    private AdapterRegioes adapterRegioes;
    private List<Regioes> listRegioes;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regioes);

        listView = (ListView) findViewById(R.id.lista_regioes);

        new CarregarConteudo(this).execute();

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Regioes regioes = (Regioes) listView.getItemAtPosition(position);
        Intent intent = new Intent(this, HorariosActivity.class);
        intent.putExtra("id_regiao", String.valueOf(regioes.getId()));
        startActivity(intent);
    }

    private class CarregarConteudo extends AsyncTask<Void, Integer, List<Regioes>> {
        private ProgressBar progressBar;
        private DataBase dataBase;
        private Context context;

        public CarregarConteudo(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            dataBase = new DataBase(context);
            progressBar = (ProgressBar) findViewById(R.id.barraProgressoRegioes);
            progressBar.setMax(100);

        }

        @Override
        protected List<Regioes> doInBackground(Void... params) {
            publishProgress(25);
            listRegioes = dataBase.listarRegioes();
            publishProgress(75);
            adapterRegioes = new AdapterRegioes(context, listRegioes);
            publishProgress(100);
            return listRegioes;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(List<Regioes> regioes) {
            listView.setAdapter(adapterRegioes);
            progressBar.setVisibility(View.GONE);

        }
    }

}
