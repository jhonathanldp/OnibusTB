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
 * Created by Jhonathan on 08/01/2017.
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
        View v;
        ViewHolder viewHolder;
        if (convertView == null) {
            v = View.inflate(mContext, R.layout.list_item_horarios, null);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.horario.setText(mHorarioList.get(position).getHorario());
        if (horariosVolta) {
            viewHolder.nomeRegiaoRetorno.setText(mHorarioList.get(position).getRegiao_nome());
            viewHolder.nomeRegiaoIda.setText(R.string.centro);
        } else{
            viewHolder.nomeRegiaoIda.setText(mHorarioList.get(position).getRegiao_nome());
            viewHolder.nomeRegiaoRetorno.setText(R.string.centro);
        }

        viewHolder.rota.setText(mHorarioList.get(position).getInfoAdicional());
        //Log.d("horario", mHorarioList.get(position).getHorario());

        if (mHorarioList.get(position).getId_linha() == 1) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.limao));
        } else if (mHorarioList.get(position).getId_linha() == 2) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.verdeClaro));
        } else if (mHorarioList.get(position).getId_linha() == 3) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        } else if (mHorarioList.get(position).getId_linha() == 4) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else if (mHorarioList.get(position).getId_linha() == 5) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        } else if (mHorarioList.get(position).getId_linha() == 6) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.roxo));
        } else if (mHorarioList.get(position).getId_linha() == 7) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.roxo_claro));
        } else if (mHorarioList.get(position).getId_linha() == 8) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.azul_claro));
        } else if (mHorarioList.get(position).getId_linha() == 9) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cyan));
        } else if (mHorarioList.get(position).getId_linha() == 10) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.cyan_escuro));
        } else if (mHorarioList.get(position).getId_linha() == 11) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.ceu));
        } else if (mHorarioList.get(position).getId_linha() == 12) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.marrom));
        } else if (mHorarioList.get(position).getId_linha() == 13) {
            viewHolder.corLinha.setBackgroundColor(ContextCompat.getColor(mContext, R.color.pink));
        }

        return v;
    }

    private static class ViewHolder {
        TextView nomeRegiaoIda;
        TextView nomeRegiaoRetorno;
        TextView horario;
        TextView rota;
        View corLinha;

        ViewHolder(View view) {
            nomeRegiaoIda = (TextView) view.findViewById(R.id.regiaoNome);
            nomeRegiaoRetorno = (TextView) view.findViewById(R.id.regiaoNome2);
            horario = (TextView) view.findViewById(R.id.horario);
            rota = (TextView) view.findViewById(R.id.rota);
            corLinha = view.findViewById(R.id.corLinha);
        }

    }
}
