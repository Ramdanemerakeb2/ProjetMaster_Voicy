package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Exercice;
import com.example.voicy_v2.model.ExerciceLogatome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Pattern;

public class AccueilActivity extends FonctionnaliteActivity {

    private String[] fileList;
    private Exercice exoTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_accueil);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate of ListePatientActivity !
        View contentView = inflater.inflate(R.layout.activity_accueil, null, false);
        drawerLayout.addView(contentView, 0);

        //rechrager les exo predefinis disponible sur la tablette afin de selectionné un aleatoirement
        try {
            // Fonction permettant de récupérer tous les noms des listes predefinis
            fileList = getAssets().list("liste_exo_specefiques");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //selectionner un exo aleatoirement
        int rnd = new Random().nextInt(fileList.length);
        String exoSelected = fileList[rnd];

        String[] parts = exoSelected.split(Pattern.quote("."));
        String idExo = parts[0];
        String motDb = getTextFromFileV2(exoSelected)[0];
        String phonemDb = getTextFromFileV2(exoSelected)[1];
        System.out.println("idExo "+idExo);
        System.out.println("motDb "+motDb);
        System.out.println("phonemDb "+phonemDb);
        System.out.println("idPatient null");

        exoTest = new ExerciceLogatome(this,idExo,motDb,phonemDb,"logatome",null,"Homme");

        // a completer




    }

    private String[] getTextFromFileV2(String file) {
        String[] res = new String[2];
        String[] resultMotPhoneme = new String[] { "", "" };

        // buffer sur la liste présentes dans assets
        try(BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("liste_exo_specefiques/"+file))))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                line = line.substring(line.indexOf("\t")+1);

                // split de la ligne par rapport à une tabulation
                res = line.split("\t");

                resultMotPhoneme[0]  += res[0]+",";
                resultMotPhoneme[1]  += res[1]+",";
            }
            resultMotPhoneme[0] = resultMotPhoneme[0].substring(0, resultMotPhoneme[0].length() - 1);
            resultMotPhoneme[1] = resultMotPhoneme[1].substring(0, resultMotPhoneme[1].length() - 1);

            return  resultMotPhoneme;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return resultMotPhoneme;
        }
    }
}