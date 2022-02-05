package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    private List<Producto> listaProductos = new ArrayList<>();
    private AdaptadorCatalogo adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        RecyclerView recView = findViewById(R.id.idRecycleViewCitas);
        recView.setLayoutManager(new GridLayoutManager(Catalogo.this, 1));

        cargarArrayList();


        adaptador=new AdaptadorCatalogo(Catalogo.this, listaProductos);
        recView.setAdapter(adaptador);

    }

    public void cargarArrayList(){

        int codigo;
        String descripcion;
        float precio;

        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);

        SQLiteDatabase db = usdbh.getReadableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM Almacen_Deleg",null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                codigo= c.getInt(0);
                descripcion = c.getString(1);
                precio = c.getFloat(2);

                listaProductos.add(new Producto(codigo, descripcion, precio));
            } while(c.moveToNext());
        }



    }
}