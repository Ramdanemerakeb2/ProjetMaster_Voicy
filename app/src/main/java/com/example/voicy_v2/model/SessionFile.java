package com.example.voicy_v2.model;

import java.io.Serializable;
import java.util.regex.Pattern;

public class SessionFile implements Serializable
{
    private String nameFile;
    private String date;
    private String hour;
    private String type;
    private String idExo;
    private String pathName;

    public SessionFile(String name)
    {
        this.nameFile = name;

        formatDataFromNameFolder();
    }

    public SessionFile(String name, String d, String h, String t)
    {
        this.nameFile = name;
        this.date = d;
        this.hour = h;
        this.type = t;
    }

    private void formatDataFromNameFolder()
    {
        String laDate = "" ,laDate1 = "", lheure = "",lheure1 = "";

        String chaine[] = this.nameFile.split(Pattern.quote("_"));

        laDate1 = addChar(chaine[0],'/',2);
        laDate = addChar(laDate1,'/',5);

        lheure1 = addChar(chaine[1],':',2);
        lheure = addChar(lheure1,':',5);



        this.date = laDate;
        this.hour = lheure;
        this.type  = chaine[2];
        this.idExo = chaine[3];

    }

    public String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdExo() {
        return idExo;
    }

    public void setIdExo(String idExo) {
        this.idExo = idExo;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}
