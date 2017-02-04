package com.celusoftwares.onibustb.Activities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.celusoftwares.onibustb.R;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<Horario> horarioList;
    private ListView listView;
    private Spinner spinner;
    private AlertDialog.Builder alertDialog;
    private int idHorario;
    private ProgressBar progressBar;
    private CarregadorDeHorarios carregadorDeHorarios;
    private ContentValues contentValues;
    private String tipoHorarioSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horarios);

        setTitle(R.string.nome_titulo);

        alertDialog = new AlertDialog.Builder(this);

        dataBase = new DataBase(this);
        listView = (ListView) findViewById(R.id.lista_horarios);
        spinner = (Spinner) findViewById(R.id.selectTipoHorario);


        List<String> listSpinner = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.selecaoTipoHorario)));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item_spinner, listSpinner);

        spinner.setAdapter(arrayAdapter);

        contentValues = new ContentValues();

        progressBar = (ProgressBar) findViewById(R.id.barraProgressoHorario);

        carregadorDeHorarios = new CarregadorDeHorarios(progressBar, dataBase, this, this);

        String favoritoPress = getIntent().getStringExtra("favorito");

        contentValues.put("parametroTipoHorario", "1");

        // Pega do usuario a seleção do comboBox
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoHorarioSelecionado = spinner.getSelectedItem().toString();
                switch (tipoHorarioSelecionado) {
                    case "Dias úteis":
                        contentValues.put("parametroTipoHorario", "1");
                        break;
                    case "Sábados":
                        contentValues.put("parametroTipoHorario", "6");
                        break;
                    case "Domingos/Feriados":
                        contentValues.put("parametroTipoHorario", "7");
                        break;
                }

                recarregarItens();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(1);
                contentValues.put("parametroTipoHorario", "7");
            }
        });

        if ("true".equals(favoritoPress)) {
            contentValues.put("favorito", true);
            spinner.setVisibility(View.GONE);
        } else {
            contentValues.put("favorito", false);
            contentValues.put("parametroBairro", getIntent().getStringExtra("id_regiao"));
            contentValues.put("isRetorno", getIntent().getBooleanExtra("modoHorario", false));
        }

        carregadorDeHorarios.execute(contentValues);

    }

    public void callbackCarregarHorarios(List<Horario> result) {
        horarioList = result;
        boolean modoHorario = getIntent().getBooleanExtra("modoHorario", false);
        AdapterHorarios adapterHorarios = new AdapterHorarios(this, horarioList, modoHorario);

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
    }

    public void recarregarItens() {
        carregadorDeHorarios = new CarregadorDeHorarios(progressBar, dataBase, this, this);
        carregadorDeHorarios.execute(contentValues);
    }
}
