package com.checkcode.fsrl.db;

import com.dacorp.database.annot.Column;
import com.dacorp.database.annot.Table;
import com.dacorp.database.data.enu.Schema;

@Table(schema = Schema.PUBLIC)
public class Producto {
     @Column
     private long idProducto;
     @Column
     private String nombreProducto;
     @Column
     private String detalleProducto;
     @Column
     private String cantidad;
     @Column
     private String stock;

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", detalleProducto='" + detalleProducto + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(String detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}

