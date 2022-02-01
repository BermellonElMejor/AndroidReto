package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultaPartners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_partners);

        EditText txt_consulta_IdPartner=findViewById(R.id.txt_modificar_IdPartner);
        TextView txt_consulta_nombre=findViewById(R.id.txt_modificar_nombre);
        TextView txt_consulta_direccion=findViewById(R.id.txt_modificar_direccion);
        TextView txt_consulta_poblacion=findViewById(R.id.txt_modificar_poblacion);
        TextView txt_consulta_cif=findViewById(R.id.txt_modificar_cif);
        TextView txt_consulta_telefono=findViewById(R.id.txt_modificar_telefono);
        TextView txt_consulta_email=findViewById(R.id.txt_modificar_email);
        TextView txt_consulta_comercial=findViewById(R.id.txt_modificar_comercial);
        Button btn_consultar=findViewById(R.id.btn_modificar_buscar);

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (txt_consulta_IdPartner.getText().toString().length()!=0){
                UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();

                int id=Integer.parseInt(txt_consulta_IdPartner.getText().toString());

                Cursor fila=db.rawQuery
                        ("select nombre,direccion,poblacion,cif,telefono,email,idComercial from Partners WHERE idPartner="+id, null);

                if(fila.moveToFirst()){
                    txt_consulta_nombre.setText(fila.getString(0));
                    txt_consulta_direccion.setText(fila.getString(1));
                    txt_consulta_poblacion.setText(fila.getString(2));
                    txt_consulta_cif.setText(fila.getString(3));
                    txt_consulta_telefono.setText(fila.getString(4));
                    txt_consulta_email.setText(fila.getString(5));
                    txt_consulta_comercial.setText(fila.getString(6));
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


    }
}