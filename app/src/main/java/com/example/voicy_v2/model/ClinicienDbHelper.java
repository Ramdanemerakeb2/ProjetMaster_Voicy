package com.example.voicy_v2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;
import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

public class ClinicienDbHelper extends SQLiteOpenHelper {

    private static final String TAG = ClinicienDbHelper.class.getSimpleName();


    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Voicy.db";

    public static final String TABLE_NAME = "Clinicien";

    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_CLINICIEN_ID = "idClinicien";
    public static final String COLUMN_CLINICIEN_MDP = "mdpClinicien";

    public ClinicienDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_CLINICIEN_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_CLINICIEN_ID + " TEXT PRIMARY KEY," +
                COLUMN_NOM + " TEXT NOT NULL, " +
                COLUMN_PRENOM + " TEXT, " +
                COLUMN_CLINICIEN_MDP + " TEXT, " +

                // To assure the application have just one team entry per
                // product name , it's created a UNIQUE
                " UNIQUE (" + COLUMN_CLINICIEN_ID + ") ON CONFLICT ROLLBACK);";

        db.execSQL(SQL_CREATE_CLINICIEN_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Adds a new Clinicien
     * @return  true if the Clinicien was added to the table ; false otherwise (case when Clinicien is
     * already in the data base)
     */

    public boolean addClinicien(Clinicien clinicien) {
        Log.i(TAG, "Ajout du clinicien ... " + clinicien.getNom());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, clinicien.getNom());
        values.put(COLUMN_PRENOM, clinicien.getPrenom());
        values.put(COLUMN_CLINICIEN_ID, clinicien.getIdentifiant());
        values.put(COLUMN_CLINICIEN_MDP, clinicien.getMdp());


        // Inserting Row
        // The unique used for creating table ensures to have only one copy of each team
        // If rowID = -1, an error occured
        long rowID = db.insertWithOnConflict(TABLE_NAME, null, values, CONFLICT_IGNORE);
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
        values.put(COLUMN_NOM, clinicien.getNom());
        values.put(COLUMN_PRENOM, clinicien.getPrenom());
        values.put(COLUMN_CLINICIEN_ID, clinicien.getIdentifiant());
        values.put(COLUMN_CLINICIEN_MDP, clinicien.getMdp());

        // updating row
        return db.updateWithOnConflict(TABLE_NAME, values, COLUMN_CLINICIEN_ID + " = ?",
                new String[] { String.valueOf(clinicien.getIdentifiant()) }, CONFLICT_IGNORE);
    }

    public Clinicien getClinicien(String id, String mdp) {
        Log.i(TAG, "MyDatabaseHelper.getClinicien ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_CLINICIEN_ID,
                        COLUMN_NOM, COLUMN_PRENOM, COLUMN_CLINICIEN_MDP}, COLUMN_CLINICIEN_ID + "= ? AND " + COLUMN_CLINICIEN_MDP +"= ?",
                new String[] { id,mdp }, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Clinicien clinicien = new Clinicien(cursor.getString(cursor.getColumnIndex(COLUMN_CLINICIEN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOM)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PRENOM)),
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


}
