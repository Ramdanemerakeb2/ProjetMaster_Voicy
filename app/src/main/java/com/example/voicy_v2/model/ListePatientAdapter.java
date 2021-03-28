package com.example.voicy_v2.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.voicy_v2.R;

import java.util.ArrayList;
import java.util.List;

public class ListePatientAdapter extends BaseAdapter implements Filterable {

    private List<Patient> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public static VoicyDbHelper dbPatient;

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
    //elle est utilser pour le filre de a liste des patient selon l'id enter par le clinicien
    //https://stackoverflow.com/questions/14663725/list-view-filter-android/14663821#14663821
    public Filter getFilter() {
        dbPatient = new VoicyDbHelper(this.context);
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listData = (List<Patient>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Patient> FilteredArrayNames = new ArrayList<Patient>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < dbPatient.getAllPatient().size(); i++) {
                    Patient dataId = dbPatient.getAllPatient().get(i);
                    if (dataId.getId().toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(dataId);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;

    }
}
