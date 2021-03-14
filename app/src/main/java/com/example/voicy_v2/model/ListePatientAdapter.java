package com.example.voicy_v2.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.voicy_v2.R;

import java.util.List;

public class ListePatientAdapter extends BaseAdapter {

    private List<Patient> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListePatientAdapter(Context aContext,  List<Patient> listData) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_liste_patient, null);
            holder = new ViewHolder();
            holder.liste_patients_id = (TextView) convertView.findViewById(R.id.liste_patients_id);
            holder.liste_patients_genre = (TextView) convertView.findViewById(R.id.liste_patients_genre);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Patient patient = this.listData.get(position);
        holder.liste_patients_id.setText(patient.getId());
        holder.liste_patients_genre.setText(patient.getGenre());

        return convertView;
    }

    static class ViewHolder {
        TextView liste_patients_id;
        TextView liste_patients_genre;
    }
}
