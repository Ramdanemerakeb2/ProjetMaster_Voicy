package com.example.voicy_v2.model;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.lang.*;

public class ExerciceLogatome extends Exercice
{

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ExerciceLogatome(int nb, String leGenre, Context c)
    {
        super(c);
        totalIteration = nb;
        genre = leGenre;

        // Va récuperer les nonmots de la liste
        recupereElementExercice("lexique_phone.AA4");

        directoryName = getExerciceDirectory();

        DirectoryManager.getInstance().createFolder(DirectoryManager.OUTPUT_RESULTAT + "/" + directoryName);
    }

    public ExerciceLogatome(Context c ,String id, String mot, String phonem, String idPatient){
        super(c);
        this.id = id;
        this.listMotString = mot ;
        this.listPhonemString = phonem;
        this.patientSpecifiqueId = idPatient;
        this.typeExo = "logatome";
    }
    //constructeur d'exo specifique a ajouter dans la bd
    public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String phonemeFiltrage)
    {
        super(c);
        totalIteration = nb;
        genre = leGenre;
        //idExo = id ;
        this.id = idExo ;
        this.typeExo = "logatome";
//        listMotString = listmots;
        patientSpecifiqueId = patientId;
//        listPhonemString = listPhonem ;
//        recupereElementExercice("lexique_phone.AA4");
//        String[] mesFiltres = phonemeFiltrage.split(",");
//
//        for (int i =0 ; i<mesFiltres.length;i++){
//        Log.i("mesFiltre",mesFiltres[i]);
//            Log.i("size is ", String.valueOf(listeElement.size()));
//            if (!(listeElement.get(i).getPhonemes().contains(mesFiltres[i]))){
//                Log.i("mesFiltre","c'est passé");
//                listeElement.remove(listeElement.get(i));
//            }
//        }
//        Log.i("size is ", String.valueOf(listeElement.size()));
//        for(int i = 0; i < listeElement.size(); i++) {
//            //Log.i("mot : ",listeElement.get(i).getMot());
//            //Log.i("phonem: ",listeElement.get(i).getPhonemes());
//        }
//        Log.i("la taille : ", String.valueOf(listeElement.size()));
        recupereElementExercice("lexique_phone.AA4");
        String[] mesFiltres = phonemeFiltrage.split(",");
        ArrayList<Mot> temp = new ArrayList<Mot>();
        int i =0 ;
        while (temp.size()!=totalIteration){
            Log.i("mot : ",listeElement.get(i).getMot());
            for (int j=0;j<mesFiltres.length;j++){
            if (listeElement.get(i).getPhonemes().contains(mesFiltres[j])){
                temp.add(new Mot(listeElement.get(i).getMot(),listeElement.get(i).getPhonemes()));

            }


            }
            i++;
        }
        if (temp.size()!=0){
            listeElement.clear();
            for (int k=0 ;k< temp.size();k++){
                listeElement.add(new Mot(temp.get(k).getMot(),temp.get(k).getPhonemes()));
                listMotString += temp.get(k).getMot();
                listMotString += ",";
                listPhonemString += temp.get(k).getPhonemes();
                listPhonemString += ",";
            }
            listMotString = listMotString.substring(3, listMotString.length() - 1);
            listPhonemString = listPhonemString.substring(3, listPhonemString.length() - 1);
        }
//        listeElement = temp ;

        //via le fichier de logatomes+phonems
         /*if (!phonemeFiltrage.equals("")){
            String[] mesFiltres = phonemeFiltrage.split(",");
            String[] res = null;
            try(BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("lexique_phone.AA4"))))
            {
                String line;
                while ((line = br.readLine()) != null)
                {
                    line = line.substring(line.indexOf("\t")+1);

                    // split de la ligne par rapport à une tabulation
                    res = line.split("\t");
                    for (int j=0;j<=totalIteration;j++) {
                        for (int i = 0; i < mesFiltres.length; i++) {
                            if (res[1].contains(mesFiltres[i])) {
                                Log.i("le resultat " + i + " est : ", res[i]);
                                listeElement.add(new Mot(res[0], res[1]));
                                listMotString += res[0];
                                listMotString += ",";
                                listPhonemString += res[1];
                                listPhonemString += ",";
                                //a inserer dans la base de données
                            }
                        }
                    }
                    Log.i("la taille : ", String.valueOf(listeElement.size()));
                    // Log.i("message du mot",res[0]);
                    //Log.i("message du phonem",res[1]);
                    // Ajout du mot dans l'arrayList avec en param 1 = mot et param 2 = phoneme


                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }


        }*/
        Collections.shuffle(listeElement);

        directoryName = getExerciceDirectory();

        DirectoryManager.getInstance().createFolder(DirectoryManager.OUTPUT_RESULTAT + "/" + directoryName);

    }

    //Constructeur d'exercice depuisla Bd (exo a recharger) .
    public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String listmots,String listPhonem)
    {
        super(c);
        totalIteration = nb;
        genre = leGenre;
        idExo = id ;
        listMotString = listmots;
        patientSpecifiqueId = patientId;
        listPhonemString = listPhonem ;

        // Va récuperer les nonmots de la liste
        rechargerMots();

        directoryName = getExerciceDirectory();

        DirectoryManager.getInstance().createFolder(DirectoryManager.OUTPUT_RESULTAT + "/" + directoryName);
    }

    public void rechargerMots(){
        listeElement.clear();
        String[] resLogatoms = listMotString.split(",");
//        String[] resLogatoms = listMotString.split("|");
        String[] resPhonems = listPhonemString.split(",");
        Log.i("taille", String.valueOf(resLogatoms.length));
        // String[] resPhonems = listPhonemString.split("|");
        for (int i =1 ; i<resLogatoms.length;i++){
            listeElement.add(new Mot(resLogatoms[i], resPhonems[i]));
            Log.i("message du mot",resLogatoms[1]);
            // Log.i("message du phoneme",resPhonems[i]);
        }
        //Collections.shuffle(listeElement);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void recupereElementExercice(String fileName)
    {
        // Vide la liste (au cas où)
        listeElement.clear();

        String[] res = null;

        // buffer sur la liste présentes dans assets
        try(BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName))))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                line = line.substring(line.indexOf("\t")+1);

                // split de la ligne par rapport à une tabulation
                res = line.split("\t");
                // Log.i("message du mot",res[0]);
                //Log.i("message du phonem",res[1]);
                // Ajout du mot dans l'arrayList avec en param 1 = mot et param 2 = phoneme
                listeElement.add(new Mot(res[0], res[1]));

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        // Un mélange de la liste finale
        Collections.shuffle(listeElement);
    }

    @Override
    protected String getExerciceDirectory()
    {
        String direcName = "Logatome_";

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String currentDateandTime = sdf.format(new Date());

        direcName += currentDateandTime;

        direcName += "_" + genre;

        //Log.d("logATOM", "Directory : " + direcName);

        return direcName;
    }
}
