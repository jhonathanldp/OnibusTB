package BancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import Models.Horario;
import Models.Regioes;
import Models.TipoHorario;

/**
 * Created by jhona on 30/12/2016.
 * Responsavel por carregar os dados do Banco SQLite3
 * O banco fica localizado na pasta assets
 */

public class DataBase extends SQLiteAssetHelper {
    private static final String DBNOME = "onibustb.db";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static final int DBVERSION = 5;

    public DataBase(Context context) {
        super(context, DBNOME, null, DBVERSION);
        this.mContext = context;
        setForcedUpgrade();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        /*if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }*/
        mDatabase = getWritableDatabase();
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Regioes> listarRegioes() {
        Regioes regioes;
        List<Regioes> listRegioes = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM BAIRROS", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            regioes = new Regioes(cursor.getInt(0), cursor.getString(1));
            listRegioes.add(regioes);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return listRegioes;
    }

    public boolean adicionarFavoritoDAO(int idHorario) {
        final String tbName = "HORARIOS";
        String sql = "SELECT favorito FROM HORARIOS WHERE id_horario = ?";
        String[] args = {String.valueOf(idHorario)};
        openDatabase();
        Cursor cursor = mDatabase.rawQuery(sql, args);
        cursor.moveToFirst();
        boolean isFavorite;

        isFavorite = cursor.getInt(0) != 0;

        cursor.close();
        ContentValues contentValues = new ContentValues();
        if (!isFavorite) {
            contentValues.put("favorito", 1);
            mDatabase.update(tbName, contentValues, "id_horario = ?", args);
            mDatabase.close();
            return true;
        } else{
            contentValues.put("favorito", 0);
            mDatabase.update(tbName, contentValues, "id_horario = ?", args);
            mDatabase.close();
            return false;
        }

    }

    public List<Horario> listarHorarios(String[] argumentos) {
        Horario horario;
        List<Horario> horarioList = new ArrayList<>();
        openDatabase();

        String sql = "SELECT * FROM HORARIOS h JOIN BAIRROS b ON h.id_regiao = b.id_regiao JOIN LINHAS l ON h.id_linhas = l.id_linhas " +
                "AND h.id_regiao = ? " +
                "AND h.id_tipoHorario = ? " +
                "AND h.retorno = ?";

        Cursor cursor = mDatabase.rawQuery(sql, argumentos);
        cursor.moveToFirst();

        //Verifique a estrutura do Banco para entender essa parte do código
        while (!cursor.isAfterLast()) {
            horario = new Horario(cursor.getInt(0), cursor.getInt(1) != 0, cursor.getString(2), cursor.getInt(3),
                    cursor.getInt(4), cursor.getInt(5), cursor.getString(8), cursor.getString(10));

            horarioList.add(horario);
            cursor.moveToNext();
        }

        cursor.close();

        return horarioList;
    }

    public List<Horario> listarFavoritos(){
        Horario horario;
        List<Horario> horarioList = new ArrayList<>();
        openDatabase();

        String sql = "SELECT * FROM HORARIOS h JOIN BAIRROS b ON h.id_regiao = b.id_regiao JOIN LINHAS l ON h.id_linhas = l.id_linhas" +
                " AND h.favorito = ?";
        String args[] = {"1"}; // No SQLite boolean é validado por 1 para true e 0 para false

        Cursor cursor = mDatabase.rawQuery(sql, args);
        cursor.moveToFirst();

        //Verifique a estrutura do Banco para entender essa parte do código
        while (!cursor.isAfterLast()) {
            horario = new Horario(cursor.getInt(0), cursor.getInt(1) != 0, cursor.getString(2), cursor.getInt(3),
                    cursor.getInt(4), cursor.getInt(5), cursor.getString(8), cursor.getString(10));

            horarioList.add(horario);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();

        return horarioList;
    }

    public List<TipoHorario> listarTipoHorario(){
        List<TipoHorario> tipoHorarioList = new ArrayList<>();
        TipoHorario tipoHorario;

        openDatabase();

        String sql = "SELECT * FROM TIPODEHORARIO";

        Cursor cursor = mDatabase.rawQuery(sql, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            tipoHorario = new TipoHorario(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            tipoHorarioList.add(tipoHorario);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();

        return tipoHorarioList;

    }

}
