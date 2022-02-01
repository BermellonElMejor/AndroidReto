package com.example.aplicacion_reto;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AltaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        EditText edtNombre = findViewById(R.id.txt_modificar_nombre);
        EditText edtDireccion = findViewById(R.id.txt_modificar_direccion);
        EditText edtPoblacion = findViewById(R.id.txt_modificar_poblacion);
        EditText edtCIF = findViewById(R.id.txt_modificar_cif);
        EditText edtTelefono = findViewById(R.id.txt_modificar_telefono);
        EditText edtEmail = findViewById(R.id.txt_modificar_email);
        EditText edtComercial = findViewById(R.id.txt_modificar_comercial);
        Button DarAltaBtn = findViewById(R.id.btn_modificar_buscar);

        DarAltaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vnombre = edtNombre.getText().toString();
                String vdireccion = edtDireccion.getText().toString();
                String vpoblacion = edtPoblacion.getText().toString();
                String vcif = edtCIF.getText().toString();
                String vtelefono = edtTelefono.getText().toString();
                String vemail = edtEmail.getText().toString();
                String vcomercial = edtComercial.getText().toString();
                if(edtNombre.length()!=0 && edtDireccion.length()!=0 && edtPoblacion.length()!=0 && edtCIF.length()!=0 && edtTelefono.length()!=0 && edtEmail.length()!=0 && edtComercial.length()!=0) {
                    UsuariosSQLiteHelper usdbh =
                            new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);

                    SQLiteDatabase db = usdbh.getWritableDatabase();



                    //Si hemos abierto correctamente la base de datos
                    if (db != null) {
                        //Insertamos 5 usuarios de ejemplo
                        Cursor c = db.rawQuery("SELECT idComercial FROM Comerciales WHERE idComercial = " + vcomercial,null);

                        if(c.moveToFirst()){
                            db.execSQL("INSERT INTO Partners (nombre, direccion, poblacion, cif, telefono, email, idComercial) " +
                                    "VALUES ('" + vnombre +"', '"+ vdireccion +"', '"+ vpoblacion +"', '"+ vcif +"', '"+ vtelefono +"', '"+ vemail +"', "+ vcomercial +")");
                            //Cerramos la base de datos
                            Toast correcto = Toast.makeText(getApplicationContext(), "El partner se ha dado de alta correctamente.", Toast.LENGTH_SHORT);
                            correcto.show();
                        }else{
                            Toast caviso = Toast.makeText(getApplicationContext(), "El comercial introducido no existe, introduzca uno existente por favor.", Toast.LENGTH_SHORT);
                            caviso.show();
                        }


                        db.close();
                    }
                }else{
                    Toast aviso = Toast.makeText(getApplicationContext(), "Introduzca todos los datos por favor.", Toast.LENGTH_SHORT);
                    aviso.show();
                }
            }
        });


    }
}