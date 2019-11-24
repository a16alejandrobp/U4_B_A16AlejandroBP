package com.example.u4_b_a16alejandrobp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class U4_B_A16AlejandroBP extends AppCompatActivity {
    private DialogoFragmento dialogoFragmento = new DialogoFragmento();
    private static final int DIALOGO_CHECK_BOX = 5;
    EditText et;
    public static TextView tv;
    AlertDialog.Builder venta;
    public static ArrayList<String> elementos;
    public static boolean[] aceptar;
    public static boolean[] cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.tv);
        elementos = new ArrayList<String>();

    }
    @Override
    protected void onSaveInstanceState(Bundle estado) {
        estado.putBooleanArray("ACEPTAR",aceptar);
        estado.putBooleanArray("CANCELAR",cancelar);
        estado.putStringArrayList("ELEMENTOS",elementos);
        estado.putString("VALOR_TEXTVIEW",tv.getText().toString());
        super.onSaveInstanceState(estado);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        aceptar = savedInstanceState.getBooleanArray("ACEPTAR").clone();
        cancelar = savedInstanceState.getBooleanArray("CANCELAR").clone();
        elementos = (ArrayList<String>) savedInstanceState.getStringArrayList("ELEMENTOS").clone();
        tv.setText(savedInstanceState.getString("VALOR_TEXTVIEW"));
    }
    protected Dialog onCreateDialog(int id) {
        venta = new AlertDialog.Builder(this);
        venta.setIcon(android.R.drawable.ic_dialog_info);
        venta.setTitle("Selecciona elementos");
        Resources res = getResources();
        if (aceptar == null | aceptar.length < elementos.size()) {
            aceptar = new boolean[elementos.size()];
            for (int i = 0; i < elementos.size(); i++) {
                aceptar[i] = false;
            }
        }
        if (cancelar == null | cancelar.length < elementos.size()) {
            cancelar = new boolean[elementos.size()];
            for (int i = 0; i < elementos.size(); i++) {
                cancelar[i] = false;
            }
        }
        final String[] matriz = elementos.toArray(new String[elementos.size()]);
        // Non incluír mensaxe dentro de este tipo de diálogo!!!
        venta.setMultiChoiceItems(matriz, aceptar, new DialogInterface.OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int opcion, boolean isChecked) {
                // Evento que ocorre cando o usuario selecciona unha opción
                aceptar[opcion] = isChecked;
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Seleccionaches " + matriz[opcion], Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Deseleccionaches " + matriz[opcion], Toast.LENGTH_SHORT).show();
                }
            }
        });
        venta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                String cadea = "Elementos seleccionados: \n";
                Toast.makeText(getApplicationContext(), "Premches 'Aceptar'", 1).show();
                for(int i = 0 ; i < aceptar.length; i++) {
                    if(aceptar[i]) {
                        cadea += "\t" + matriz[i] + "\n";
                    }
                }
                tv.setText(cadea);
            }
        });
        venta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                Toast.makeText(getApplicationContext(), "Premeches 'Cancelar'", 1).show();
            }
        });
        return venta.create();
    }
    public void onShowDialogClick(View view) {
        if(elementos.isEmpty()){
            Toast.makeText(this,"Non se engadiu ningún elemento",Toast.LENGTH_LONG).show();
        }else{
            showDialog(DIALOGO_CHECK_BOX);
        }
    }

    public void onEngadirElementoClick(View view) {
        elementos.add(et.getText().toString());
        et.setText("");
    }

    public void onDialogFragmentClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        dialogoFragmento.show(fm, "cb");
    }
}
