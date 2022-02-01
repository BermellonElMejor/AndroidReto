package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        Button btnIniciar = findViewById(R.id.btnIniciar);
        EditText editUsuario = findViewById(R.id.editUsuario);
        EditText editPassword = findViewById(R.id.editPwd);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (editUsuario.getText().toString().length() == 0 && editPassword.getText().toString().length() == 0) {
                    Toast mensaje = Toast.makeText(getApplicationContext(), "Introduzca el usuario y la contraseña", Toast.LENGTH_SHORT);
                    mensaje.show();
                } else {
                    String usuario = editUsuario.getText().toString();
                    String contra = editPassword.getText().toString();

                    UsuariosSQLiteHelper usdbh =
                            new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);

                    SQLiteDatabase db = usdbh.getReadableDatabase();

                    Boolean existe = false;

                    Cursor c = db.rawQuery("SELECT usuario, contraseña FROM Comerciales WHERE usuario='" + usuario + "' AND contraseña='" + contra + "'", null);

                    if (c.getCount()>0) {
                        existe = true;
                    }

                    if (existe) {
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                    } else {
                        Toast mensaje = Toast.makeText(getApplicationContext(), "Los datos no coinciden", Toast.LENGTH_LONG);
                        mensaje.show();
                        //prueba
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                    }

                    db.close();
                }
            }
        });
    }

}
