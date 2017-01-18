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

/**
 * Created by jhona on 30/12/2016.
 * Responsavel por carregar os dados do Banco SQLite3
 */

public class DataBase extends SQLiteAssetHelper {
    private static final String DBNOME = "onibustb.db";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static final int DBVERSION = 1;

    public DataBase(Context context) {
        super(context, DBNOME, null, DBVERSION);
        this.mContext = context;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNOME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = getReadableDatabase();
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Regioes> listarRegioes() {
        Regioes regioes = null;
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
        if (cursor.getInt(0) != 0) {
            isFavorite = true;
        } else {
            isFavorite = false;
        }
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

    public List<Horario> listarHorarios(String[] argumentos, String collumName) {
        Horario horario = null;
        List<Horario> horarioList = new ArrayList<>();
        openDatabase();
        String sql = "SELECT * FROM HORARIOS h JOIN BAIRROS b ON h.id_regiao = b.id_regiao AND h." + collumName + " = ?";
        String[] selectionArgs = {"true"};
        Cursor cursor = mDatabase.rawQuery(sql, argumentos);
        cursor.moveToFirst();

        if (cursor.getCount() == 0) {
            Log.println(Log.ERROR, "dados", "teste342432");
        }

        while (!cursor.isAfterLast()) {
            horario = new Horario(cursor.getInt(0), cursor.getInt(1) != 0, cursor.getString(2), cursor.getString(3),
                    cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(8));

            horarioList.add(horario);

            Log.println(Log.INFO, "dados", cursor.getString(7));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return horarioList;

    }
}
