package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class InfosPatientActivity extends FonctionnaliteActivity {

    private TextView btnModification, btnListerExo, btnSessions;
    private static VoicyDbHelper dbPatient;
    private String idPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_infos_patient);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate of ListePatientActivity !
        View contentView = inflater.inflate(R.layout.activity_infos_patient, null, false);
        drawerLayout.addView(contentView, 0);

        dbPatient = new VoicyDbHelper(this);

        Intent i = getIntent();
        idPatient = (String) i.getStringExtra("idPatient");

        btnModification = (TextView) findViewById(R.id.btnModification);
        btnListerExo = (TextView) findViewById(R.id.btnListerExo);
        btnSessions = (TextView) findViewById(R.id.btnSessions);

        btnModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfosPatientActivity.this, AffichagePatientActivity.class);
                intent.putExtra("idPatient", idPatient);
                startActivity(intent);
            }
        });



    }
}