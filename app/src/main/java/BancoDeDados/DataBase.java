package BancoDeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Models.Horario;
import Models.Regioes;

/**
 * Created by jhona on 30/12/2016.
 * Responsavel por carregar os dados do Banco SQLite3
 */

public class DataBase extends SQLiteOpenHelper {
    public static final String DBNOME = "onibustb.sqlite3";
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

    public List<Horario> listarHorarios(String [] argumentos){
        Horario horario = null;
        List<Horario> horarioList = new ArrayList<>();
        openDatabase();
        String sql = "SELECT * FROM HORARIOS h JOIN BAIRROS b ON h.id_regiao = b.id_regiao AND h.id_regiao = ?";
        String [] selectionArgs = {"1"};
        Cursor cursor = mDatabase.rawQuery(sql, argumentos);
        cursor.moveToFirst();

        if (cursor.getCount() == 0){
            Log.println(Log.ERROR,"dados", "teste342432");
        }

        while (!cursor.isAfterLast()){
            horario = new Horario(cursor.getInt(0), cursor.getInt(1) !=0, cursor.getString(2), cursor.getString(3),
                    cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getString(8));

            horarioList.add(horario);

            Log.println(Log.INFO,"dados", cursor.getString(7));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return horarioList;

    }
}
