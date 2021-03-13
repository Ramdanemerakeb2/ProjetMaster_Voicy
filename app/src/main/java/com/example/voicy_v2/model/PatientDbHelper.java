package com.example.voicy_v2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

public class PatientDbHelper extends SQLiteOpenHelper {

    private static final String TAG = PatientDbHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Voicy1.db";

    public static final String TABLE_NAME = "patient";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_COMMENTAIRE = "commentaire";


    public PatientDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY," +
                COLUMN_GENRE + " TEXT , " +
                COLUMN_COMMENTAIRE + " TEXT, " +

                // To assure the application have just one team entry per
                // product name , it's created a UNIQUE
                " UNIQUE (" + COLUMN_ID + ") ON CONFLICT ROLLBACK);";

        sqLiteDatabase.execSQL(SQL_CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean ajoutPatient(Patient patient) {
        Log.i(TAG, "Ajout du patient num :  " + patient.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, patient.getId());
        values.put(COLUMN_GENRE, patient.getGenre());
        values.put(COLUMN_COMMENTAIRE, patient.getCommentaire());

        // Inserting Row
        // The unique used for creating table ensures to have only one copy of each team
        // If rowID = -1, an error occured
        long rowID = db.insertWithOnConflict(TABLE_NAME, null, values, CONFLICT_IGNORE);
        db.close(); // Closing database connection

        return (rowID != -1);

    }

}
