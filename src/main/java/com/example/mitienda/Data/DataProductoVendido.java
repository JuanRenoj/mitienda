package com.example.mitienda.Data;

import com.example.mitienda.ClassAux.Conexion;
import com.example.mitienda.Model.Producto_Vendido;
import com.example.mitienda.Model.Venta;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataProductoVendido {
    public ArrayList<Producto_Vendido> viewProductoVendido(Venta venta, String accion){


        ArrayList<Producto_Vendido> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call consulta_producto_vendido(?, ?, ?, ?)}");
            callableStatement.setInt(1,venta.getIndventa());
            callableStatement.setString(2, venta.getfInicial());
            callableStatement.setString(3,venta.getfFinal());
            callableStatement.setString(4,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Producto_Vendido vendido=new Producto_Vendido();
                vendido.setCodigo(resultSet.getInt("id_producto"));
                vendido.setCantidad(resultSet.getInt("cantidad_vendida"));
                vendido.setNombre(resultSet.getString("nombre"));
                lista.add(vendido);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }
}
