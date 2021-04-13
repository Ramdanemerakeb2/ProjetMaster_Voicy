package com.example.voicy_v2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Mot implements Parcelable
{
    private String mot;
    private String phonemes;

    public Mot(String m, String p)
    {
        mot = m;
        phonemes = p;
    }

    protected Mot(Parcel in) {
        mot = in.readString();
        phonemes = in.readString();
    }

    public static final Creator<Mot> CREATOR = new Creator<Mot>() {
        @Override
        public Mot createFromParcel(Parcel in) {
            return new Mot(in);
        }

        @Override
        public Mot[] newArray(int size) {
            return new Mot[size];
        }
    };

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getPhonemes() {
        return phonemes;
    }

    public void setPhonemes(String phonemes) {
        this.phonemes = phonemes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mot);
        dest.writeString(phonemes);
    }
}
