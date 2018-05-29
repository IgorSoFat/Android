package com.example.asus.tp1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class NextActivityAdd extends Activity {

    private SQLiteDatabase db;
    DBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);

        setContentView(R.layout.activity_add);
        Button buttonPrev = (Button) findViewById(R.id.buttonPrev);
        buttonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.i("OC_RSS","Ca marche");
                Intent myIntent = new Intent(NextActivityAdd.this,NextActivity1.class);
                startActivity(myIntent);
            }
        });

       /*

                        contactManager.open();
                        contactManager.addContact(contact);
                        contactManager.dbm.close();

                        Intent intent = new Intent(AjouterContact.this, MainActivity.class);
                        startActivity(intent);
                        AjouterContact.this.finish();
                    }
                }

            });
        }
*/
        Button insert = (Button) findViewById(R.id.buttonInsert);

        EditText editNom = (EditText) findViewById(R.id.editNom);
        EditText editPrenom = (EditText) findViewById(R.id.editPrenom);
        EditText editNumero = (EditText) findViewById(R.id.editNum);
        RadioButton radioHomme = (RadioButton) findViewById(R.id.radioHomme);
        RadioButton radioFemme = (RadioButton) findViewById(R.id.radioFemme);
        String sexe = null;
        if(radioHomme.callOnClick()) {
            sexe = "Homme";
        } else {
            sexe =  "Femme";
        }

        Contact c = new Contact(
               editPrenom.getText().toString(),
                editNom.getText().toString(),
                editNumero.getText().toString(),
                sexe);

        dbOpenHelper = new DBOpenHelper(this,DBOpenHelper.DATABASE_NAME,null,DBOpenHelper.DATABASE_VERSION);

        openDB();

        ContentValues contentValues = new ContentValues();
        long rowId = insertRecord(c);

        rowId = updateRecord(contentValues, rowId, c);

        queryTheDatabase(c);

        deleteRecord(rowId,c);

    }

    @Override
    protected void onResume() {
        super.onResume();
        openDB();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDB();
    }

    public void openDB() throws SQLiteException {
        try {
            db = dbOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }
    public void closeDB() {
        db.close();
    }

    private long insertRecord(Contact c) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.ID,c.getId());
        contentValues.put(DBOpenHelper.PRENOM,c.getPrenom());
        contentValues.put(DBOpenHelper.NOM,c.getNom());
        contentValues.put(DBOpenHelper.NUMERO,c.getNumero());
        contentValues.put(DBOpenHelper.SEXE,c.getSexe());

        long rowId = db.insert(DBOpenHelper.TABLE_NAME, null, contentValues);

        if (rowId == -1) {
            Toast.makeText(this, "Contact non cree",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Contact cree dans la BDD",
                    Toast.LENGTH_LONG).show();
        }
        return rowId;
    }

    private long updateRecord(ContentValues contentValues, long rowId, Contact c) {
        contentValues.clear();
        contentValues.put(DBOpenHelper.PRENOM,c.getPrenom());
        contentValues.put(DBOpenHelper.NOM,c.getNom());
        rowId = db.update(DBOpenHelper.TABLE_NAME,contentValues,DBOpenHelper.ID + "="+ rowId,null);

        if (rowId == -1) {
            Toast.makeText(this, "Contact non modifie", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Contact modifie dans la BDD", Toast.LENGTH_LONG).show();
        }
        return rowId;
    }

    private void deleteRecord(long rowId, Contact c) {
        rowId = db.delete(DBOpenHelper.TABLE_NAME,
                DBOpenHelper.ID + "=" + rowId, null);
        if (rowId == -1) {
            Toast.makeText(this, "Contact non supprime", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Contact supprime", Toast.LENGTH_LONG).show();
        }
    }

    private void queryTheDatabase(Contact c) {
        String[] projections = new String[] { DBOpenHelper.ID,
                DBOpenHelper.PRENOM,DBOpenHelper.NOM};
        final int cursorIdColNumber = 0, cursorNameColNumber = 1, cursorFirstNameColNumber = 2;
        String selection = DBOpenHelper.NUMERO + "=?";
        String[] selectionArg = new String[] {c.getNumero()};
        String groupBy = c.getSexe();
        String having = null;
        String orderBy = null;
        String maxResultsListSize = "60";
        Cursor cursor = db.query(DBOpenHelper.TABLE_NAME, projections, selection,
                selectionArg, groupBy, having, orderBy, maxResultsListSize);
        displayResults(cursor);

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DBOpenHelper.TABLE_NAME);
        qb.setDistinct(true);
        cursor = qb.query(db, projections, selection, selectionArg, groupBy,
                having, orderBy);
        displayResults(cursor);
    }

    private void displayResults(Cursor cursor) {
        if (cursor.moveToFirst()) {
            Integer colId;
            String prenom;
            String nom;
            int indexId = cursor.getColumnIndex(DBOpenHelper.ID);
            int indexPrenom = cursor.getColumnIndex(DBOpenHelper.PRENOM);
            int indexNom = cursor.getColumnIndex(DBOpenHelper.NOM);
            int count = 0;
            do {
                colId = cursor.getInt(indexId);
                prenom = cursor.getString(indexPrenom);
                nom = cursor.getString(indexNom);
                Toast.makeText(
                        this,
                        "Recherche de l'element :" + prenom + "," + nom + " ("
                                + colId + ")", Toast.LENGTH_LONG).show();
                count++;
            } while (cursor.moveToNext());
            Toast.makeText(this,
                    "Il y a  " + count + " elements",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Pas d'element trouve : ", Toast.LENGTH_LONG)
                    .show();
        }
    }

}
