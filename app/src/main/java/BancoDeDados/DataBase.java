package BancoDeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Models.Regioes;

/**
 * Created by jhona on 30/12/2016.
 * Responsavel por carregar os dados do Banco SQLite3
 */

public class DataBase extends SQLiteOpenHelper {
    public static final String DBNOME = "onibustb.sqlite";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static final String DBLOCATION = "/data/data/com.celusoftwares.onibustb/databases/";

    public DataBase(Context context) {
        super(context, DBNOME, null, 1);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNOME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
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
}
