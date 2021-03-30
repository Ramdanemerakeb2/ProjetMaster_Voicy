package com.example.voicy_v2.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.ListePatientAdapter;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListePatientActivity extends FonctionnaliteActivity {

    public static VoicyDbHelper dbPatient;
    SearchView searchView;
    ListView listView ;
    ListePatientAdapter adapter ;
    SharedPreferences sharedpreferences;
    List<Patient> listePatients ;

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

        searchView = (SearchView) findViewById(R.id.btnRechPatientId);

        sharedpreferences = getSharedPreferences(ConnexionActivity.clinicienSession, Context.MODE_PRIVATE);

        listePatients = dbPatient.getAllPatient(sharedpreferences.getString(ConnexionActivity.sessionIdClinicien, null));
        listView = (ListView) findViewById(R.id.list_patients);
        adapter = new ListePatientAdapter(this, listePatients);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Patient patient = (Patient) o;
                //Toast.makeText(ListePatientActivity.this, "Selected :" + " " + patient.getId(), Toast.LENGTH_LONG).show();

               /* String[] typeExo = {"phrase","logatome"};

                Random rand = new Random();

                // Obtain a number between [0 - 1].
                int n = rand.nextInt(2);


                Intent intent = new Intent(ListePatientActivity.this, ExerciceActivity.class);
                intent.putExtra("type", typeExo[n]);
                intent.putExtra("genre", ((Patient) o).getGenre());
                intent.putExtra("iteration", 1);
                startActivityForResult(intent, 0);*/
                Intent intent = new Intent(ListePatientActivity.this, InfosPatientActivity.class);
                intent.putExtra("idPatient", patient.getId().toString());
                startActivity(intent);
            }
        });

        //https://www.javatpoint.com/android-searchview
        //elle permet de filter la liste des patient selon l'id entrer par le clinicien
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ListePatientActivity.this.adapter.getFilter().filter(newText);
                return false;
            }
        });


    }
}