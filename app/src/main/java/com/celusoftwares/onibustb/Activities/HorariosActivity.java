package com.celusoftwares.onibustb.Activities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.celusoftwares.onibustb.R;

import java.util.List;

import Background.CarregadorDeHorarios;
import BancoDeDados.DataBase;
import Models.Horario;
import adapter.AdapterHorarios;

/**
 * Created by jhona on 08/01/2017.
 */

public class HorariosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DataBase dataBase;
    private AdapterHorarios adapterHorarios;
    private List<Horario> horarioList;
    private ListView listView;
    private Spinner spinner;
    private AlertDialog.Builder alertDialog;
    private int idHorario;
    private ProgressBar progressBar;
    private CarregadorDeHorarios carregadorDeHorarios;
    private ContentValues contentValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horarios);

        setTitle(R.string.nome_titulo);

        alertDialog = new AlertDialog.Builder(this);

        dataBase = new DataBase(this);
        listView = (ListView) findViewById(R.id.lista_horarios);
        spinner = (Spinner) findViewById(R.id.selectTipoHorario);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.selecaoTipoHorario, R.layout.list_item_spinner);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        contentValues = new ContentValues();

        progressBar = (ProgressBar) findViewById(R.id.barraProgressoHorario);

        carregadorDeHorarios = new CarregadorDeHorarios(progressBar, dataBase, this, this);

        String favoritoPress = getIntent().getStringExtra("favorito");

        if ("true".equals(favoritoPress)) {
            contentValues.put("parametro", "1");
            contentValues.put("collum", "favorito");
        } else {
            contentValues.put("parametro", getIntent().getStringExtra("id_regiao"));
            contentValues.put("collum", "id_regiao");
        }

        carregadorDeHorarios.execute(contentValues);

    }

    public void callbackCarregarHorarios(List<Horario> result) {
        horarioList = result;
        adapterHorarios = new AdapterHorarios(this, horarioList);

        if (listView.getCount() != 0) {
            adapterHorarios.notifyDataSetChanged();
            listView.setAdapter(adapterHorarios);
        } else {
            listView.setAdapter(adapterHorarios);

            listView.setOnItemClickListener(this);
        }

    }

    public void adicionarFavorito(View view) {
        if (dataBase.adicionarFavoritoDAO(idHorario)) {
            Toast.makeText(this, "Esse Horario acaba de ser adicionado aos Favoritos!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Esse Horario acaba de ser removido dos Favoritos!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LayoutInflater layoutInflater = getLayoutInflater();
        idHorario = horarioList.get(position).getId_horario();
        alertDialog.setView(layoutInflater.inflate(R.layout.dialog_favorito, parent, false));
        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recarregarItens();
                dialog.dismiss();
            }
        });
        alertDialog.create();
        alertDialog.show();
        Log.v("Teste", "DialogoFav list click");
    }

    public void recarregarItens() {
        carregadorDeHorarios = new CarregadorDeHorarios(progressBar, dataBase, this, this);
        carregadorDeHorarios.execute(contentValues);
    }
}
