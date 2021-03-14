package com.example.voicy_v2.activity;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.ListePatientAdapter;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.util.List;

public class ListePatientActivity extends FonctionnaliteActivity {

    public static VoicyDbHelper dbPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_liste_patient);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate of ListePatientActivity !
        View contentView = inflater.inflate(R.layout.activity_liste_patient, null, false);
        drawerLayout.addView(contentView, 0);

        dbPatient = new VoicyDbHelper(this);
        List<Patient> listePatients = dbPatient.getAllPatient();
        final ListView listView = (ListView) findViewById(R.id.list_patients);
        listView.setAdapter(new ListePatientAdapter(this, listePatients));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Patient patient = (Patient) o;
                Toast.makeText(ListePatientActivity.this, "Selected :" + " " + patient.getId(), Toast.LENGTH_LONG).show();
            }
        });
    }
}