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
        //final TextView genre = (TextView) findViewById(R.id.genre);
        genrePatient = findViewById(R.id.spinner_genre);
        final TextView commentaire = (TextView) findViewById(R.id.commentaire);
        final TextView btnsave = (TextView) findViewById(R.id.btnSave);
        final Button btnPhrase = findViewById(R.id.btnPhrase);
        final Button btnLog = findViewById(R.id.btnLog);

        //ajout de la liste des genre pour le comobox
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genrePatient.setAdapter(adapter);

        idPatient.setText(patient.getId());
        //genre.setText(patient.getGenre());
        selectSpinnerItemByValue(genrePatient,patient.getGenre());
        commentaire.setText(patient.getCommentaire());

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!((idPatient.getText().toString().isEmpty())&&(genrePatient.getSelectedItem().toString().isEmpty())))
                {   Patient patient1 = new Patient(idPatient.getText().toString(),genrePatient.getSelectedItem().toString(),commentaire.getText().toString());
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

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DirectoryManager.getInstance().getAvailableMo() > 100)
                {
                    LogVoicy.getInstance().createLogInfo("Clique sur le bouton exercice phonème détecté");
                    LogVoicy.getInstance().createLogInfo("Changement de page vers ExerciceActivity avec envoie du paramètre [type: logatome]");
                    Intent intent = new Intent(getApplicationContext(), ConfigurationExerciceActivity.class);
                    intent.putExtra("type", "logatome");
                    startActivityForResult(intent, 0);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }

            }
        });

        btnPhrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DirectoryManager.getInstance().getAvailableMo() > 100)
                {
                    LogVoicy.getInstance().createLogInfo("Clique sur le bouton exercice phrase détecté");
                    LogVoicy.getInstance().createLogInfo("Changement de page vers PhonemeActivity avec envoie du paramètre [type: phrase]");
                    Intent intent = new Intent(getApplicationContext(), ConfigurationExerciceActivity.class);
                    intent.putExtra("type", "phrase");
                    startActivityForResult(intent, 1);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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