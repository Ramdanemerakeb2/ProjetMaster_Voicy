package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.DirectoryManager;
import com.example.voicy_v2.model.Exercice;
import com.example.voicy_v2.model.ListSessionAdapter;
import com.example.voicy_v2.model.ListeExoSpecefiqueAdapter;
import com.example.voicy_v2.model.ResultFile;
import com.example.voicy_v2.model.SessionFile;
import com.example.voicy_v2.model.SortFileByCreationDate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SessionActivity extends FonctionnaliteActivity{

    private ListView listView ;
    private String idPatient, typeFiltre, dateFiltrage;
    private ListSessionAdapter adapter ;
    private List<SessionFile> listeSession ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_session);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate of ExoSpecifiqueActivity !
        View contentView = inflater.inflate(R.layout.activity_session, null, false);
        drawerLayout.addView(contentView, 0);

        Intent i = getIntent();
        idPatient = (String) i.getStringExtra("idPatient");
        typeFiltre = (String) i.getStringExtra("typeFiltre");
        dateFiltrage = (String) i.getStringExtra("dateFiltrage");

        //conversion de la date au format qui convient au nommage des dossier en supprimant "-"
        if(dateFiltrage.contains("/")){
            System.out.println("******** Modification de date");
            dateFiltrage = dateFiltrage.replace("/", "");
        }


        System.out.println("******** typeFiltre "+ typeFiltre+" **********");
        System.out.println("******** dateFiltrage "+ dateFiltrage+" **********");

        listView = (ListView) findViewById(R.id.list_sessions);
        listeSession = getAllSessionsFromAppFolderById(idPatient);
        adapter = new ListSessionAdapter(this, listeSession);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                SessionFile session = (SessionFile) o;

                Intent intent = new Intent(SessionActivity.this, AffichageExerciceActivity.class);
                intent.putExtra("resultat", session);
                intent.putExtra("idPatient", idPatient);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


    }

    // Fonction permettant de récupérer tous dossier sessions d'un patient et en les transformants en objet SessionFile
    public List<SessionFile> getAllSessionsFromAppFolderById(String idPatient)
    {
        List<SessionFile> listResult = new ArrayList<>();

        File[] dirs = SortFileByCreationDate.getInstance().getListSorted(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient);

        //pas de filtre (par defaut)
        if(typeFiltre.equals("null")){
            for(int i = (dirs.length - 1); i >= 0; i--) {
                SessionFile s = new SessionFile(dirs[i].getName());
                s.setPathName(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient+"/"+dirs[i].getName());
                listResult.add(s);
            }
        }

        //filtrage par logatomes
        if(typeFiltre.equals("logatomes")){
            for(int i = (dirs.length - 1); i >= 0; i--) {
                if(dirs[i].getName().contains("logatome")){
                    SessionFile s = new SessionFile(dirs[i].getName());
                    s.setPathName(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient+"/"+dirs[i].getName());
                    listResult.add(s);
                }
            }
        }

        //filtrage par phrase
        if(typeFiltre.equals("phrase")){
            for(int i = (dirs.length - 1); i >= 0; i--) {
                if(dirs[i].getName().contains("phrase")){
                    SessionFile s = new SessionFile(dirs[i].getName());
                    s.setPathName(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient+"/"+dirs[i].getName());
                    listResult.add(s);
                }
            }
        }

        //filtrage par texte
        if(typeFiltre.equals("texte")){
            for(int i = (dirs.length - 1); i >= 0; i--) {
                if(dirs[i].getName().contains("texte")){
                    SessionFile s = new SessionFile(dirs[i].getName());
                    s.setPathName(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient+"/"+dirs[i].getName());
                    listResult.add(s);
                }
            }
        }

        //filtrage par date
        if(typeFiltre.equals("date")){
            for(int i = (dirs.length - 1); i >= 0; i--) {
                if(dirs[i].getName().contains(dateFiltrage)){
                    SessionFile s = new SessionFile(dirs[i].getName());
                    s.setPathName(DirectoryManager.OUTPUT_PATEIENTS+"/"+idPatient+"/"+dirs[i].getName());
                    listResult.add(s);
                }
            }
        }
        return listResult;
    }
}