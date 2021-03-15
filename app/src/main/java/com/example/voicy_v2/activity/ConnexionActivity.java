package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Clinicien;
import com.example.voicy_v2.model.VoicyDbHelper;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ConnexionActivity extends AppCompatActivity {

    public static VoicyDbHelper dbClinicien;
    private Clinicien clinicienSup ;

    //Pour la gestion des sessions
    public static final String clinicienSession = "clinicienSession" ;
    public static final String sessionIdClinicien = "idClinicien";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        dbClinicien = new VoicyDbHelper(this);


        TextView sup = (TextView) findViewById(R.id.sup);
        final EditText idConnexion = (EditText) findViewById(R.id.idConnexion);
        final EditText mdpConnexion = (EditText) findViewById(R.id.mdpConnexion);
        TextView btnConnexion = (TextView) findViewById(R.id.btnConnexion);


        //switch vers l'inscription
        sup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(ConnexionActivity.this, InscriptionActivity.class);
                startActivity(it);
            }
        });

        //verification des identifiants
        btnConnexion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String shalMdp = new String(Hex.encodeHex(DigestUtils.md5(mdpConnexion.getText().toString())));
                clinicienSup = dbClinicien.getClinicien(idConnexion.getText().toString(),shalMdp) ;

                if(clinicienSup != null){

                    //creation de session pour le clinicien (stock id)
                    sharedpreferences = getSharedPreferences(clinicienSession, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(sessionIdClinicien, clinicienSup.getIdentifiant());
                    editor.commit();

                    Intent it = new Intent(ConnexionActivity.this, MainActivity.class);
                    startActivity(it);
                }else{
                    SweetAlertDialog sDialog = new SweetAlertDialog(ConnexionActivity.this, SweetAlertDialog.ERROR_TYPE);
                    sDialog.setTitleText("Oups ...");
                    sDialog.setContentText("Vos identifiants sont incorrectes");
                    sDialog.setCancelable(true);
                    sDialog.show();
                }

            }
        });
    }
}