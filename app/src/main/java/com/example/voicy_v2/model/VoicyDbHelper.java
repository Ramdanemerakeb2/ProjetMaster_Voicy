package com.example.voicy_v2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voicy_v2.R;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

public class VoicyDbHelper extends SQLiteOpenHelper {

    private static final String TAG = VoicyDbHelper.class.getSimpleName();

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Voicy.db";

    //***************Table Clinicien***********************
    public static final String TABLE_CLINICIEN = "Clinicien";
    public static final String COLUMN_CLINICIEN_NOM = "nom";
    public static final String COLUMN_CLINICIEN_PRENOM = "prenom";
    public static final String COLUMN_CLINICIEN_ID = "id";
    public static final String COLUMN_CLINICIEN_MDP = "MDP";

    //***************Table Patient***********************
    public static final String TABLE_PATIENT = "Patient";
    public static final String COLUMN_PATIENT_ID = "id";
    public static final String COLUMN_PATIENT_GENRE = "genre";
    public static final String COLUMN_PATIENT_COMMENTAIRE = "commentaire";

    public VoicyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // creation de la table clinicien
        final String SQL_CREATE_CLINICIEN_TABLE = "CREATE TABLE " + TABLE_CLINICIEN + " (" +
                COLUMN_CLINICIEN_ID + " TEXT PRIMARY KEY," +
                COLUMN_CLINICIEN_NOM + " TEXT NOT NULL, " +
                COLUMN_CLINICIEN_PRENOM + " TEXT, " +
                COLUMN_CLINICIEN_MDP + " TEXT, " +

                // To assure the application have just one team entry per
                // product name , it's created a UNIQUE
                " UNIQUE (" + COLUMN_CLINICIEN_ID + ") ON CONFLICT ROLLBACK);";

        // creation de la table Patient
        final String SQL_CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + " (" +
                COLUMN_PATIENT_ID + " TEXT PRIMARY KEY," +
                COLUMN_PATIENT_GENRE + " TEXT , " +
                COLUMN_PATIENT_COMMENTAIRE + " TEXT, " +
                " UNIQUE (" + COLUMN_PATIENT_ID + ") ON CONFLICT ROLLBACK);";

        db.execSQL(SQL_CREATE_PATIENT_TABLE);

        db.execSQL(SQL_CREATE_CLINICIEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //******************** gestion de clinicien ********************
    /**
     * Adds a new Clinicien
     * @return  true if the Clinicien was added to the table ; false otherwise (case when Clinicien is
     * already in the data base)
     */

    public boolean addClinicien(Clinicien clinicien) {
        Log.i(TAG, "Ajout du clinicien ... " + clinicien.getNom());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLINICIEN_NOM, clinicien.getNom());
        values.put(COLUMN_CLINICIEN_PRENOM, clinicien.getPrenom());
        values.put(COLUMN_CLINICIEN_ID, clinicien.getIdentifiant());
        values.put(COLUMN_CLINICIEN_MDP, clinicien.getMdp());


        // Inserting Row
        // The unique used for creating table ensures to have only one copy of each team
        // If rowID = -1, an error occured
        long rowID = db.insertWithOnConflict(TABLE_CLINICIEN, null, values, CONFLICT_IGNORE);
        db.close(); // Closing database connection

        return (rowID != -1);

    }

