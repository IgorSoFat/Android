package com.example.asus.tp1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myData.db";
    public static final int DATABASE_VERSION = 1;


    public static final String ID     = "id";
    public static final String PRENOM = "prenom";
    public static final String NOM    = "nom";
    public static final String NUMERO = "numero";
    public static final String SEXE   = "sexe";

    public static final String TABLE_NAME = "Contact";
    public static final String TABLE_CREATE = new StringBuilder("CREATE TABLE ")
                                                .append(TABLE_NAME)
                                                .append("(")
                                                .append(ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                                                .append(PRENOM).append(" TEXT,")
                                                .append(NOM).append(" TEXT,")
                                                .append(NUMERO).append(" VARCHAR(10),")
                                                .append(SEXE).append(" TEXT);").toString();

    public static final String TABLE_DROP = " DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }
}
