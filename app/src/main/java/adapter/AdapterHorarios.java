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
    private boolean horariosVolta;

    public AdapterHorarios(Context mContext, List<Horario> mHorarioList, boolean horariosVolta) {
        this.mContext = mContext;
        this.mHorarioList = mHorarioList;
        this.horariosVolta = horariosVolta;
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

        textView = (TextView) v.findViewById(R.id.horario);
        textView.setText(mHorarioList.get(position).getHorario());
        if (horariosVolta){
            textView = (TextView) v.findViewById(R.id.regiaoNome2);
            textView.setText(mHorarioList.get(position).getRegiao_nome());

            textView = (TextView) v.findViewById(R.id.regiaoNome);
            textView.setText(R.string.centro);
        }
        else if (!horariosVolta){
            textView = (TextView) v.findViewById(R.id.regiaoNome);
            textView.setText(mHorarioList.get(position).getRegiao_nome());

            textView = (TextView) v.findViewById(R.id.regiaoNome2);
            textView.setText(R.string.centro);
        }

        textView = (TextView) v.findViewById(R.id.rota);
        textView.setText(mHorarioList.get(position).getInfoAdicional());

        View colorView = v.findViewById(R.id.corLinha);
        if(mHorarioList.get(position).getId_linha() == 1){
            colorView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.limao));
        }
        else if (mHorarioList.get(position).getId_linha() == 2){
            colorView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.verdeClaro));
        }

        return v;
    }
}
