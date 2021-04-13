package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Exercice;
import com.example.voicy_v2.model.ListeExoSpecefiqueAdapter;
import com.example.voicy_v2.model.ListePatientAdapter;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.util.List;

public class ExoSpecifiqueActivity extends FonctionnaliteActivity {

    private ListView listView ;
    private TextView titre;
    private ImageButton ajoutExo2,ajoutExo;;
    private String idPatient;
    public static VoicyDbHelper dbExo;
    private ListeExoSpecefiqueAdapter adapter ;
    private List<Exercice> listeExoSpec ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_exo_specifique);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate of ExoSpecifiqueActivity !
        View contentView = inflater.inflate(R.layout.activity_exo_specifique, null, false);
        drawerLayout.addView(contentView, 0);

        dbExo = new VoicyDbHelper(this);

        Intent i = getIntent();
        idPatient = (String) i.getStringExtra("idPatient");

        titre = (TextView) findViewById(R.id.titre_exo);
        ajoutExo = (ImageButton) findViewById(R.id.btn_ajout_exo);
        ajoutExo2 = (ImageButton) findViewById(R.id.btn_ajout_exo2);
        listView = (ListView) findViewById(R.id.list_exo_spec);

        listeExoSpec = dbExo.getExosByPatient(idPatient);
        adapter = new ListeExoSpecefiqueAdapter(this, listeExoSpec);
        listView.setAdapter(adapter);

        titre.setText("Exercices spécifiques "+idPatient);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Exercice exoSelected = (Exercice) o;

                Intent intent = new Intent(ExoSpecifiqueActivity.this, ExerciceActivity.class);
                intent.putExtra("exoSelected", (Parcelable) exoSelected);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        ajoutExo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ExoSpecifiqueActivity.this, CreationExoSpecifiqueLogatomActivity.class);
                intent.putExtra("idPatient", idPatient);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        ajoutExo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListeExoPredefiniActivity.class);
                intent.putExtra("idPatient", idPatient);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });




    }
}