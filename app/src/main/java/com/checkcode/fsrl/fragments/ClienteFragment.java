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
import com.checkcode.fsrl.adapter.ClientListAdapter;
import com.checkcode.fsrl.controller.ControllerCliente;
import com.checkcode.fsrl.db.Cliente;

import java.util.ArrayList;
import java.util.List;


public class ClienteFragment extends Fragment {
    private ClientListAdapter clienteListAdapter;
    private List<Cliente> mListCliente;
    private RecyclerView mRecyclerView;
    private EditText edtId, edtNombre, edtApellido, edtNit, edtTelefono;
    private Button btnGuardar;
    LayoutInflater inflater;

    public ClienteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClienteFragment newInstance(String param1, String param2) {
        ClienteFragment fragment = new ClienteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);

        ////boton registrar////
        edtId = view.findViewById(R.id.cId);
        edtNombre = view.findViewById(R.id.campoNombreConsulta);
        edtApellido = view.findViewById(R.id.campoApellidoConsulta);
        edtNit = view.findViewById(R.id.campoNitConsulta);
        edtTelefono = view.findViewById(R.id.campoTelefonoConsulta);

        btnGuardar = view.findViewById(R.id.btnRegistrar);

        btnGuardar.setOnClickListener(v -> {
            ControllerCliente.InsertarCliente(getContext(),
                    (int) Long.parseLong(edtId.getText().toString()),
                    edtNombre.getText().toString(),
                    edtApellido.getText().toString(),
                    edtNit.getText().toString(),
                    edtTelefono.getText().toString());
        });
        mRecyclerView = view.findViewById(R.id.listViewClientes);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(new RandomNumListAdapter(1234));

        loadClientList();
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        try {

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL);
            mRecyclerView.addItemDecoration(dividerItemDecoration);
            clienteListAdapter = new ClientListAdapter(getContext(), mListCliente);
            mRecyclerView.setAdapter(clienteListAdapter);


        } catch (Exception ex) {

            Log.e("Error Recycler:", ex.getMessage());
        }
    }

    private void loadClientList() {
        mListCliente = new ArrayList<>();
        mListCliente = ControllerCliente.listaClientes(getContext());
        for (int i = 0; i < mListCliente.size(); i++) {
            System.out.println("id_cliente:" + mListCliente.get(i).getIdCliente());
        }
    }

}