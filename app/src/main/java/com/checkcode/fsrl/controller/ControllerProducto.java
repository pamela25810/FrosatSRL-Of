package com.checkcode.fsrl.controller;

import android.content.Context;
import android.util.Log;

import com.checkcode.fsrl.db.DataBaseProductos;
import com.checkcode.fsrl.db.Producto;
import com.dacorp.database.error.EjecutionDBExcepcion;
import com.dacorp.database.sql.cond.Condition;

import java.util.ArrayList;
import java.util.List;

public class ControllerProducto {
    public static void InsertarProducto(Context context, int idProducto , String nombreProducto, String detalleProducto, String cantidadProducto, String stockProducto) {
        DataBaseProductos data = new DataBaseProductos(context);
        try {
            data.connect();
            Producto mProducto,mProductoAnt;
            mProducto = new Producto();
            mProducto.setIdProducto(idProducto);
            mProducto.setNombreProducto(nombreProducto);
            mProducto.setDetalleProducto(detalleProducto);
            mProducto.setCantidad(cantidadProducto);
            mProducto.setStock(stockProducto);

            mProductoAnt = data.selectFirst(Producto.class, new Condition("idProducto='"
                    +mProducto.getIdProducto()
                    +"' AND noombreProducto="
                    +mProducto.getNombreProducto()));
            if (mProductoAnt!= null){
                mProducto.setIdProducto(mProductoAnt.getIdProducto());
                data.update(mProducto, new Condition("nombreProducto='"
                        +mProducto.getNombreProducto()
                        +"'"));
                Log.e("Update producto",mProducto.toString());

            }else {
                data.insert(mProducto);
                Log.i("Insert producto",mProducto.toString());

            }
        }catch (EjecutionDBExcepcion ejecutionDBExcepcion){
            ejecutionDBExcepcion.printStackTrace();
        }
        finally {
            data.close();
        }

    }
    public static List<Producto> listaProductos(Context context){
        List<Producto> list = new ArrayList<>();
        DataBaseProductos data = new DataBaseProductos(context);
        try {
            data.connect();
            list = data.select(Producto.class);

        }catch (EjecutionDBExcepcion ejecutionDBExcepcion){
            ejecutionDBExcepcion.printStackTrace();
        }
        finally {
            data.close();
        }
        return list;
    }
}
