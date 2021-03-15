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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AjoutPatientActivity extends FonctionnaliteActivity  {
    private Patient patient;
    public static VoicyDbHelper patientDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ajout_patient);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_ajout_patient, null, false);
        drawerLayout.addView(contentView, 0);

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
                if (!((idPatient.getText().toString().isEmpty())&&(genrePatient.getText().toString().isEmpty()))){
                    patient = new Patient(idPatient.getText().toString(),genrePatient.getText().toString(),commentairePatient.getText().toString());
                    if(patientDbHelper.ajoutPatient(patient)){
                        Toast.makeText(AjoutPatientActivity.this, patient.getId().toString()+" ajouté",Toast.LENGTH_LONG).show();

                        /*SweetAlertDialog sDialog = new SweetAlertDialog(AjoutPatientActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        sDialog.setContentText("Le patient "+patient.getId()+" est ajouté ");
                        sDialog.setCancelable(true);
                        sDialog.show();*/

                        Intent intent = new Intent(AjoutPatientActivity.this, MainActivity.class);
                        startActivity(intent);

                    }else{
                        SweetAlertDialog sDialog = new SweetAlertDialog(AjoutPatientActivity.this, SweetAlertDialog.ERROR_TYPE);
                        sDialog.setTitleText("Oups ...");
                        sDialog.setContentText("Le patient "+patient.getId()+" existe sur la Base de données");
                        sDialog.setCancelable(true);
                        sDialog.show();
                    }
                }else{
                    SweetAlertDialog sDialog = new SweetAlertDialog(AjoutPatientActivity.this, SweetAlertDialog.ERROR_TYPE);
                    sDialog.setTitleText("Oups ...");
                    sDialog.setContentText("veuillez renseigner les informations SVP ");
                    sDialog.setCancelable(true);
                    sDialog.show();
                }
            }
        });

    }
}