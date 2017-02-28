package com.celusoftwares.onibustb.Activities;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import Fragment.CustomDialogFragment;
import Models.Horario;
import adapter.AdapterHorarios;

/**
 * Created by Jhonathan on 08/01/2017.
 */

public class HorariosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, CustomDialogFragment.NoticeDialogListener {

    private DataBase dataBase;
    private List<Horario> horarioList;
    private ListView listView;
    private Spinner spinner;
    private int idHorario;
    private ProgressBar progressBar;
    private CarregadorDeHorarios carregadorDeHorarios;
    private ContentValues contentValues;
    private String tipoHorarioSelecionado;
    private CustomDialogFragment customDialogFragment;
    private String favoritoPress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horarios);

        setTitle(R.string.nome_titulo);


        dataBase = new DataBase(this);
        listView = (ListView) findViewById(R.id.lista_horarios);
        spinner = (Spinner) findViewById(R.id.selectTipoHorario);


        List<String> listSpinner = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.selecaoTipoHorario)));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item_spinner, listSpinner);

        spinner.setAdapter(arrayAdapter);

        contentValues = new ContentValues();

        progressBar = (ProgressBar) findViewById(R.id.barraProgressoHorario);

        carregadorDeHorarios = new CarregadorDeHorarios(progressBar, dataBase, this, this);

        favoritoPress = getIntent().getStringExtra("favorito");

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

    private void abrirDialogo(){
        customDialogFragment = new CustomDialogFragment();
        if ("true".equals(favoritoPress)){
            customDialogFragment.show(getFragmentManager(),"DialogFavoritoRemove");
        }else {
            customDialogFragment.show(getFragmentManager(),"DialogFavoritoAdd");
        }

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
            recarregarItens();
        }
        customDialogFragment.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idHorario = horarioList.get(position).getId_horario();
        abrirDialogo();
    }

    private void recarregarItens() {
        carregadorDeHorarios = new CarregadorDeHorarios(progressBar, dataBase, this, this);
        carregadorDeHorarios.execute(contentValues);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        recarregarItens();
        dialog.dismiss();
    }
}
