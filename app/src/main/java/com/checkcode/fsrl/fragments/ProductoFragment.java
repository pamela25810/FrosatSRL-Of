package com.checkcode.fsrl.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.checkcode.fsrl.R;

import com.checkcode.fsrl.adapter.ProductListAdapter;
import com.checkcode.fsrl.controller.ControllerProducto;

import com.checkcode.fsrl.db.Producto;

import java.util.ArrayList;
import java.util.List;


public class ProductoFragment extends Fragment {

    private ProductListAdapter productListAdaptero;
    private List<Producto> mListProducto;
    private RecyclerView mRecyclerView;
    private EditText edtId, edtNombre, edtDetalle, edtCantidad, edtStock;
    private Button btnGuardar;
    LayoutInflater inflater;



    public ProductoFragment() {
        // Required empty public constructor
    }

    public static ProductoFragment newInstance(String param1, String param2) {
        ProductoFragment fragment = new ProductoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producto, container, false);

        ////boton registrar////
        edtId = view.findViewById(R.id.cIdP);
        edtNombre = view.findViewById(R.id.campoNombreProducto);
        edtDetalle = view.findViewById(R.id.campoDetalleProducto);
        edtCantidad = view.findViewById(R.id.campoCantidadProducto);
        edtStock = view.findViewById(R.id.campoStockProducto);

        btnGuardar = view.findViewById(R.id.btnRegistrarP);

        btnGuardar.setOnClickListener(v -> {
            ControllerProducto.InsertarProducto(getContext(),
                    (int) Long.parseLong(edtId.getText().toString()),
                    edtNombre.getText().toString(),
                    edtDetalle.getText().toString(),
                    edtCantidad.getText().toString(),
                    edtStock.getText().toString());
        });
        mRecyclerView = view.findViewById(R.id.listViewProducto);

        loadProductList();
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        try {

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(dividerItemDecoration);
            productListAdaptero = new ProductListAdapter(getContext(),mListProducto);
            mRecyclerView.setAdapter(productListAdaptero);


        } catch (Exception ex) {

            Log.e("Error Recycler:", ex.getMessage());
        }
    }

    private void loadProductList() {
        mListProducto = new ArrayList<>();
        mListProducto = ControllerProducto.listaProductos(getContext());
        for (int i = 0; i < mListProducto.size(); i++) {
            System.out.println("id_producto:" + mListProducto.get(i).getIdProducto());
        }
    }
}