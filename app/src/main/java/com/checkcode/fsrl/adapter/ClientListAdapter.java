package com.checkcode.fsrl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.checkcode.fsrl.R;
import com.checkcode.fsrl.db.Cliente;


import java.util.List;

public class ClientListAdapter extends RecyclerView.Adapter<ViewHolderAdapter> {

    private Context context;
    private List<Cliente> clientList;


    public ClientListAdapter(Context context, List<Cliente> clientList) {
        this.context = context;
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_cliente,parent,false);
        ViewHolderAdapter holderClientes = new ViewHolderAdapter(v);
        return holderClientes;
    }

    @Override
    public void onBindViewHolder( ViewHolderAdapter holder, int position) {
        Cliente cliente= clientList.get(position);

        System.out.println("nombre:"+ cliente.getNombreCliente());
        holder.tvId.setText(String.valueOf(cliente.getIdCliente()));
        holder.tvName.setText(cliente.getNombreCliente());
        holder.tvApellido.setText(cliente.getApellidoCliente());
        holder.tvNit.setText(cliente.getNitCliente());
        holder.tvCellphone.setText(cliente.getTelefonoCliente());
    }

    @Override
    public int getItemCount() {

        return  this.clientList.size();
    }


}