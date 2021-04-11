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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_creation_exo_specifique_logatom, null, false);
        drawerLayout.addView(contentView, 0);

        final TextView TextIteration = findViewById(R.id.textIteration);
        final TextView lesPhonems = findViewById(R.id.phonemeFilter);
        TextView btnAjout = (TextView) findViewById(R.id.btnAjout);
        final Spinner genre = findViewById(R.id.spinner_genre);

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
                    {   String listmot = "zouvrin,zouvro,zucra";
                        String listphoneme = "zz ou vv rr in,zz ou vv rr au,zz uu kk rr aa" ;
                        String genrePatient = genre.getSelectedItem().toString() ;
                        String phonemesFiltrage = lesPhonems.getText().toString();
                        Log.i("taille",listmot );


                        //public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String listmots,String listPhonem)
                        //exercice = new ExerciceLogatome(iteration)
                        Intent intent = new Intent(CreationExoSpecifiqueLogatomActivity.this, ExerciceSpecifiqueLogatomLaunchActivity.class);
                        intent.putExtra("phonemes",phonemesFiltrage);
                        intent.putExtra("type", "logatome");
                        intent.putExtra("genre", genrePatient);
                        intent.putExtra("iteration", iteration);
                        intent.putExtra("idExo", "5");
                        intent.putExtra("patientId", "2");
                        intent.putExtra("logatoms","zouvrin,zouvro,zucra");
                        intent.putExtra("phonems",listphoneme);
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
