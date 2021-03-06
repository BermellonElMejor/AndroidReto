package com.example.aplicacion_reto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
   /*Dar de alta: Se recogerán los datos del partner: nombre, dirección, población, CIF, teléfono, email y lo asociaremos al
    comercial correspondiente.
            ● En la confirmación del nuevo partner se enviará al archivo de nuevos partners del día que posteriormente se enviarán a su
    delegación (formato XML)
    */


    String sqlCreate1 = "CREATE TABLE Comerciales (idComercial INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, usuario TEXT, nombre TEXT, apellido TEXT, contraseña TEXT)";
    String sqlCreate2 = "CREATE TABLE Citas (idCita INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, fecha DATETIME NOT NULL, descripcion TEXT, hora TEXT)";
    String sqlCreate3 = "CREATE TABLE Partners (idPartner INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT, direccion TEXT, poblacion TEXT, cif TEXT, telefono TEXT, email TEXT, idComercial INTEGER, CONSTRAINT ck_idComerial FOREIGN KEY (idComercial) REFERENCES Comerciales (idComercial))";
    String sqlCreate4 = "CREATE TABLE Cab_Pedidos (idPedido INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, fecha TIMESTAMP)";
    String sqlCreate5 = "CREATE TABLE Lin_Pedidos (idPedido INTEGER NOT NULL, linea INTEGER, idAlmacen INTEGER, cantidad INTEGER, precio INTEGER, descripcion TEXT, PRIMARY KEY(idPedido, linea), CONSTRAINT ck_idPedido FOREIGN KEY (idPedido) REFERENCES Cab_Pedidos (idPedido), CONSTRAINT ck_idAlmacen FOREIGN KEY (idAlmacen) REFERENCES Almacen_Deleg (idAlmacen))";
    String sqlCreate6 = "CREATE TABLE Almacen_Deleg (idAlmacen INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, descripcion TEXT, pr_vent INTEGER, existencias INTEGER)";
    String usuarioEjemplo = "INSERT INTO Comerciales(idComercial, usuario, contraseña) VALUES(1,'123','123')";
    String parterEjemplo = "INSERT INTO Partners(idPartner,nombre,direccion,poblacion,cif,telefono,email,idComercial) VALUES(1,'Partner','direccion_partner','poblacion_partner','cif_partner','telefono_partner','email_partner',1)";
    String parterEjemplo1 = "INSERT INTO Partners(idPartner,nombre,direccion,poblacion,cif,telefono,email,idComercial) VALUES(2,'Javi','direccion_de_javi','Donostia','2665666JK','681662639','gmaildeJaVi@gmail.com',1)";
    String producto1 = "INSERT INTO Almacen_Deleg (descripcion, pr_vent, existencias) VALUES('Teclado',80,100)";
    String producto2 = "INSERT INTO Almacen_Deleg (descripcion, pr_vent, existencias) VALUES('Ratón',60,100)";
    String producto3 = "INSERT INTO Almacen_Deleg (descripcion, pr_vent, existencias) VALUES('Alfombrilla',15,100)";
    String producto4 = "INSERT INTO Almacen_Deleg (descripcion, pr_vent, existencias) VALUES('Silla Gamer',150,50)";
    String producto5 = "INSERT INTO Almacen_Deleg (descripcion, pr_vent, existencias) VALUES('Taza personalizada',200,50)";


    public UsuariosSQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);
        db.execSQL(sqlCreate4);
        db.execSQL(sqlCreate5);
        db.execSQL(sqlCreate6);
        db.execSQL(usuarioEjemplo);
        db.execSQL(parterEjemplo);
        db.execSQL(parterEjemplo1);
        db.execSQL(producto1);
        db.execSQL(producto2);
        db.execSQL(producto3);
        db.execSQL(producto4);
        db.execSQL(producto5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate1);
        db.execSQL(sqlCreate2);
        db.execSQL(sqlCreate3);
        db.execSQL(sqlCreate4);
        db.execSQL(sqlCreate5);
        db.execSQL(sqlCreate6);
        db.execSQL(usuarioEjemplo);
        db.execSQL(parterEjemplo);
        db.execSQL(parterEjemplo1);
        db.execSQL(producto1);
        db.execSQL(producto2);
        db.execSQL(producto3);
        db.execSQL(producto4);
        db.execSQL(producto5);
    }
}