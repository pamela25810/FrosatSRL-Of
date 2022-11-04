package com.checkcode.fsrl.controller;

import android.content.Context;
import android.util.Log;

import com.checkcode.fsrl.db.Cliente;
import com.checkcode.fsrl.db.DataBaseClientes;


import com.dacorp.database.error.EjecutionDBExcepcion;
import com.dacorp.database.sql.cond.Condition;

import java.util.ArrayList;



import java.util.List;

public class ControllerCliente {

    public static void InsertarCliente(Context context, int idCliente , String nombreCliente, String appelidoCliente, String nitCliente, String telefonoCliente) {
        DataBaseClientes data = new DataBaseClientes(context);
        try {
            data.connect();
            Cliente mCliente,mClienteAnt;
            mCliente = new Cliente();
            mCliente.setIdCliente(idCliente);
            mCliente.setNombreCliente(nombreCliente);
            mCliente.setApellidoCliente(appelidoCliente);
            mCliente.setNitCliente(nitCliente);
            mCliente.setTelefonoCliente(telefonoCliente);

            mClienteAnt = data.selectFirst(Cliente.class, new Condition("nombreCliente='"
                    +mCliente.getNombreCliente()
                    +"' AND idCliente="
                    +mCliente.getIdCliente()));
            if (mClienteAnt!= null){
                mCliente.setIdCliente(mClienteAnt.getIdCliente());
                data.update(mCliente, new Condition("nombreCliente='"
                        +mCliente.getNombreCliente()
                        +"'"));
                Log.e("Update cliente",mCliente.toString());

            }else {
                data.insert(mCliente);
                Log.i("Insert cliente",mCliente.toString());

            }
        }catch (EjecutionDBExcepcion ejecutionDBExcepcion){
            ejecutionDBExcepcion.printStackTrace();
        }
        finally {
            data.close();
        }

    }

    public static void BuscarCliente(Context context, int idCliente ) {
        DataBaseClientes data = new DataBaseClientes(context);
        try {
            data.connect();
            Cliente mCliente,mClienteAnt;
            mCliente = new Cliente();
            mCliente.setIdCliente(idCliente);

            mClienteAnt = data.selectFirst(Cliente.class, new Condition("idCliente='"
                    +mCliente.getIdCliente()
                    +"' AND nombreCliente="
                    +mCliente.getNombreCliente()
                    +"' AND apellidoCliente="
                    +mCliente.getApellidoCliente()
                    +"' AND nitCliente="
                    +mCliente.getNitCliente()
                    +"' AND telefonoCliente="
                    +mCliente.getTelefonoCliente()));
            if (mClienteAnt!= null){
                mCliente.setIdCliente(mClienteAnt.getIdCliente());
                mCliente.setNombreCliente(mClienteAnt.getNombreCliente());
                mCliente.setApellidoCliente(mClienteAnt.getApellidoCliente());
                mCliente.setNitCliente(mClienteAnt.getNitCliente());
                mCliente.setTelefonoCliente(mCliente.getTelefonoCliente());
                Log.e("Cliente Encontrado",mCliente.toString());
            }else {

                Log.i("No existe Id de cliente",mCliente.toString());

            }
        }catch (EjecutionDBExcepcion ejecutionDBExcepcion){
            ejecutionDBExcepcion.printStackTrace();
        }
        finally {
            data.close();
        }

    }

  /* public static void EliminarCliente(Context context, int idCliente ) {
        DataBaseClientes data = new DataBaseClientes(context);
        try {
            data.connect();
            Cliente mCliente;
            mCliente = new Cliente();
            mCliente.setIdCliente(idCliente);

           mCliente = data.delete(Cliente.class, new Condition("idCliente='"
             +mCliente.getIdCliente()));
        }catch (EjecutionDBExcepcion ejecutionDBExcepcion){
            ejecutionDBExcepcion.printStackTrace();
        }
        finally {
            data.close();
        }

    }*/

    public static List<Cliente> listaClientes(Context context){
        List<Cliente> list = new ArrayList<>();
        DataBaseClientes data = new DataBaseClientes(context);
        try {
            data.connect();
            list = data.select(Cliente.class);

        }catch (EjecutionDBExcepcion ejecutionDBExcepcion){
            ejecutionDBExcepcion.printStackTrace();
        }
        finally {
            data.close();
        }
        return list;
    }
}
