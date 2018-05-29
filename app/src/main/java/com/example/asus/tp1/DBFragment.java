package com.example.asus.tp1;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.Fragment;

public abstract class DBFragment extends Fragment {
    protected SQLiteDatabase db;
    protected DBOpenHelper dbOpenHelper;

    @Override
    public void onResume() {
        super.onResume();
        openDB();
    }

    @Override
    public void onPause() {
        super.onPause();
        closeDB();
    }

    public void createConnexion() {
        dbOpenHelper = new DBOpenHelper(
            this.getContext(),
            DBOpenHelper.DATABASE_NAME,
            null,
            DBOpenHelper.DATABASE_VERSION
        );

        openDB();
    }

    /**
     * Permet d'avoir accès à la base de données via l'attribut
     * `db` de la classe.
     *
     * @throws SQLiteException
     */
    protected void openDB() throws SQLiteException {
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    /**
     * Permet de fermer la connexion avec la base de données.
     */
    protected void closeDB() {
        db.close();
    }
}
