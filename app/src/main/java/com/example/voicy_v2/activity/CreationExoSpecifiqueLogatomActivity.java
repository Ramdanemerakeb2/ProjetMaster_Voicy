package com.example.voicy_v2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Exercice;
import com.example.voicy_v2.model.ExerciceLogatome;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;



public class CreationExoSpecifiqueLogatomActivity  extends FonctionnaliteActivity {
    Exercice exercice ;
    public static VoicyDbHelper dbPatientExo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_creation_exo_specifique_logatom, null, false);
        drawerLayout.addView(contentView, 0);

        dbPatientExo = new VoicyDbHelper(this);
        final TextView TextIdentifiantExo = findViewById(R.id.idExo);
        final TextView TextIteration = findViewById(R.id.textIteration);
        final TextView lesPhonems = findViewById(R.id.phonemeFilter);
        TextView btnAjout = (TextView) findViewById(R.id.btnAjout);
        final Spinner genre = findViewById(R.id.spinner_genre);
        Intent i = getIntent();
        final String idPatient = (String) i.getStringExtra("idPatient");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);

        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(String.valueOf(TextIteration.getText()).length() > 2 || String.valueOf(TextIteration.getText()).length() < 1)
                {
                    mauvaiseConfig("Veuillez remplir le nombre d'exercice à effectuer");
                }
                else
                {
                    String.valueOf(TextIteration.getText());
                    int iteration = Integer.parseInt(String.valueOf(TextIteration.getText()));

                    if(iteration == 0)
                    {
                        mauvaiseConfig("Impossible de lancer 0 exercice.");
                    }
                    else if(iteration > 12)
                    {
                        mauvaiseConfig("Impossible de lancer plus de 12 exercices");
                    }
                    else
                    {
                        String genrePatient = genre.getSelectedItem().toString() ;
                        String phonemesFiltrage = lesPhonems.getText().toString();

                        //public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String phonemeFiltrage)
                        if(dbPatientExo.addExercice(new ExerciceLogatome(Integer.parseInt(String.valueOf(TextIteration.getText())),genrePatient,getApplicationContext(),TextIdentifiantExo.getText().toString(),idPatient,phonemesFiltrage))){
                            SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sDialog.setContentText("Exercice ajouté");
                            sDialog.setCancelable(true);
                            sDialog.show();
                        }else{
                            SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.ERROR_TYPE);
                            sDialog.setTitleText("Oups ...");
                            sDialog.setContentText("Ce patient possède déjà cet exercice");
                            sDialog.setCancelable(true);
                            sDialog.show();
                        }
                        //public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String listmots,String listPhonem)
                        //exercice = new ExerciceLogatome(iteration)
                        Intent intent = new Intent(CreationExoSpecifiqueLogatomActivity.this, ExerciceSpecifiqueLogatomLaunchActivity.class);
                        intent.putExtra("phonemes",phonemesFiltrage);
                        intent.putExtra("type", "logatome");
                        intent.putExtra("genre", genrePatient);
                        intent.putExtra("iteration", iteration);
                        intent.putExtra("IdPatient", idPatient);
                        startActivityForResult(intent, 0);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                }
            }
        });

    }

    public void mauvaiseConfig(String error)
    {
        SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.ERROR_TYPE);
        sDialog.setTitleText("Oups ...");
        sDialog.setContentText(error);
        sDialog.setConfirmText("Ok");
        sDialog.setCancelable(false);
        sDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
        {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        });
        sDialog.show();
    }
}
