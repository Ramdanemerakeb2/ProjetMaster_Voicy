package com.example.voicy_v2.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.voicy_v2.R;

import java.util.List;

public class ListSessionAdapter extends BaseAdapter {

    private List<SessionFile> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListSessionAdapter(Context aContext,  List<SessionFile> listData) {
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
        ListSessionAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_list_session, null);
            holder = new ListSessionAdapter.ViewHolder();
            holder.list_date_exo = (TextView) convertView.findViewById(R.id.list_date_exo);
            holder.list_heure_exo = (TextView) convertView.findViewById(R.id.list_heure_exo);
            holder.list_type_exo = (TextView) convertView.findViewById(R.id.list_type_exo);
            convertView.setTag(holder);
        } else {
            holder = (ListSessionAdapter.ViewHolder) convertView.getTag();
        }

        SessionFile session = this.listData.get(position);
        holder.list_date_exo.setText(session.getDate());
        holder.list_heure_exo.setText(session.getHour());
        holder.list_type_exo.setText(session.getType()+" "+session.getIdExo());

        return convertView;
    }



    static class ViewHolder {
        TextView list_date_exo;
        TextView list_heure_exo;
        TextView list_type_exo;
    }
}
