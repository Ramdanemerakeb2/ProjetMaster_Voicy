package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
    private CheckBox filtrageLogatomes, filtagePhrases, filrageTexte ;
    private EditText filtrageDate ;
    private String typeFiltre = "null";
    private String dateFiltrage = "null";


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
        filtrageLogatomes = (CheckBox) findViewById(R.id.filtrage_logatomes);
        filtagePhrases = (CheckBox) findViewById(R.id.filtrage_phrases);
        filrageTexte = (CheckBox) findViewById(R.id.filtrage_texte);
        filtrageDate = (EditText) findViewById(R.id.filtrage_date);

        btnModification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfosPatientActivity.this, AffichagePatientActivity.class);
                intent.putExtra("idPatient", idPatient);
                startActivity(intent);
            }
        });

        btnListerExo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfosPatientActivity.this, ExoSpecifiqueActivity.class);
                intent.putExtra("idPatient", idPatient);
                startActivity(intent);
            }
        });

        filtrageLogatomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    //desactiver le champs de date
                    filtrageDate.setEnabled(false);
                    typeFiltre = "logatomes";
                }else{
                    filtrageDate.setEnabled(true);
                    typeFiltre = "null";
                }
            }
        });

        //ajouter un textWatcher sur la date
        filtrageDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!(s.toString().trim().length()==0)) {
                    //desactiver les chekbox
                    filtrageLogatomes.setEnabled(false);
                    filtagePhrases.setEnabled(false);
                    filrageTexte.setEnabled(false);
                    typeFiltre = "date";

                } else {
                    filtrageLogatomes.setEnabled(true);
                    filtagePhrases.setEnabled(true);
                    filrageTexte.setEnabled(true);
                    typeFiltre = "null";
                    dateFiltrage = "null";
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!( filtrageDate.getText().toString().equals("") ))
                    dateFiltrage = filtrageDate.getText().toString();
                else
                    dateFiltrage = "null";
            }
        });

        btnSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("******** typeFiltre "+ typeFiltre+" **********");
                System.out.println("******** dateFiltrage "+ dateFiltrage+" **********");
                Intent intent = new Intent(InfosPatientActivity.this, SessionActivity.class);
                intent.putExtra("idPatient", idPatient);
                intent.putExtra("typeFiltre", typeFiltre);
                intent.putExtra("dateFiltrage", dateFiltrage);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });



    }
}