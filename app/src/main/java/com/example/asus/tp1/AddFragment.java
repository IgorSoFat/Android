package com.example.asus.tp1;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddFragment extends DBFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_tab, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createConnexion();

        // +-----------------------------------+
        // | Gestion de l'envoie du formulaire |
        // +-----------------------------------+
        final Button insert = (Button) getActivity().findViewById(R.id.buttonInsert);

        final EditText editNom       = getActivity().findViewById(R.id.editNom);
        final EditText editPrenom    = getActivity().findViewById(R.id.editPrenom);
        final EditText editNumero    = getActivity().findViewById(R.id.editNum);
        final RadioGroup radioGroup  = getActivity().findViewById(R.id.radioSexe);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                String sexe = (radioGroup.getCheckedRadioButtonId()== R.id.radioH) ? " Homme" : "Femme";
                Contact c = new Contact(
                    editPrenom.getText().toString(),
                    editNom.getText().toString(),
                    editNumero.getText().toString(),
                    sexe
                );

                // Si l'insertion de l'utilisateur a réussi, on redirige vers la liste
                // de contacts.
                if (insertRecord(c) != -1) {
                    ((MainActivity)getActivity()).viewPager.setCurrentItem(2);
                }
            }
        });
    }

    /**
     * Permet d'insérer une instance de la classe `Contact` dans la base
     * de données.
     *
     * @param c - Le contact à insérer
     * @return L'id du nouveau contact
     */
    protected long insertRecord(Contact c) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBOpenHelper.PRENOM, c.getPrenom());
        contentValues.put(DBOpenHelper.NOM,    c.getNom());
        contentValues.put(DBOpenHelper.NUMERO, c.getNumero());
        contentValues.put(DBOpenHelper.SEXE,   c.getSexe());

        long rowId = db.insert(DBOpenHelper.TABLE_NAME, null, contentValues);

        if (rowId == -1) {
            Toast.makeText(this.getContext(), "Erreur lors de la création", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.getContext(), "Contact créé avec succès", Toast.LENGTH_LONG).show();
        }

        return rowId;
    }
}
