package com.example.aplicacion_reto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCitas extends RecyclerView.Adapter<AdaptadorCitas.CitasViewHolder>{

    private Context context;
    private List<Cita> listaCitas = new ArrayList<>();


    public AdaptadorCitas(Context context, List<Cita> listaCitas){
        this.context = context;
        this.listaCitas=listaCitas;
    }

    @NonNull
    public AdaptadorCitas.CitasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_adaptador_citas, null, false);
        return new AdaptadorCitas.CitasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCitas.CitasViewHolder citasViewHolder, @SuppressLint("RecyclerView") final int i) {
        citasViewHolder.editCita.setText(listaCitas.get(i).getDescripcion());
        citasViewHolder.lbl_fecha.setText("Fecha: "+listaCitas.get(i).getFecha());
        citasViewHolder.lbl_hora.setText("Hora: "+listaCitas.get(i).getHora());
    }

    @Override
    public int getItemCount() {
        return listaCitas.size();
    }


    public class CitasViewHolder extends RecyclerView.ViewHolder{
        TextView lbl_fecha,lbl_hora;
        EditText editCita;

        public CitasViewHolder(@NonNull View itemView) {
            super(itemView);

            lbl_fecha=itemView.findViewById(R.id.lbl_fecha);
            lbl_hora=itemView.findViewById(R.id.lbl_hora);
            editCita=itemView.findViewById(R.id.editCita);

        }


    }
}