    /**
     * Updates the information of a Clinicien inside the data base
     * @return the number of updated rows
     */
    public int update(Clinicien clinicien) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLINICIEN_NOM, clinicien.getNom());
        values.put(COLUMN_CLINICIEN_PRENOM, clinicien.getPrenom());
        values.put(COLUMN_CLINICIEN_ID, clinicien.getIdentifiant());
        values.put(COLUMN_CLINICIEN_MDP, clinicien.getMdp());

        // updating row
        return db.updateWithOnConflict(TABLE_CLINICIEN, values, COLUMN_CLINICIEN_ID + " = ?",
                new String[] { String.valueOf(clinicien.getIdentifiant()) }, CONFLICT_IGNORE);
    }

    public Clinicien getClinicien(String id, String mdp) {
        Log.i(TAG, "MyDatabaseHelper.getClinicien ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLINICIEN, new String[] { COLUMN_CLINICIEN_ID,
                        COLUMN_CLINICIEN_NOM, COLUMN_CLINICIEN_PRENOM, COLUMN_CLINICIEN_MDP}, COLUMN_CLINICIEN_ID + "= ? AND " + COLUMN_CLINICIEN_MDP +"= ?",
                new String[] { id,mdp }, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Clinicien clinicien = new Clinicien(cursor.getString(cursor.getColumnIndex(COLUMN_CLINICIEN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CLINICIEN_NOM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CLINICIEN_PRENOM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_CLINICIEN_MDP)) );
            cursor.close();
            db.close();
            return clinicien;

        }else{
            cursor.close();
            db.close();
            return null ;
        }
    }

    public String getClinicienInfo(String id) {
        Log.i(TAG, "MyDatabaseHelper.getClinicienInfo ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLINICIEN, new String[] { COLUMN_CLINICIEN_ID,
                        COLUMN_CLINICIEN_NOM, COLUMN_CLINICIEN_PRENOM, COLUMN_CLINICIEN_MDP}, COLUMN_CLINICIEN_ID+ "= ? " ,
                new String[] { id }, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String info = cursor.getString(cursor.getColumnIndex(COLUMN_CLINICIEN_NOM))+" "+cursor.getString(cursor.getColumnIndex(COLUMN_CLINICIEN_PRENOM));
            cursor.close();
            db.close();
            return info;

        }else{
            cursor.close();
            db.close();
            return null ;
        }
    }


    //******************** gestion de Patient ********************

    public int updatePatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_ID, patient.getId());
        values.put(COLUMN_PATIENT_GENRE, patient.getGenre());
        values.put(COLUMN_PATIENT_COMMENTAIRE, patient.getCommentaire());

        // updating row
        return db.updateWithOnConflict(TABLE_PATIENT, values, COLUMN_PATIENT_ID + " = ?",
                new String[] { String.valueOf(patient.getId()) }, CONFLICT_IGNORE);
    }


    public boolean ajoutPatient(Patient patient) {
        Log.i(TAG, "Ajout du patient num :  " + patient.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_ID, patient.getId());
        values.put(COLUMN_PATIENT_GENRE, patient.getGenre());
        values.put(COLUMN_PATIENT_COMMENTAIRE, patient.getCommentaire());

        long rowID = db.insertWithOnConflict(TABLE_PATIENT, null, values, CONFLICT_IGNORE);
        db.close();

        return (rowID != -1);

    }
    public Patient getPatient(String id) {
        Log.i(TAG, "MyDatabaseHelper.getPatient ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT, new String[] { COLUMN_PATIENT_ID,
                        COLUMN_PATIENT_GENRE, COLUMN_PATIENT_COMMENTAIRE}, COLUMN_PATIENT_ID + "= ? ",
                new String[] { id}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Patient patient = new Patient(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_GENRE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_COMMENTAIRE)) );
            cursor.close();
            db.close();
            return patient;

        }else{
            cursor.close();
            db.close();
            return null ;
        }

    }

    /**
     * Returns a cursor on all the Patient of the data base
     */
    public Cursor fetchAllProduct() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PATIENT, null,
                null, null, null, null, COLUMN_PATIENT_ID +" ASC", null);

        Log.d(TAG, "call fetchAllProduct()");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Returns a list on all the MuseumProduct of the data base
     */
    public List<Patient> getAllPatient() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PATIENT, null,
                null, null, null, null, COLUMN_PATIENT_ID +" ASC", null);

        Log.d(TAG, "call getAllPatient()");
        if (cursor != null && cursor.moveToFirst()) {

            List<Patient> res = new ArrayList<>();

            while (!cursor.isAfterLast()){
                Patient patient = new Patient(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_GENRE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_COMMENTAIRE)) );

                res.add(patient);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
            return res;
        }else{
            return null;
        }


    }

    public void deletePatient(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PATIENT, COLUMN_PATIENT_ID + " = ?",
                new String[]{id});
        db.close();
    }


}
