package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        Button btnEnviar = findViewById(R.id.btnEnviar);
        Intent facturaIntent = new Intent(this, ConfirmarPedido.class);

        cargarArrayList();

        adaptador=new AdaptadorCatalogo(Catalogo.this, listaProductos);
        recView.setAdapter(adaptador);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuariosSQLiteHelper usdbh =
                        new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();

                int maxId=0;

                //Cab pedidos
                db.execSQL("INSERT INTO Cab_pedidos (fecha) VALUES (CURRENT_TIMESTAMP)");
                Cursor c = db.rawQuery("SELECT MAX(idPedido) FROM Cab_pedidos",null);
                if(c.moveToFirst()){
                    maxId=c.getInt(0);
                }

                for(int i = 0; i<adaptador.getListaCompra().size();i++){
                    //Lineas de la cabecera
                    db.execSQL("INSERT INTO Lin_pedidos (linea, idPedido, idAlmacen, cantidad, precio, descripcion) VALUES ("+(i+1)+", "+maxId+", "+adaptador.getListaCompra().get(i).getIdProducto()+", "+adaptador.getListaCompra().get(i).getCantidad()+", "+ adaptador.getListaCompra().get(i).getPrecio()+", '"+adaptador.getListaCompra().get(i).getDescripcion()+"')");

                }

                startActivity(facturaIntent);
            }
        });
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