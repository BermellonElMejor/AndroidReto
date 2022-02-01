package com.example.aplicacion_reto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PartnersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partners);

        Button darAlta = findViewById(R.id.btnDarAlta);
        Button darBaja =findViewById(R.id.btnDarBaja);
        Button consulta=findViewById(R.id.btnConsultas);
        Button modificaciones=findViewById(R.id.btnModificaciones);


        Intent intentA = new Intent(this, AltaActivity.class);
        Intent intentB = new Intent(this, DarBajaPartners.class);
        Intent intentC= new Intent(this, ConsultaPartners.class);
        Intent intentM= new Intent(this, ModificarPartners.class);


        darAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentA);
            }
        });

        darBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentB);
            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentC);
            }
        });
        modificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentM);
            }
        });
    }


}