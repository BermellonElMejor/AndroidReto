package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DarBajaPartners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baja_partners);

        EditText txt_baja_IdPartner=findViewById(R.id.txt_baja_IdPartner);
        TextView txt_baja_nombre=findViewById(R.id.txt_baja_nombre);
        Button btn_eliminar=findViewById(R.id.btn_eliminar);
        Button btn_baja_buscar=findViewById(R.id.btn_baja_buscar);

        btn_baja_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_baja_IdPartner.getText().toString().length()!=0){
                    UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    int id=Integer.parseInt(txt_baja_IdPartner.getText().toString());

                    Cursor fila=db.rawQuery
                            ("select nombre from Partners WHERE idPartner="+id, null);

                    if(fila.moveToFirst()){
                        txt_baja_nombre.setText(fila.getString(0));

                        db.close();
                    }else{
                        Toast aviso = Toast.makeText(getApplicationContext(), "No se ha encontrado ese Partner", Toast.LENGTH_SHORT);
                        aviso.show();
                        db.close();
                    }
                }else{
                    Toast aviso = Toast.makeText(getApplicationContext(), "Introduzca una IDPartner, por favor", Toast.LENGTH_SHORT);
                    aviso.show();
                }
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_baja_IdPartner.getText().toString().length()!=0){
                    UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    int id=Integer.parseInt(txt_baja_IdPartner.getText().toString());

                    int cantidad_borrados=db.delete("Partners","idPartner="+id,null);

                    db.close();

                    txt_baja_nombre.setText("");
                    txt_baja_IdPartner.setText("");

                    if(cantidad_borrados==1){
                        Toast aviso = Toast.makeText(getApplicationContext(), "Partner Eliminado exitosamente", Toast.LENGTH_SHORT);
                        aviso.show();
                    }else{
                        Toast aviso = Toast.makeText(getApplicationContext(), "Error al eliminar el Partner", Toast.LENGTH_SHORT);
                        aviso.show();
                    }

                }else{
                    Toast aviso = Toast.makeText(getApplicationContext(), "Introduzca una IDPartner, por favor", Toast.LENGTH_SHORT);
                    aviso.show();
                }
            }
        });
    }
}