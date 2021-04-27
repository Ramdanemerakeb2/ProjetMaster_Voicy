package com.example.voicy_v2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Exercice;
import com.example.voicy_v2.model.ExerciceLogatome;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import org.w3c.dom.Text;

import cn.pedant.SweetAlert.SweetAlertDialog;



public class CreationExoSpecifiqueLogatomActivity  extends FonctionnaliteActivity {
    Exercice exercice ;
    public static VoicyDbHelper dbPatientExo;
    public static String suiteFiltre = "" ;
    public static String serieFiltre = "" ;
    public static  String[] CVCV = new String[4]; ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_creation_exo_specifique_logatom, null, false);
        drawerLayout.addView(contentView, 0);

        dbPatientExo = new VoicyDbHelper(this);

        final TextView firstC1 = findViewById(R.id.C1);
        final TextView firstV1 = findViewById(R.id.V1);
        final TextView firstC2 = findViewById(R.id.C2);
        final TextView firstV2 = findViewById(R.id.V2);


        final TextView TextIdentifiantExo = findViewById(R.id.idExo);
        final TextView filtrePhonem = findViewById(R.id.phonems);
        final TextView filtrePhonemSerie = findViewById(R.id.phonems2);
        final TextView TextIteration = findViewById(R.id.textIteration);
        final TextView lesPhonems = findViewById(R.id.phonemeFilter);
        final TextView seriePhonems = findViewById(R.id.suitePhonem);
        TextView btnAjout = (TextView) findViewById(R.id.btnAjout);
        final Spinner genre = findViewById(R.id.spinner_genre);
        Intent i = getIntent();
        final String idPatient = (String) i.getStringExtra("idPatient");


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);

        firstC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstC1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstC1.setText(firstC1.getText().toString()+item.getTitle());
                        CVCV[0]=item.getTitle().toString();
                        if (CVCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        firstV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstV1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstV1.setText(firstV1.getText().toString()+item.getTitle());
                        CVCV[1]=item.getTitle().toString();
                        if (CVCV[1]==null){CVCV[1]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        firstC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstC2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstC2.setText(firstC2.getText().toString()+item.getTitle());
                        CVCV[2]=item.getTitle().toString();
                        if (CVCV[2]==null){CVCV[2]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        firstV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstV2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstV2.setText(firstV2.getText().toString()+item.getTitle());
                        CVCV[3]=item.getTitle().toString();
                        if (CVCV[3]==null){CVCV[3]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        filtrePhonemSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, filtrePhonemSerie);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.pop_up_phonem, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String s = item.getTitle().toString();
                        serieFiltre += s ;
                        serieFiltre += " ";
                        seriePhonems.setText("Les phonems sont '"+serieFiltre+"'");
                        Log.i("filtres ",suiteFiltre);
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"la suite de Phonems est: "+serieFiltre+" ajout du phonem: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        filtrePhonem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, filtrePhonem);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.pop_up_phonem, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String s = item.getTitle().toString();
                        suiteFiltre += s ;
                        suiteFiltre += " ";
                        lesPhonems.setText("la suite de phonems est ' "+suiteFiltre+"'");
                        Log.i("filtres ",suiteFiltre);
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"les Phonems sont "+suiteFiltre+"ajout du phonem : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
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
                        String phonemesFiltrage;
                        if(suiteFiltre!=""){
                            suiteFiltre=suiteFiltre.substring(0, suiteFiltre.length() - 1);
                        }
                        if(serieFiltre!=""){
                            serieFiltre=serieFiltre.substring(0, serieFiltre.length() - 1);
                        }
                        phonemesFiltrage = suiteFiltre;
                        for (int m =0 ; m<CVCV.length;m++){
                            if (CVCV[m]==null){CVCV[m]="  ";}
                        }
                        //public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String phonemeFiltrage)
                        if(dbPatientExo.addExercice(new ExerciceLogatome(Integer.parseInt(String.valueOf(TextIteration.getText())),genrePatient,getApplicationContext(),TextIdentifiantExo.getText().toString(),idPatient,suiteFiltre,serieFiltre,CVCV))){
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
                        intent.putExtra("CVCV",CVCV);
                        intent.putExtra("phonemes",phonemesFiltrage);
                        intent.putExtra("serieFiltre",serieFiltre);
                        intent.putExtra("suiteFiltre",suiteFiltre);
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
