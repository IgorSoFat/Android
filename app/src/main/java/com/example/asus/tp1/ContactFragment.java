package com.example.asus.tp1;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactFragment extends DBFragment {
    private float scale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View tab = inflater.inflate(R.layout.contact_tab, container, false);

        //REvenir a issou
        final Button prev = (Button) tab.findViewById(R.id.buttonPrevContact) ;

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).viewPager.setCurrentItem(0);
            }
        });

        createConnexion();

        // On récupère l'échelle du terminal pour pouvoir calculer les marges en fonction
        // de la densité de pixels
        this.scale = getResources().getDisplayMetrics().density;
        return tab;
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
