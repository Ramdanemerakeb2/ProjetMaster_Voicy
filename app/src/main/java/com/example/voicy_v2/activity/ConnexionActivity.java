package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.voicy_v2.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ConnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        TextView sup = (TextView) findViewById(R.id.sup);
        final EditText idConnexion = (EditText) findViewById(R.id.identifiant);
        final EditText mdpConnexion = (EditText) findViewById(R.id.mdp);
        TextView btnonnexion = (TextView) findViewById(R.id.btnConnexion);


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
        btnonnexion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if( (idConnexion.getText().toString().equals("test")) &&(mdpConnexion.getText().toString().equals("test"))){
                    Intent it = new Intent(ConnexionActivity.this, MainActivity.class);
                    startActivity(it);
                }else{
                    SweetAlertDialog sDialog = new SweetAlertDialog(ConnexionActivity.this, SweetAlertDialog.ERROR_TYPE);
                    sDialog.setTitleText("Oups ...");
                    sDialog.setContentText("identifiant ou mot de passe incorrecte");
                    sDialog.setCancelable(true);
                    sDialog.show();
                }

            }
        });
    }
}