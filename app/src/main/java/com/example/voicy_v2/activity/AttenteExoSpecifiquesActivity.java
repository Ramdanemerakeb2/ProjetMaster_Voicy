package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.DirectoryManager;
import com.example.voicy_v2.model.Exercice;
import com.example.voicy_v2.model.ListeExoSpecefiqueAdapter;
import com.example.voicy_v2.model.SortFileByCreationDate;
import com.example.voicy_v2.model.VoicyDbHelper;

import java.io.File;
import java.util.List;

public class AttenteExoSpecifiquesActivity extends FonctionnaliteActivity {

    private ListView listView ;
    private TextView titre, btn_exo;
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
        View contentView = inflater.inflate(R.layout.activity_attente_exo_specifiques, null, false);
        drawerLayout.addView(contentView, 0);

        dbExo = new VoicyDbHelper(this);

        Intent i = getIntent();
        idPatient = (String) i.getStringExtra("idPatient");

        titre = (TextView) findViewById(R.id.titre_exo);
        btn_exo = (TextView) findViewById(R.id.btn_exo);
        listView = (ListView) findViewById(R.id.list_exo_spec);

        listeExoSpec = dbExo.getExosByPatient(idPatient);
        adapter = new ListeExoSpecefiqueAdapter(this, listeExoSpec);
        listView.setAdapter(adapter);

        titre.setText("Exercices spécifiques "+idPatient);

        //switch vers exo a realiser
        btn_exo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(AttenteExoSpecifiquesActivity.this, ExoSpecifiqueActivity.class);
                it.putExtra("idPatient", idPatient);
                startActivity(it);
            }
        });

        //gestion de la suppression d'un exo
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {

                Object o = listView.getItemAtPosition(position);
                final Exercice exo = (Exercice) o;

                new cn.pedant.SweetAlert.SweetAlertDialog(AttenteExoSpecifiquesActivity.this, cn.pedant.SweetAlert.SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Êtes-vous sûr ?")
                        .setContentText("Voulez-vous vraiment supprimer cet exercice ?")
                        .setConfirmText("Oui")
                        .setConfirmClickListener(new cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(cn.pedant.SweetAlert.SweetAlertDialog sDialog)
                            {
                                sDialog.dismissWithAnimation();

                                //suppression de l'exo de la BD
                                dbExo.deleteExoByPatient(idPatient);
                                //suppression les eventuelles sessions enregistré pour cet exo sur la tablette
                                //deleteFilesSessionByExo(exo.getId());
                                //suppression de l'exo de la liste
                                listeExoSpec.remove(position);

                                adapter.notifyDataSetChanged();

                            }
                        })
                        .setCancelButton("Non", new cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(cn.pedant.SweetAlert.SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();




                return true;
            }});


        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Exercice exoSelected = (Exercice) o;

                Intent intent = new Intent(AttenteExoSpecifiquesActivity.this, ExerciceActivity.class);
                intent.putExtra("exoSelected", (Parcelable) exoSelected);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });






    }

    private void deleteFilesSessionByExo(String idExo){

        File[] dirs = SortFileByCreationDate.getInstance().getListSorted(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient);

        for(int i = (dirs.length - 1); i >= 0; i--) {
            if(dirs[i].getName().contains(idExo)){
                DirectoryManager.getInstance().rmdirDirectory(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient+"/"+dirs[i].getName());
            }
        }

    }
}