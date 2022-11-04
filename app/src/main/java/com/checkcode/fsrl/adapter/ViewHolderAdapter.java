package com.checkcode.fsrl.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.checkcode.fsrl.R;

public class ViewHolderAdapter extends RecyclerView.ViewHolder{
    public TextView tvId,
            tvName,
            tvApellido,
            tvNit,
            tvCellphone;
    public TextView tvIdP,
            tvNameP,
            tvDetalleP,
            tvCantidadP,
            tvStockP;
    private TextView view;
    public ViewHolderAdapter(@NonNull View itemView) {
        super(itemView);
        tvId=itemView.findViewById(R.id.tvId_lista);
        tvName = itemView.findViewById(R.id.tvName_lista);
        tvApellido = itemView.findViewById(R.id.tvPaterno_lista);
        tvNit=itemView.findViewById(R.id.tvNit_lista);
        tvCellphone = itemView.findViewById(R.id.tvCellphone_lista);
        view = itemView.findViewById(R.id.randomText);

        tvIdP=itemView.findViewById(R.id.tvId_listaP);
        tvNameP=itemView.findViewById(R.id.tvName_listaP);
        tvDetalleP=itemView.findViewById(R.id.tvDetal_listaP);
        tvCantidadP=itemView.findViewById(R.id.tvCanti_listaP);
        tvStockP=itemView.findViewById(R.id.tvStock_listaP);
    }

    public TextView getView(){
        return view;
    }
}
