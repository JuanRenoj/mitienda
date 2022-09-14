package com.example.mitienda.Data;

import com.example.mitienda.Model.DetalleFactura;
import com.example.mitienda.Model.Modelo_factura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModelo_factura {

    public DataModelo_factura(){}
    public ObservableList<Modelo_factura> datosFactura(ObservableList<DetalleFactura> detalleFacturas){
        ObservableList<Modelo_factura> datos= FXCollections.observableArrayList();
        for (int i=0; i<detalleFacturas.size(); i++){
            Modelo_factura modelo=new Modelo_factura();
            modelo.setCantidad(detalleFacturas.get(i).getCantidad());
            modelo.setDescripcion(detalleFacturas.get(i).getDescripcion());
            modelo.setPrecio(detalleFacturas.get(i).getPrecio());
            modelo.setSubtotal(detalleFacturas.get(i).getSubtotal());
            datos.addAll(modelo);
        }
        return  datos;
    }
}
