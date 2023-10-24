package com.example.testexamen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.testexamen.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CRUDRecetas CRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAgregar= findViewById(R.id.btnAgregar);
        Button btnEliminar= findViewById(R.id.btnEliminar);
        Button btnEditar= findViewById(R.id.btnEditar);
        EditText edNombre = findViewById(R.id.etBDNombre);
        EditText etBDDescripcion = findViewById(R.id.etBDDescripcion);
        EditText etBDID = findViewById(R.id.etBDID);
        CRUD = new CRUDRecetas(this);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String elNombre= edNombre.getText().toString();
                String laDescripcion= etBDDescripcion.getText().toString();
                if (elNombre.isEmpty() || laDescripcion.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese los datos faltantes", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        CRUD.insertarReceta(elNombre,laDescripcion);
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Ingrese los datos faltantes", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });

        ListView listaRecetas = findViewById(R.id.listarecetas);
        Cursor informacion = CRUD.mostrarRecetas();

        List<String> listaRecetitas = new ArrayList<>();

        while (informacion.moveToNext()) {
            String titulo = informacion.getString(1);
            listaRecetitas.add(titulo);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaRecetitas);

        listaRecetas.setAdapter(adapter);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elID= etBDID.getText().toString();
                if (elID.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Ingrese los datos faltantes", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        CRUD.eliminarReceta(Integer.parseInt(elID));
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Ingrese los datos faltantes", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             CRUD.actualizarReceta(Integer.parseInt(etBDID.getText().toString()),edNombre.getText().toString(),etBDDescripcion.getText().toString());
            }
        });







    }}