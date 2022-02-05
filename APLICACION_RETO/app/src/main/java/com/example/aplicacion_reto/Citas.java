package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Citas extends AppCompatActivity {

    private List<Cita> listaCitas = new ArrayList<>();
    private AdaptadorCitas adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        RecyclerView recView = findViewById(R.id.idRecycleViewCitas);
        recView.setLayoutManager(new GridLayoutManager(Citas.this, 1));

        cargarArrayList();


        adaptador=new AdaptadorCitas(Citas.this, listaCitas);
        recView.setAdapter(adaptador);
    }

    public void cargarArrayList(){

        String fecha,hora,descripcion;

        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);

        SQLiteDatabase db = usdbh.getReadableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM Citas",null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                fecha=c.getString(1);
                descripcion=c.getString(2);
                hora=c.getString(3);

                //falta el date

                listaCitas.add(new Cita(fecha, hora, descripcion));
            } while(c.moveToNext());
        }



    }
}