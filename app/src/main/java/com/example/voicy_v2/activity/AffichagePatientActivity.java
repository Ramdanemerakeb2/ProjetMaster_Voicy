package com.example.voicy_v2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.io.Serializable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AffichagePatientActivity extends FonctionnaliteActivity implements Serializable {
    private Patient patient;
    public static VoicyDbHelper patientDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_affichage_patient);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_affichage_patient, null, false);
        drawerLayout.addView(contentView, 0);

        patientDbHelper = new VoicyDbHelper(this);

        Intent i = getIntent();
        String patientId = (String) i.getStringExtra("patient");
        final Patient patient = patientDbHelper.getPatient(patientId);

        final TextView idPatient = (TextView) findViewById(R.id.idPatient);
        final TextView genre = (TextView) findViewById(R.id.genre);
        final TextView commentaire = (TextView) findViewById(R.id.commentaire);
        final TextView btnsave = (TextView) findViewById(R.id.btnSave);

        idPatient.setText(patient.getId());
        genre.setText(patient.getGenre());
        commentaire.setText(patient.getCommentaire());

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!((idPatient.getText().toString().isEmpty())&&(genre.getText().toString().isEmpty())))
                {   Patient patient1 = new Patient(idPatient.getText().toString(),genre.getText().toString(),commentaire.getText().toString());
                    patientDbHelper.updatePatient(patient1);

                    Toast.makeText(AffichagePatientActivity.this, patient.getId().toString() + " mis a jour", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AffichagePatientActivity.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    SweetAlertDialog sDialog = new SweetAlertDialog(AffichagePatientActivity.this, SweetAlertDialog.ERROR_TYPE);
                    sDialog.setTitleText("Oups ...");
                    sDialog.setContentText("veuillez renseigner les informations SVP ");
                    sDialog.setCancelable(true);
                    sDialog.show();
                }
            }
        });

    }
}