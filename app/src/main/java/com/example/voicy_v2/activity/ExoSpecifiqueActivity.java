package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.voicy_v2.R;

public class ExoSpecifiqueActivity extends FonctionnaliteActivity {

    private ListView listView ;
    private TextView titre;
    private ImageButton ajoutExo;
    private String idPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_exo_specifique);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate of ExoSpecifiqueActivity !
        View contentView = inflater.inflate(R.layout.activity_exo_specifique, null, false);
        drawerLayout.addView(contentView, 0);

        Intent i = getIntent();
        idPatient = (String) i.getStringExtra("idPatient");

        titre = (TextView) findViewById(R.id.titre_exo);
        ajoutExo = (ImageButton) findViewById(R.id.btn_ajout_exo);
        listView = (ListView) findViewById(R.id.list_exo_spec);

        titre.setText("Exercices spécifiques "+idPatient);


    }
}