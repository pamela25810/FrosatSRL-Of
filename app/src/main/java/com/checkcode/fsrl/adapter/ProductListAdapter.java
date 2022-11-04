package com.checkcode.fsrl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.checkcode.fsrl.R;
import com.checkcode.fsrl.db.Cliente;
import com.checkcode.fsrl.db.Producto;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ViewHolderAdapter> {

    private Context context;
    private List<Producto> productList;


    public ProductListAdapter(Context context, List<Producto> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_producto,parent,false);
        ViewHolderAdapter holderProducto = new ViewHolderAdapter(v);
        return holderProducto;
    }

    @Override
    public void onBindViewHolder( ViewHolderAdapter holder, int position) {
        Producto producto = productList.get(position);

        holder.tvIdP.setText(String.valueOf(producto.getIdProducto()));
        holder.tvNameP.setText(producto.getNombreProducto());
        holder.tvDetalleP.setText(producto.getDetalleProducto());
        holder.tvCantidadP.setText(producto.getCantidad());
        holder.tvStockP.setText(producto.getStock());
    }

    @Override
    public int getItemCount() {

        return  this.productList.size();
    }


}