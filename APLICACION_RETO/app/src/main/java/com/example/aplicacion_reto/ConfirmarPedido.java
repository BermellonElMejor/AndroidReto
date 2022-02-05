package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        TextView txtFactura = findViewById(R.id.txtFactura);

        String facturaFinal = "";
        int maxId=0;
        int linea=0, cantidad=0;
        float precio=0;
        String nombreProducto="";

        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT MAX(idPedido) FROM Cab_Pedidos",null);

        if(c.moveToFirst()){
            maxId=c.getInt(0);
        }
        c.close();

        facturaFinal+="ID DE PEDIDO: "+maxId+"\n\n";

        Cursor c2 = db.rawQuery("SELECT linea, cantidad, precio, descripcion FROM Lin_Pedidos WHERE idPedido = "+maxId,null);

        if (c2.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                linea=c2.getInt(0);
                cantidad=c2.getInt(1);
                precio=c2.getFloat(2);
                nombreProducto=c2.getString(3);

                facturaFinal+="Linea número "+linea+"\nArtículo: "+nombreProducto+"\nCantidad: "+cantidad+"\n"+"Precio unitario: "+precio+"\n\n";

            } while(c2.moveToNext());
        }

        c2.close();
        db.close();
        txtFactura.setText(facturaFinal);
    }
}