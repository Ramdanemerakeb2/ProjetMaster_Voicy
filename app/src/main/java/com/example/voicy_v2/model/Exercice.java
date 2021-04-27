package com.example.voicy_v2.model;

import android.content.Context;

import java.util.ArrayList;

public abstract class Exercice
{
    protected int totalIteration;
    protected int actuelIteration;
    protected String genre;
    protected ArrayList<Mot> listeElement;
    protected Context context;
    protected String directoryName;
    protected String id ;
    protected String idDb ;
    protected String listMotString = "";
    protected String listPhonemString = "";
    protected String patientSpecifiqueId ;
    protected String typeExo ;

    public String getListPhonemString() {
        return listPhonemString;
    }

    public ArrayList<Mot> getListElement() {
        return listeElement;
    }

    public void setListPhonemString(String listPhonemString) {
        this.listPhonemString = listPhonemString;
    }

    public String getTypeExo() {
        return typeExo;
    }

    public void setTypeExo(String typeExo) {
        this.typeExo = typeExo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDb() {
        return idDb;
    }

    public void setIdDb(String idDb) {
        this.idDb = idDb;
    }

    public String getListMotString() {
        return listMotString;
    }

    public void setListMotString(String listMotString) {
        this.listMotString = listMotString;
    }

    public String getPatientSpecifiqueId() {
        return patientSpecifiqueId;
    }

    public void setPatientSpecifiqueId(String patientSpecifiqueId) {
        this.patientSpecifiqueId = patientSpecifiqueId;
    }



    public Exercice(Context c)
    {
        actuelIteration = 0;
        listeElement = new ArrayList<>();
        context = c;
    }

    // Permet de déterminer si l'exercice est terminé
    public boolean isExerciceFinish()
    {
        if(actuelIteration == totalIteration)
            return true;

        return false;
    }

    // Incremente l'iteration de l'exercice
    public void nextIteration()
    {
        actuelIteration++;
    }

    // Decrémente l'iteration de l'exercice
    public void previousIteration()
    {
        if(actuelIteration > 0)
        {
            actuelIteration--;
        }
    }

    // Renvoie un objet mot correspondant à celui qui est en cours d'affichage
    public Mot getActuelMot()
    {
        return listeElement.get(actuelIteration);
    }

    // Renvoie sous format text : 1/3 ou bien 2/3 donc iteration / maxIteration
    public String getIterationSurMax() {  return (actuelIteration + 1) + "/" + totalIteration; }

    protected abstract void recupereElementExercice(String f);
    protected abstract String getExerciceDirectory();

    public int getTotalIteration() { return totalIteration;}
    public int getActuelIteration() { return actuelIteration;}
    public String getDirectoryName() { return directoryName; }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
// Renvoie le chemin complet vers le dossier de l'exercice se trouvant dans resultat
    //public String getDirectoryPath() { return DirectoryManager.OUTPUT_RESULTAT + "/" + directoryName;}

    public String getDirectoryPath() { return directoryName;}


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
