package com.example.voicy_v2.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.io.Serializable;

public class AffichagePatientActivity extends AppCompatActivity implements Serializable {
    private Patient patient;
    public static VoicyDbHelper patientDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_patient);

        patientDbHelper = new VoicyDbHelper(this);

        final TextView idPatient = (TextView) findViewById(R.id.idPatient);
        final TextView genre = (TextView) findViewById(R.id.genre);
        final TextView commentaire = (TextView) findViewById(R.id.commentaire);
        final TextView btnsave = (TextView) findViewById(R.id.btnSave);

    }
}