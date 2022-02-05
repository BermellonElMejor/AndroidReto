package com.example.aplicacion_reto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCatalogo extends RecyclerView.Adapter<AdaptadorCatalogo.ProductosViewHolder> {

    private Context context;
    private List<Producto> listaProductos = new ArrayList<>();
    private List<Producto> listaCompra = new ArrayList<>();


    public AdaptadorCatalogo(Context context, List<Producto> listaProductos){

        this.context = context;
        this.listaProductos=listaProductos;
    }

    @NonNull
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_adaptador_catalogo, null, false);
        return new AdaptadorCatalogo.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder productosViewHolder, @SuppressLint("RecyclerView") final int i) {
        productosViewHolder.txtDescripcion.setText(listaProductos.get(i).getDescripcion());
        productosViewHolder.txtPrecio.setText("Precio: "+listaProductos.get(i).getPrecio());

        int id = context.getResources().getIdentifier("producto_"+i, "drawable", context.getPackageName());
        try{
            productosViewHolder.imgb.setImageResource(id);
        }catch (Exception e){
            productosViewHolder.imgb.setImageResource(R.drawable.gaming_grandma_logo);
        }
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }


    public class ProductosViewHolder extends RecyclerView.ViewHolder{
        TextView txtDescripcion, txtPrecio, txtCantidad;
        ImageView imgb;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDescripcion=itemView.findViewById(R.id.idDescripcion);
            txtPrecio=itemView.findViewById(R.id.idPrecio);
            imgb=itemView.findViewById(R.id.imgProducto);

        }


    }

}