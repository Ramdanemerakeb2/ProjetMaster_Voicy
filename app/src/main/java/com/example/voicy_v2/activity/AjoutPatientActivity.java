package com.example.voicy_v2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

public class AjoutPatientActivity extends AppCompatActivity  {
    private Patient patient;
    public static VoicyDbHelper patientDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_patient);

        patientDbHelper = new VoicyDbHelper(this);

        final TextView idPatient = (TextView) findViewById(R.id.idPatient);
        final TextView genrePatient = (TextView) findViewById(R.id.genre);
        final TextView commentairePatient = (TextView) findViewById(R.id.commentaire);
        final TextView btnAjout = (TextView) findViewById(R.id.btnAjout);

        btnAjout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!((idPatient.getText().toString().isEmpty())&&(genrePatient.getText().toString().isEmpty())&&(commentairePatient.getText().toString().isEmpty()))){
                    patient = new Patient(idPatient.getText().toString(),genrePatient.getText().toString(),commentairePatient.getText().toString());
                    if(patientDbHelper.ajoutPatient(patient)){
                        Toast.makeText(AjoutPatientActivity.this, patient.getId().toString()+" ajouté",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AjoutPatientActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });

    }
}