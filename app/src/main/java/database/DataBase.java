package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import models.Horario;
import models.Regioes;

/**
 * Created by jhona on 30/12/2016.
 * Responsavel por carregar os dados do Banco SQLite3
 * O banco fica localizado na pasta assets
 */

public class DataBase extends SQLiteAssetHelper {
    private static final String DBNOME = "onibustb.db";
    private static final int DBVERSION = 8;
    private SQLiteDatabase mDatabase;

    public DataBase(Context context) {
        super(context, DBNOME, null, DBVERSION);
        setForcedUpgrade();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void openDatabase() throws SQLiteAssetException {
        mDatabase = getWritableDatabase();
    }

    private void closeDatabase() {
        mDatabase.close();
    }

    public List<Regioes> listarRegioes() throws SQLException {
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

    public boolean adicionarFavoritoDAO(int idHorario) throws SQLException {
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
        } else {
            contentValues.put("favorito", 0);
            mDatabase.update(tbName, contentValues, "id_horario = ?", args);
            mDatabase.close();
            return false;
        }

    }

    public List<Horario> listarHorarios(String[] argumentos) throws SQLException {
        Horario horario;
        List<Horario> horarioList = new ArrayList<>();
        openDatabase();

        String sql = "SELECT * FROM HORARIOS h JOIN BAIRROS b ON h.id_regiao = b.id_regiao JOIN LINHAS l ON h.id_linhas = l.id_linhas " +
                "AND h.id_regiao = ? " +
                "AND h.id_tipoHorario = ? " +
                "AND h.retorno = ? " +
                "ORDER BY h.horario ASC";

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

    public List<Horario> listarFavoritos() {
        Horario horario;
        List<Horario> horarioList = new ArrayList<>();
        openDatabase();

        String sql = "SELECT * FROM HORARIOS h JOIN BAIRROS b ON h.id_regiao = b.id_regiao JOIN LINHAS l ON h.id_linhas = l.id_linhas" +
                " AND h.favorito = ? " +
                "ORDER BY h.horario ASC";
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

}
