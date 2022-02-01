package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarPartners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_partners);

        EditText txt_modificar_IdPartner=findViewById(R.id.txt_modificar_IdPartner);
        TextView txt_modificar_nombre=findViewById(R.id.txt_modificar_nombre);
        TextView txt_modificar_direccion=findViewById(R.id.txt_modificar_direccion);
        TextView txt_modificar_poblacion=findViewById(R.id.txt_modificar_poblacion);
        TextView txt_modificar_cif=findViewById(R.id.txt_modificar_cif);
        TextView txt_modificar_telefono=findViewById(R.id.txt_modificar_telefono);
        TextView txt_modificar_email=findViewById(R.id.txt_modificar_email);
        TextView txt_modificar_comercial=findViewById(R.id.txt_modificar_comercial);

        EditText txt_modificado_nombre=findViewById(R.id.txt_modificado_nombre);
        EditText txt_modificado_direccion=findViewById(R.id.txt_modificado_direccion);
        EditText txt_modificado_poblacion=findViewById(R.id.txt_modificado_poblacion);
        EditText txt_modificado_cif=findViewById(R.id.txt_modificado_cif);
        EditText txt_modificado_telefono=findViewById(R.id.txt_modificado_telefono);
        EditText txt_modificado_gmail=findViewById(R.id.txt_modificado_gmail);
        EditText txt_modificado_idComercial=findViewById(R.id.txt_modificado_idComercial);




        Button btn_modificar_buscar=findViewById(R.id.btn_modificar_buscar);
        Button btn_modificar_modidicar=findViewById(R.id.btn_modificar_modidicar);


        btn_modificar_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_modificar_IdPartner.getText().toString().length()!=0){
                    UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    int id=Integer.parseInt(txt_modificar_IdPartner.getText().toString());

                    Cursor fila=db.rawQuery
                            ("select nombre,direccion,poblacion,cif,telefono,email,idComercial from Partners WHERE idPartner="+id, null);

                    if(fila.moveToFirst()){
                        txt_modificar_nombre.setText(fila.getString(0));
                        txt_modificar_direccion.setText(fila.getString(1));
                        txt_modificar_poblacion.setText(fila.getString(2));
                        txt_modificar_cif.setText(fila.getString(3));
                        txt_modificar_telefono.setText(fila.getString(4));
                        txt_modificar_email.setText(fila.getString(5));
                        txt_modificar_comercial.setText(fila.getString(6));
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

        btn_modificar_modidicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txt_modificado_nombre.getText().toString().length()!=0 && txt_modificado_direccion.getText().toString().length()!=0 && txt_modificado_poblacion.getText().toString().length()!=0 && txt_modificado_cif.getText().toString().length()!=0 && txt_modificado_telefono.getText().toString().length()!=0 && txt_modificado_gmail.getText().toString().length()!=0 && txt_modificado_idComercial.getText().toString().length()!=0 && txt_modificar_IdPartner.getText().toString().length()!=0){
                    UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(getApplicationContext(), "DBUsuarios", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    int id=Integer.parseInt(txt_modificar_IdPartner.getText().toString());

                    String nombre=txt_modificado_nombre.getText().toString();
                    String direccion=txt_modificado_direccion.getText().toString();
                    String poblacion=txt_modificado_poblacion.getText().toString();
                    String cif=txt_modificado_cif.getText().toString();
                    String telefono=txt_modificado_telefono.getText().toString();
                    String gmail=txt_modificado_gmail.getText().toString();
                    int idComercial=Integer.parseInt(txt_modificado_idComercial.getText().toString());

                    ContentValues registro=new ContentValues();
                    registro.put("nombre",nombre);
                    registro.put("direccion",direccion);
                    registro.put("poblacion",poblacion);
                    registro.put("cif",cif);
                    registro.put("telefono",telefono);
                    registro.put("email",gmail);
                    registro.put("idComercial",idComercial);

                    int cantidad_actualizados=db.update("Partners",registro,"idPartner="+id,null);

                    db.close();

                    txt_modificar_IdPartner.setText("");
                    txt_modificado_nombre.setText("");
                    txt_modificado_direccion.setText("");
                    txt_modificado_poblacion.setText("");
                    txt_modificado_cif.setText("");
                    txt_modificado_telefono.setText("");
                    txt_modificado_gmail.setText("");
                    txt_modificado_idComercial.setText("");

                    if(cantidad_actualizados==1){
                        Toast aviso = Toast.makeText(getApplicationContext(), "Se ha actualizado correctamente", Toast.LENGTH_SHORT);
                        aviso.show();
                    }else{
                        Toast aviso = Toast.makeText(getApplicationContext(), "No se ha actualizado", Toast.LENGTH_SHORT);
                        aviso.show();
                    }

                }else{
                    Toast aviso = Toast.makeText(getApplicationContext(), "Tiene que poner todos los valores a modificar y la IDPartner", Toast.LENGTH_SHORT);
                    aviso.show();
                }

            }
        });

    }
}