package com.example.u4_b_a16alejandrobp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogoFragmento extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("Selecciona elementos");
        Resources res = getResources();
        if (U4_B_A16AlejandroBP.aceptar == null || U4_B_A16AlejandroBP.aceptar.length < U4_B_A16AlejandroBP.elementos.size()) {
            U4_B_A16AlejandroBP.aceptar = new boolean[U4_B_A16AlejandroBP.elementos.size()];
            for (int i = 0; i < U4_B_A16AlejandroBP.elementos.size(); i++) {
                U4_B_A16AlejandroBP.aceptar[i] = false;
            }
        }
        if (U4_B_A16AlejandroBP.cancelar == null || U4_B_A16AlejandroBP.cancelar.length < U4_B_A16AlejandroBP.elementos.size()) {
            U4_B_A16AlejandroBP.cancelar = new boolean[U4_B_A16AlejandroBP.elementos.size()];
            for (int i = 0; i < U4_B_A16AlejandroBP.elementos.size(); i++) {
                U4_B_A16AlejandroBP.cancelar[i] = false;
            }
        }
        final String[] matriz = U4_B_A16AlejandroBP.elementos.toArray(new String[U4_B_A16AlejandroBP.elementos.size()]);
        final ArrayList<String> as = new ArrayList<String>();
        // Non incluír mensaxe dentro de este tipo de diálogo!!!
        //builder.setMultiChoiceItems(matriz, bAccept, new DialogInterface.OnMultiChoiceClickListener() {
            builder.setMultiChoiceItems(matriz, U4_B_A16AlejandroBP.aceptar, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int opcion, boolean isChecked) {
                        /*// Evento que ocorre cando o usuario selecciona unha opción
                        if (isChecked){
                           // bCancel=bAccept;
                            Toast.makeText(getActivity().getApplicationContext(), "Seleccionaches " + matriz[opcion], Toast.LENGTH_SHORT).show();

                        }else{
                            //bAccept=bCancel;
                            Toast.makeText(getActivity().getApplicationContext(), "Deseleccionaches " + matriz[opcion], Toast.LENGTH_SHORT).show();
                        }*/
            }
        });
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                String cadea = "Elementos seleccionados: \n";
                System.arraycopy(U4_B_A16AlejandroBP.aceptar,0,U4_B_A16AlejandroBP.cancelar,0,U4_B_A16AlejandroBP.aceptar.length);
                for(int i = 0 ; i < U4_B_A16AlejandroBP.aceptar.length; i++) {
                    if(U4_B_A16AlejandroBP.aceptar[i]) {
                        cadea += "\t" + matriz[i] + "\n";
                    }
                }
                U4_B_A16AlejandroBP.tv.setText(cadea);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                System.arraycopy(U4_B_A16AlejandroBP.cancelar,0,U4_B_A16AlejandroBP.aceptar,0,U4_B_A16AlejandroBP.cancelar.length);
                Toast.makeText(getActivity().getApplicationContext(), "Premeches 'Cancelar'", Toast.LENGTH_LONG).show();
            }
        });
        return builder.create();
    }
}