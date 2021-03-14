package com.example.voicy_v2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.io.Serializable;

public class RecherchePatientActivity extends AppCompatActivity implements Serializable {
    private Patient patient;
    public static VoicyDbHelper patientDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_patient);

        patientDbHelper = new VoicyDbHelper(this);

        final TextView idPatient = (TextView) findViewById(R.id.idRech);
        final TextView btnrech = (TextView) findViewById(R.id.btnRech);

        btnrech.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // clinicienSup = dbClinicien.getClinicien(idConnexion.getText().toString(),shalMdp) ;
                patient = patientDbHelper.getPatient(idPatient.getText().toString());
                if (patient != null){
                    Intent intent = new Intent(RecherchePatientActivity.this, AffichagePatientActivity.class);
                    intent.putExtra("patient", patient.getId().toString());
                    startActivity(intent);

                }
            }
        });
    }
}
