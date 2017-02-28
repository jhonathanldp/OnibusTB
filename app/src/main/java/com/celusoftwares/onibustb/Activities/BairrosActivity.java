package com.celusoftwares.onibustb.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class BairrosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private AdapterRegioes adapterRegioes;
    private ListView listView;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regioes);

        setTitle(R.string.nome_titulo);

        listView = (ListView) findViewById(R.id.lista_regioes);

        new CarregarConteudo(this).execute();

        this.registerForContextMenu(listView);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Regioes regioes = (Regioes) listView.getItemAtPosition(position);
        intent = new Intent(this, HorariosActivity.class);
        intent.putExtra("id_regiao", String.valueOf(regioes.getId()));
        view.showContextMenu();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_regioes, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.horario_ida:
                intent.putExtra("modoHorario", false);
                startActivity(intent);
                break;
            case R.id.horario_volta:
                intent.putExtra("modoHorario", true);
                startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private class CarregarConteudo extends AsyncTask<Void, Integer, List<Regioes>> {
        private ProgressBar progressBar;
        private DataBase dataBase;
        private Context context;

        CarregarConteudo(Context context) {
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
            List<Regioes> listRegioes = dataBase.listarRegioes();
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
