package com.example.asus.tp1;

import android.database.sqlite.SQLiteDatabase;

public class ContactDAO {
    public static final String TABLE_NAME = "Contact";
    public static final String ID = "id";
    public static final String PRENOM = "prenom";
    public static final String NOM = "nom";
    public static final String NUMERO = "numero";
    public static final String POSTE = "poste";

    public static final String TABLE_CREATE =
            "CREATE TABLE "+TABLE_NAME+" ("+
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    PRENOM + " TEXT, " +
                    NOM + " TEXT, " +
                    NUMERO + " TEXT, "+
                    POSTE + " TEXT);";

    public void ajouter(Contact c) {
        String req = "INSERT INTO " +TABLE_NAME+" ( " +
                PRENOM + ", " +
                NOM + ", " +
                NUMERO + ", " +
                POSTE + ") VALUES ( " +
                c.getPrenom() + ", " +
                c.getNom() + ", " +
                c.getNumero() + ", " +
                c.getSexe() + ");";

    }
}
