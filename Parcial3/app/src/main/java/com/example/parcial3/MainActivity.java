package com.example.parcial3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.menu_navegacion);
        navigationView.setOnNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_insertar:
                // Acción para la opción Insertar
                Toast.makeText(getApplicationContext(), "Se selecciona Insertar", Toast.LENGTH_LONG).show();

                return true;
            case R.id.menu_borrar:
                // Acción para la opción Borrar
                Toast.makeText(getApplicationContext(), "Se selecciona Borrar", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_actualizar:
                // Acción para la opción Actualizar
                Toast.makeText(getApplicationContext(), "Se selecciona Borrar", Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.busqueda_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buscar_nombre:
                // Acción para buscar por nombre
                Toast.makeText(getApplicationContext(), "Buscar por Nombre ", Toast.LENGTH_LONG).show();
                Busqueda (1,"Nombre");
                return true;
            case R.id.buscar_apellido:
                // Acción para buscar por apellido
                Toast.makeText(getApplicationContext(), "Buscar por Apellido ", Toast.LENGTH_LONG).show();
                Busqueda (2,"Apellido");
                return true;
            case R.id.buscar_telefono:
                // Acción para buscar por teléfono
                Toast.makeText(getApplicationContext(), "Buscar por Apellido ", Toast.LENGTH_LONG).show();
                Busqueda (3,"Telefono");
                return true;
            case R.id.buscar_correo:
                // Acción para buscar por correo
                Toast.makeText(getApplicationContext(), "Buscar por Apellido ", Toast.LENGTH_LONG).show();
                Busqueda (4,"Correo");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Busqueda (int Opcion,String NombreBusqueda){

        /*
        Tipos Busquedas
        1-Nombre
        2-Apellido
        3-Telefono
        4-Correo
        */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buscando por " + NombreBusqueda);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = input.getText().toString().trim();
                //List<Contact> contacts = db.searchContactsByName(name);
                // Display the results in a list or another view
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


}