package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Clinicien;
import com.example.voicy_v2.model.ClinicienDbHelper;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ConnexionActivity extends AppCompatActivity {

    public static ClinicienDbHelper dbClinicien;
    private Clinicien clinicienSup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        dbClinicien = new ClinicienDbHelper(this);


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