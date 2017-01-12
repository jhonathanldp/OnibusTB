package com.celusoftwares.onibustb.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.celusoftwares.onibustb.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import BancoDeDados.DataBase;
import Models.Horario;
import adapter.AdapterHorarios;

/**
 * Created by jhona on 08/01/2017.
 */

public class HorariosActivity extends Activity implements AdapterView.OnItemClickListener {
    private DataBase dataBase;
    private AdapterHorarios adapterHorarios;
    private List<Horario> horarioList;
    private ListView listView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horarios);

        Log.println(Log.INFO, "teste", "teste");

        dataBase = new DataBase(this);
        listView = (ListView) findViewById(R.id.lista_horarios);
        spinner = (Spinner) findViewById(R.id.selectTipoHorario);

        File dataChecker = getApplicationContext().getDatabasePath(dataBase.DBNOME);

        if(!dataChecker.exists()){
            dataBase.getReadableDatabase();

            if(copyDatabase(this)){
                Toast.makeText(this, "Banco copiado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Hmm, ocoreu um erro :(", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        ArrayAdapter<CharSequence> arrayAdapter =  ArrayAdapter.createFromResource(this, R.array.selecaoTipoHorario, R.layout.list_item_spinner);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        String[] parametro = new String[]{getIntent().getStringExtra("id_regiao")};

        horarioList = dataBase.listarHorarios(parametro);
        Log.println(Log.INFO, "teste", String.valueOf(horarioList.size()));
        adapterHorarios = new AdapterHorarios(this, horarioList);

        listView.setAdapter(adapterHorarios);
        Log.println(Log.INFO, "teste", "teste3");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private boolean copyDatabase(Context context){
        try {
            InputStream inputStream = context.getAssets().open(dataBase.DBNOME);
            String outFileName = dataBase.DBLOCATION + dataBase.DBNOME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int tamanho = 0;

            while ((tamanho = inputStream.read(buff)) > 0){
                outputStream.write(buff,0, tamanho);
            }

            outputStream.flush();
            outputStream.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Isso é constrangedor, mas encontramos um problema :(", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void favoritoChecked(View v){
        boolean checked = ((CheckBox)v).isChecked();

        if (checked){
            ((CheckBox) v).setBackgroundResource(R.drawable.ic_star_black_24dp);
        }
        else if (!checked){
            ((CheckBox) v).setBackgroundResource(R.drawable.ic_star_border_black_24dp);
        }
    }
}
