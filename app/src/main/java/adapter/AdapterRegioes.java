package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.celusoftwares.onibustb.R;

import java.util.List;

import Models.Regioes;

/**
 * Created by Jhonathan on 30/12/2016.
 */

public class AdapterRegioes extends BaseAdapter {
    private Context mContext;
    private List<Regioes> mListaRegioes;

    public AdapterRegioes(Context mContext, List<Regioes> mListaRegioes) {
        this.mContext = mContext;
        this.mListaRegioes = mListaRegioes;
    }

    @Override
    public int getCount() {
        return mListaRegioes.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaRegioes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListaRegioes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null){
            v = View.inflate(mContext, R.layout.list_item_bairros, null);
        }
        else {
            v = convertView;
        }

        TextView regiao = (TextView) v.findViewById(R.id.regiao);
        regiao.setText(mListaRegioes.get(position).getRegiao());

        return v;
    }
}
