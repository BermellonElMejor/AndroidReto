package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity_envios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);

        Button boton_enviar_partners=findViewById(R.id.btnEnviarPartners);
        Button boton_enviar_pedidos=findViewById(R.id.btnEnviarPedidos);


        boton_enviar_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuariosSQLiteHelper usdbh =
                        new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);

                SQLiteDatabase db = usdbh.getReadableDatabase();


                Boolean existe = false;

                Cursor c = db.rawQuery("SELECT Cab_Pedidos.*,Lin_Pedidos.* FROM Cab_Pedidos,Lin_Pedidos WHERE Cab_Pedidos.idPedido=Lin_Pedidos.idPedido", null);

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
        });


        boton_enviar_partners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}