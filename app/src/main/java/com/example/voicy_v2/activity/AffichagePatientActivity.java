
package com.example.voicy_v2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.DirectoryManager;
import com.example.voicy_v2.model.LogVoicy;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.io.Serializable;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AffichagePatientActivity extends FonctionnaliteActivity implements Serializable {
    private Patient patient;
    public static VoicyDbHelper patientDbHelper;
    private Spinner genrePatient;
    String patientIdIntent;

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
        patientIdIntent = (String) i.getStringExtra("idPatient");
        final Patient patient = patientDbHelper.getPatient(patientIdIntent);

        final TextView idPatient = (TextView) findViewById(R.id.idPatient);
        //final TextView genre = (TextView) findViewById(R.id.genre);
        genrePatient = findViewById(R.id.spinner_genre);
        final TextView commentaire = (TextView) findViewById(R.id.commentaire);
        final TextView btnsave = (TextView) findViewById(R.id.btnSave);

        //ajout de la liste des genre pour le comobox
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genrePatient.setAdapter(adapter);

        idPatient.setText("Informations du patient "+patient.getId());
        selectSpinnerItemByValue(genrePatient,patient.getGenre());
        commentaire.setText(patient.getCommentaire());

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(idPatient.getText().toString().isEmpty())&& !(genrePatient.getSelectedItem().toString().isEmpty()))
                {   Patient patient1 = new Patient(patientIdIntent,genrePatient.getSelectedItem().toString(),commentaire.getText().toString());
                    patientDbHelper.updatePatient(patient1);

                    Toast.makeText(AffichagePatientActivity.this, patient.getId().toString() + " mis a jour", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AffichagePatientActivity.this, ListePatientActivity.class);
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
    //cette fonction permet de selctionner une valeur passé en argument sur le combobox
    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position).toString().equals(value) ) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}