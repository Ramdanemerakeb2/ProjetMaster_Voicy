package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Clinicien;
import com.example.voicy_v2.model.ClinicienDbHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class InscriptionActivity extends AppCompatActivity {

    public static ClinicienDbHelper dbClinicien;
    private Clinicien clinicienSin ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        dbClinicien = new ClinicienDbHelper(this);

        TextView sin = (TextView) findViewById(R.id.sin);
        final TextView nomInscription = (TextView) findViewById(R.id.nomInscription);
        final TextView prenomInscription = (TextView) findViewById(R.id.prenomInscription);
        final TextView idInscription = (TextView) findViewById(R.id.idInscription);
        final TextView mdpInscription1 = (TextView) findViewById(R.id.mdpInscription1);
        final TextView mdpInscription2 = (TextView) findViewById(R.id.mdpInscription2);
        TextView btnInscription = (TextView) findViewById(R.id.btnInscription);


        //switch vers la connexion
        sin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(InscriptionActivity.this, ConnexionActivity.class);
                startActivity(it);
            }
        });

        //verification des identifiants
        btnInscription.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // verification de la confirmation du MDP
                if( mdpInscription1.getText().toString().equals(mdpInscription2.getText().toString()) ){

                    /**https://stackoverflow.com/questions/9126567/method-not-found-using-digestutils-in-android
                     * cryptage de MDP en md5
                     */
                    String shalMdp = new String(Hex.encodeHex(DigestUtils.md5(mdpInscription1.getText().toString())));
                    System.out.println("Mot de passe crypté  "+shalMdp);

                    //création de l'objet clinicien avec le mot de passe crypté
                    clinicienSin = new Clinicien(idInscription.getText().toString(),nomInscription.getText().toString(),
                                                 prenomInscription.getText().toString(),shalMdp);

                    //Clinicien non existant sur la BD (Succes)
                    if(dbClinicien.addClinicien(clinicienSin)){
                        Intent it = new Intent(InscriptionActivity.this, ConnexionActivity.class);
                        startActivity(it);
                    }else{
                        SweetAlertDialog sDialog = new SweetAlertDialog(InscriptionActivity.this, SweetAlertDialog.ERROR_TYPE);
                        sDialog.setTitleText("Oups ...");
                        sDialog.setContentText("Veuillez changez l'identifiant SVP");
                        sDialog.setCancelable(true);
                        sDialog.show();
                    }

                }else{
                    SweetAlertDialog sDialog = new SweetAlertDialog(InscriptionActivity.this, SweetAlertDialog.ERROR_TYPE);
                    sDialog.setTitleText("Oups ...");
                    sDialog.setContentText("Confirmation de mot de passe échoué");
                    sDialog.setCancelable(true);
                    sDialog.show();
                }

            }
        });


    }
}