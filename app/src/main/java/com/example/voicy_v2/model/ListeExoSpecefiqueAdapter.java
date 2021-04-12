package com.example.voicy_v2.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.voicy_v2.R;

import java.util.List;

public class ListeExoSpecefiqueAdapter extends BaseAdapter {

    private List<Exercice> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListeExoSpecefiqueAdapter(Context aContext,  List<Exercice> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListeExoSpecefiqueAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_list_exo_specefiques, null);
            holder = new ListeExoSpecefiqueAdapter.ViewHolder();
            holder.list_exo_id = (TextView) convertView.findViewById(R.id.list_exo_id);
            holder.list_exo_type = (TextView) convertView.findViewById(R.id.list_exo_type);
            convertView.setTag(holder);
        } else {
            holder = (ListeExoSpecefiqueAdapter.ViewHolder) convertView.getTag();
        }

        Exercice exercice = this.listData.get(position);
        holder.list_exo_id.setText("Exercice "+exercice.getIdDb());
        holder.list_exo_type.setText(exercice.getTypeExo()+" "+exercice.getId());

        return convertView;
    }



    static class ViewHolder {
        TextView list_exo_id;
        TextView list_exo_type;
    }
}
