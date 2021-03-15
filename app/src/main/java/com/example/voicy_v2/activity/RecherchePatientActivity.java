package com.example.voicy_v2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.io.Serializable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RecherchePatientActivity extends FonctionnaliteActivity implements Serializable {
    private Patient patient;
    public static VoicyDbHelper patientDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_recherche_patient);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_recherche_patient, null, false);
        drawerLayout.addView(contentView, 0);

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

                }else{
                    SweetAlertDialog sDialog = new SweetAlertDialog(RecherchePatientActivity.this, SweetAlertDialog.WARNING_TYPE);
                    sDialog.setTitleText("Oups ...");
                    sDialog.setContentText("Le patient "+idPatient.getText().toString()+" n'existe pas sur la Base de données");
                    sDialog.setCancelable(true);
                    sDialog.show();
                }
            }
        });
    }
}
