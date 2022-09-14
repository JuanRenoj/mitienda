package com.example.mitienda.Data;

import com.example.mitienda.ClassAux.Conexion;
import com.example.mitienda.ClassAux.Util;
import com.example.mitienda.Model.DetalleFactura;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataDetalleFactura {
    public ArrayList<DetalleFactura> viewDetalleFactura(String accion) {


        ArrayList<DetalleFactura> lista = new ArrayList<>();
        try {
            Conexion conexion = new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement = conexion.con.prepareCall("{call ingreso_detallefactura(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, 0);
            callableStatement.setInt(2, 0);
            callableStatement.setInt(3, 0);
            callableStatement.setInt(4, 0);
            callableStatement.setFloat(5, 0);
            callableStatement.setString(6, "");
            callableStatement.setString(7, accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                DetalleFactura DetalleFactura = new DetalleFactura();
                DetalleFactura.setIddetalle(resultSet.getInt("iddetalle"));
                DetalleFactura.setIdfatura(resultSet.getInt("id_factura"));
                DetalleFactura.setIdproducto(resultSet.getInt("id_producto"));
                DetalleFactura.setCantidad(resultSet.getInt("cantidad"));
                DetalleFactura.setPrecio(resultSet.getFloat("precio"));
                DetalleFactura.setSubtotal(resultSet.getFloat("subtotal"));
                DetalleFactura.setIdlote(resultSet.getInt("idlote"));

                lista.add(DetalleFactura);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }

    public void crudDetalleFactura(DetalleFactura detalleFactura, String accion) {


        try {
            Conexion conexion = new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement = conexion.con.prepareCall("{call ingreso_detallefactura(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, detalleFactura.getIddetalle());
            callableStatement.setInt(2, detalleFactura.getIdfatura());
            callableStatement.setInt(3, detalleFactura.getIdproducto());
            callableStatement.setInt(4, detalleFactura.getCantidad());
            callableStatement.setFloat(5, detalleFactura.getPrecio());
            callableStatement.setFloat(6, detalleFactura.getIdlote());
            callableStatement.setString(7, accion);

            callableStatement.execute();
            Util.Exito("View/Venta ", "Operacion realizada con exito: " );

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            // throwables.printStackTrace();
            if (accion.equals("delete")) {
                Util.Error("DetalleFactura", "No se puede eliminar por que esta relacionado aotro registro:" + throwables);
            }
        }


    }
}
