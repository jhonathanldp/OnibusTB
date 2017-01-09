package adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.celusoftwares.onibustb.R;

import java.util.List;

import Models.Horario;

/**
 * Created by jhona on 08/01/2017.
 */

public class AdapterHorarios extends BaseAdapter {
    private Context mContext;
    private List<Horario> mHorarioList;

    public AdapterHorarios(Context mContext, List<Horario> mHorarioList) {
        this.mContext = mContext;
        this.mHorarioList = mHorarioList;
    }

    @Override
    public int getCount() {
        return mHorarioList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHorarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mHorarioList.get(position).getId_horario();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.list_item_horarios, null);

        TextView textView = (TextView) v.findViewById(R.id.regiaoNome);
        textView.setText(mHorarioList.get(position).getRegiao_nome());

        textView = (TextView) v.findViewById(R.id.horarioIda);
        textView.setText(mHorarioList.get(position).getHorario_ida());

        textView = (TextView) v.findViewById(R.id.horarioVolta);
        textView.setText(mHorarioList.get(position).getHorario_volta());

        textView = (TextView) v.findViewById(R.id.regiaoNomeVolta);
        textView.setText(mHorarioList.get(position).getRegiao_nome());

        View colorView = (View) v.findViewById(R.id.corLinha);
        if(mHorarioList.get(position).getId_linha() == 1){
            colorView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.limao));
        }
        else if (mHorarioList.get(position).getId_linha() == 2){
            colorView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.verdeClaro));
        }

        return v;
    }
}
