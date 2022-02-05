package com.example.aplicacion_reto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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


    public AdaptadorCatalogo(Context context, List<Producto> listaProductos) {

        this.context = context;
        this.listaProductos = listaProductos;
    }

    @NonNull
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_adaptador_catalogo, null, false);
        return new AdaptadorCatalogo.ProductosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder productosViewHolder, @SuppressLint("RecyclerView") final int i) {
        productosViewHolder.txtDescripcion.setText(listaProductos.get(i).getDescripcion());
        productosViewHolder.txtPrecio.setText("Precio: " + listaProductos.get(i).getPrecio());

        int id = context.getResources().getIdentifier("producto_" + i, "drawable", context.getPackageName());
        try {
            productosViewHolder.imgb.setImageResource(id);
        } catch (Exception e) {
            productosViewHolder.imgb.setImageResource(R.drawable.gaming_grandma_logo);
        }

        productosViewHolder.editCantidad.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (productosViewHolder.editCantidad.getText().length() > 0 && Integer.parseInt(productosViewHolder.editCantidad.getText().toString()) > 0) {
                    try {
                        listaCompra.remove(listaProductos.get(i));
                    } catch (Exception e) {
                    }


                    listaProductos.get(i).setCantidad(Integer.parseInt(productosViewHolder.editCantidad.getText().toString()));
                    listaCompra.add(listaProductos.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }


    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescripcion, txtPrecio;
        ImageView imgb;
        EditText editCantidad;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDescripcion = itemView.findViewById(R.id.idDescripcion);
            txtPrecio = itemView.findViewById(R.id.idPrecio);
            imgb = itemView.findViewById(R.id.imgProducto);
            editCantidad = itemView.findViewById(R.id.editCantidad);

        }


    }

    public List<Producto> getListaCompra() {
        return this.listaCompra;
    }
}