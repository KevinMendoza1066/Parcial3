package com.example.parcial3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.parcial3.Helper.SQLiteHelper;
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    EditText txtNombre,txtApellido,txtTelefono,txtCorreo,txtId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre=findViewById(R.id.edtNombre);
        txtApellido=findViewById(R.id.edtApellidos);
        txtTelefono=findViewById(R.id.edtTelefono);
        txtCorreo=findViewById(R.id.edtCorreo);
        txtId=findViewById(R.id.edtID);
        BottomNavigationView navigationView = findViewById(R.id.menu_navegacion);
        navigationView.setOnNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_insertar:
                // Acción para la opción Insertar
                Toast.makeText(getApplicationContext(), "Se selecciona Insertar", Toast.LENGTH_LONG).show();
                insertarProducto();
                limpiarCampos();
                return true;
            case R.id.menu_borrar:
                // Acción para la opción Borrar
                Toast.makeText(getApplicationContext(), "Se selecciona Borrar", Toast.LENGTH_LONG).show();
                Borrar();
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
                String Dato = input.getText().toString().trim();
                SQLiteHelper admin = new SQLiteHelper(getApplicationContext(), "Parcial", null, 2);
                SQLiteDatabase bd = admin.getWritableDatabase();
                String Consulta ="";

                switch (Opcion){
                    case 1:
                        Consulta= "SELECT IdContacto, Nombre,Apellidos,Telefono,Correo  FROM Contactos WHERE Nombre = '" + Dato + "' LIMIT 1";
                        break;
                    case 2:
                        Consulta= "SELECT IdContacto, Nombre,Apellidos,Telefono,Correo  FROM Contactos WHERE Apellidos = '" + Dato + "' LIMIT 1";
                        break;
                    case 3:
                        Consulta= "SELECT IdContacto, Nombre,Apellidos,Telefono,Correo  FROM Contactos WHERE Telefono  = '" + Dato + "' LIMIT 1";
                        break;
                    case 4:
                        Consulta= "SELECT IdContacto, Nombre,Apellidos,Telefono,Correo  FROM Contactos WHERE Correo = '" + Dato + "' LIMIT 1";
                        break;
                }

                Cursor filas = bd.rawQuery(Consulta, null);

                if (filas.moveToFirst()) {
                    txtId.setText(filas.getString(0));
                    txtNombre.setText(filas.getString(1));
                    txtApellido.setText(filas.getString(2));
                    txtTelefono.setText(filas.getString(3));
                    txtCorreo.setText(filas.getString(4));

                } else {
                    Toast.makeText(getApplicationContext(), "No se encontró ningún contacto con ese "+NombreBusqueda, Toast.LENGTH_LONG).show();
                }

                bd.close();
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
    public void insertarProducto() {
        SQLiteHelper admin = new SQLiteHelper(getApplicationContext(), "Parcial", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Nombre = txtNombre.getText().toString();
        String Apellido = txtApellido.getText().toString();
        String Telefono = txtTelefono.getText().toString();
        String Correo = txtCorreo.getText().toString();

        ContentValues informacion = new ContentValues();
        informacion.put("Nombre", Nombre);
        informacion.put("Apellidos", Apellido);
        informacion.put("Telefono", Telefono);
        informacion.put("Correo", Correo);


        try {
            bd.insert("Contactos", null, informacion);
            Toast.makeText(getApplicationContext(), "Se insertó el Contacto", Toast.LENGTH_LONG).show();
            bd.close();
        } catch (Exception e) {
            //pendiente imprimir error
            Toast.makeText(getApplicationContext(), "" + e.getCause(), Toast.LENGTH_LONG).show();
        }
    }
    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtId.setText("");
    }
    private void Borrar(){
        SQLiteHelper admin = new SQLiteHelper(getApplicationContext(),"Parcial",null,2);
        SQLiteDatabase bd= admin.getWritableDatabase();
        String Id=txtId.getText().toString();
        if(!TextUtils.isEmpty(Id)){
        int result=bd.delete("Contactos",
                "IdContacto="+Id,null);
        bd.close();


        if(result==1){
            Toast.makeText(getApplicationContext(),"Se borro el contacto",Toast.LENGTH_LONG).show();
            limpiarCampos();
        }
        else{
            Toast.makeText(getApplicationContext(),"No se borro el contacto",Toast.LENGTH_LONG).show();

        }
        }else{
            Toast.makeText(getApplicationContext(),"ID vacio , realice la busqueda antes",Toast.LENGTH_LONG).show();

        }
    }
    private void Actualizar(){
        SQLiteHelper admin = new SQLiteHelper(getApplicationContext(),"Parcial",null,2);
        SQLiteDatabase bd= admin.getWritableDatabase();
        String Id=txtId.getText().toString();
        String Nombre = txtNombre.getText().toString();
        String Apellido = txtApellido.getText().toString();
        String Telefono = txtTelefono.getText().toString();
        String Correo = txtCorreo.getText().toString();

        ContentValues informacion = new ContentValues();
        informacion.put("Nombre", Nombre);
        informacion.put("Apellidos", Apellido);
        informacion.put("Telefono", Telefono);
        informacion.put("Correo", Correo);
        if(!TextUtils.isEmpty(Id)){
            int result=bd.update("Contactos",informacion,
                    "IdContacto="+Id,null);
            bd.close();

            if(result==1){
                Toast.makeText(getApplicationContext(),"Se Actualizo el contacto",Toast.LENGTH_LONG).show();
                limpiarCampos();
            }
            else{
                Toast.makeText(getApplicationContext(),"No se Actualizo el contacto",Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(getApplicationContext(),"ID vacio , realice la busqueda antes",Toast.LENGTH_LONG).show();

        }
    }
}