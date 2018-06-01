package com.example.asus.tp1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;

public class OptionsFragment extends Fragment {
    protected final static int primary_colors[] = {
        Color.rgb(244, 67,54),
        Color.rgb(33, 150, 243),
        Color.rgb(63, 81, 181),
        Color.rgb(76, 175, 80),
        Color.rgb(255, 235, 59)
    };

    protected final static int secondary_colors[] = {
        Color.rgb(211, 47,47),
        Color.rgb(25, 118, 210),
        Color.rgb(48, 63, 159),
        Color.rgb(56, 142, 60),
        Color.rgb(251, 192, 45)
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.options_tab, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);

        Button change_color = getActivity().findViewById(R.id.change_color);

        change_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseColor();
            }
        });
    }

    public void chooseColor() {
        CharSequence colors[] = new CharSequence[]{
            getString(R.string.red),
            getString(R.string.blue),
            getString(R.string.indigo),
            getString(R.string.green),
            getString(R.string.yellow)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.choose_color);

        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int index) {
                // On récupère les couleurs avec :
                //
                // this.primary_colors[index];
                // this.secondary_colors[index];
            }
        });

        builder.show();
    }
}
