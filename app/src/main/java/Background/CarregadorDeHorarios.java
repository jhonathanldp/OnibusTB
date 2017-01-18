package Background;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.celusoftwares.onibustb.Activities.HorariosActivity;
import com.celusoftwares.onibustb.R;

import java.util.List;

import BancoDeDados.DataBase;
import Models.Horario;

/**
 * Created by jhona on 17/01/2017.
 */

public class CarregadorDeHorarios extends AsyncTask<ContentValues, Integer, List<Horario>> {
    private ProgressBar progressBar;
    private DataBase dataBase;
    private Context context;
    private HorariosActivity horariosActivity;
    private List<Horario> horarioList;

    public CarregadorDeHorarios(ProgressBar progressBar, DataBase dataBase, Context context, HorariosActivity horariosActivity) {
        this.progressBar = progressBar;
        this.dataBase = dataBase;
        this.context = context;
        this.horariosActivity = horariosActivity;
    }

    @Override
    protected void onPreExecute() {
        dataBase = new DataBase(context);
        progressBar.setMax(100);

    }

    @Override
    protected List<Horario> doInBackground(ContentValues... params) {
        publishProgress(25);
        String collumName = params[0].getAsString("collum");
        String[] args = {params[0].getAsString("parametro")};
        publishProgress(50);
        horarioList = dataBase.listarHorarios(args, collumName);
        publishProgress(100);
        return horarioList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(List<Horario> horarios) {
        horariosActivity.callbackCarregarHorarios(horarios);
        progressBar.setVisibility(View.GONE);
    }
}
