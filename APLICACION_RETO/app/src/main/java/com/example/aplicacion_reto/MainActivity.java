package com.example.aplicacion_reto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int REQUES_CODE2=2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  Button btnPruebaBDD = findViewById(R.id.btnPruebaBDD);

        ImageButton boton_mapa=findViewById(R.id.boton_mapa);
        ImageButton boton_telefono=findViewById(R.id.boton_telefono);
        ImageButton boton_gmail=findViewById(R.id.boton_gmail);
        ImageButton boton_calendario=findViewById(R.id.boton_calendario);
        ImageButton boton_partner=findViewById(R.id.boton_partner);
        ImageButton boton_pedidos=findViewById(R.id.boton_pedidos);
        ImageButton boton_delegaciones=findViewById(R.id.boton_delegaciones);


        Intent calendarioIntent =new Intent(this, CalendarioActivity.class);
        Intent partnersIntent =new Intent(this, PartnersActivity.class);
        //Intent pedidosIntent =new Intent(this, falta el layout .class);
        Intent enviosIntent =new Intent(this, activity_envios.class);


        verificarPermisos();



        boton_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=43.30472651965252,-2.01687088777822");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        boton_telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="tel:+34688843432";
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        boton_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="mailto:aunai888@gmail.com";
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        boton_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(calendarioIntent);
            }
        });

        boton_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(partnersIntent);
            }
        });

        boton_pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(pedidosIntent);
            }
        });

        boton_delegaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(enviosIntent);
            }
        });


//        btnPruebaBDD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                UsuariosSQLiteHelper usdbh =
//                        new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
//
//                SQLiteDatabase db = usdbh.getWritableDatabase();
//
//                //Si hemos abierto correctamente la base de datos
//                if(db != null)
//                {
//                    //Insertamos 5 usuarios de ejemplo
//
//                        db.execSQL("INSERT INTO Almacen_Deleg (descripcion) " +
//                                "VALUES ('Ejemplo')");
//                    //Cerramos la base de datos
//                    db.close();
//                }
//            }
//        });



    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos(){
        int PermisoLectura=ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int PermisoEscritura=ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int PermisoManage=ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE);

//        if (PermisoLectura == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this,"Permiso de lectura obtenido",Toast.LENGTH_SHORT);
//        }else{
//            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUES_CODE1);
//        }

        if (PermisoEscritura == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permiso de escritura obtenido",Toast.LENGTH_SHORT);
        }else{
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUES_CODE2);
        }
//
//        if (PermisoManage == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this,"Permiso manage obtenido",Toast.LENGTH_SHORT);
//        }else{
//            requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},REQUES_CODE3);
//        }

    }
}