package com.example.mitienda.Data;

import com.example.mitienda.ClassAux.Conexion;
import com.example.mitienda.Model.Cuenta;
import com.example.mitienda.Model.ResumenVenta;
import com.example.mitienda.Model.Venta;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataVenta {
    public ArrayList<ResumenVenta> viewVentas(Venta venta, String accion){


        ArrayList<ResumenVenta> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call consulta_ventas(?, ?, ?, ?)}");
            callableStatement.setInt(1,venta.getIndventa());
            callableStatement.setString(2, venta.getfInicial());
            callableStatement.setString(3,venta.getfFinal());
            callableStatement.setString(4,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                ResumenVenta resumenVenta=new ResumenVenta();
                resumenVenta.setIdfactura(resultSet.getInt("idfactura"));
                resumenVenta.setFecha(resultSet.getString("fecha"));
                resumenVenta.setCliente(resultSet.getString("cliente"));
                resumenVenta.setTotal(resultSet.getInt("total"));

                lista.add(resumenVenta);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Cuenta> viewCuenta(Venta venta, String accion){


        ArrayList<Cuenta> lista=new ArrayList<>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call consulta_ventas(?, ?, ?, ?)}");
            callableStatement.setInt(1,venta.getIndventa());
            callableStatement.setString(2, venta.getfInicial());
            callableStatement.setString(3,venta.getfFinal());
            callableStatement.setString(4,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Cuenta cuenta=new Cuenta();
                cuenta.setVentas(resultSet.getFloat("ventas"));
                cuenta.setInversion(resultSet.getFloat("compra"));
                cuenta.setGanancia(resultSet.getFloat("ganancia"));
                lista.add(cuenta);
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
