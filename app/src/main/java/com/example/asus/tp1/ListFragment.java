package com.example.asus.tp1;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListFragment extends DBFragment {
    private float scale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tab = inflater.inflate(R.layout.list_tab, container, false);

        createConnexion();

        // On récupère l'échelle du terminal pour pouvoir calculer les marges en fonction
        // de la densité de pixels
        this.scale = getResources().getDisplayMetrics().density;

        // On liste les contacts disponibles
        ListView root = tab.findViewById(R.id.contacts_list);
        root.setAdapter(getAllContacts());

        return tab;
    }

    /**
     * Permet de récupérer tous les contacts qui ont été stockés en base de données
     * et de les récupérer sous forme d'instances de la classe `Contact`.
     *
     * @return Liste de tous les contacts
     */
    public ListAdapter getAllContacts() {
        Cursor cursor = db.rawQuery("SELECT rowid _id, * FROM " + DBOpenHelper.TABLE_NAME, null);

        return new CursorAdapter(getContext(), cursor) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                return contactView(cursor);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

            }
        };
    }

    /**
     * Permet de créer une `View` pour un contact représenté par un curseur.
     *
     * @param cursor - Le curseur représentant l'utilisateur
     * @return Un LinearLayout représentant le contact visuelement
     */
    protected LinearLayout contactView(Cursor cursor) {
        Contact c = contactFromCursor(cursor);

        LinearLayout contactView = new LinearLayout(getActivity());
        contactView.setOrientation(LinearLayout.VERTICAL);
        contactView.setGravity(Gravity.CLIP_HORIZONTAL);

        LinearLayout.LayoutParams nameParams   = newParams();
        LinearLayout.LayoutParams numberParams = newParams();

        nameParams.setMargins(pxToDp(20), pxToDp(15), pxToDp(20), pxToDp(10));
        numberParams.setMargins(pxToDp(20), pxToDp(5), pxToDp(20), pxToDp(10));

        final TextView name   = new TextView(getActivity());
        final TextView number = new TextView(getActivity());

        name.setText(new StringBuilder(c.getPrenom()).append(" ").append(c.getNom()));
        name.setTextSize(25);
        name.setLayoutParams(nameParams);

        number.setText(c.getNumero());
        number.setTextSize(18);
        number.setLayoutParams(numberParams);

        contactView.addView(name);
        contactView.addView(number);

        return contactView;
    }

    /**
     * Permet de caster un curseur de base de données en instance de la classe
     * `Contact`.
     *
     * @param cursor
     * @return Le contact stocké au niveau du curseur
     */
    protected Contact contactFromCursor(Cursor cursor) {
        Contact contact = new Contact();

        // La valeur à l'index 0 ici est "rowid"/"_id" qui est un champ
        // requis par CursorAdapter pour fonctionner correctement.

        contact.setId(cursor.getInt(1));
        contact.setPrenom(cursor.getString(2));
        contact.setNom(cursor.getString(3));
        contact.setNumero(cursor.getString(4));
        contact.setSexe(cursor.getString(5));

        return contact;
    }

    /**
     * Permet de récupérer une nouvelle instance de LayoutParams
     * afin d'appliquer des marges aux éléments de l'interface.
     */
    protected LinearLayout.LayoutParams newParams() {
        return new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
    }

    /**
     * Permet de convertir des px en dp pour avoir le même rendu sur
     * tous les terminaux.
     *
     * @param px - La valeur en pixels.
     */
    protected int pxToDp(int px) {
        return (int)(scale * px+ 0.5f);
    }
}
