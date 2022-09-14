package com.example.mitienda.Data;

import com.example.mitienda.ClassAux.Conexion;
import com.example.mitienda.ClassAux.Util;
import com.example.mitienda.Model.Cliente;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataCliente {
    public ArrayList<Cliente> viewCliente(String accion){


        ArrayList<Cliente> lista=new ArrayList<Cliente>();
        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_cliente(?, ?, ?, ?, ?, ?, ?,?)}");
            callableStatement.setInt(1,0);
            callableStatement.setString(2,"");
            callableStatement.setString(3,"");
            callableStatement.setInt(4,0);
            callableStatement.setInt(5,0);
            callableStatement.setString(6,"");
            callableStatement.setString(7,"");
            callableStatement.setString(8,accion);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                Cliente cliente=new Cliente();
                cliente.setCodigo(resultSet.getInt("idcliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefonoUno(resultSet.getInt("TelefonoUno"));
                cliente.setTelefonoDos(resultSet.getInt("TelefonoDos"));
                cliente.setNit(resultSet.getString("nit"));
                cliente.setSexo(resultSet.getString("sexo"));
                lista.add(cliente);
            }

            callableStatement.close();
            conexion.con.close();
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lista;
    }
    public void crudCliente(Cliente cliente, String accion){


        try {
            Conexion conexion =new Conexion();

            conexion.Conexion();
            CallableStatement callableStatement=conexion.con.prepareCall("{call ingreso_cliente(?, ?, ?, ?, ?, ?, ?,?)}");
            callableStatement.setInt(1, cliente.getCodigo());
            callableStatement.setString(2, cliente.getNombre());
            callableStatement.setString(3, cliente.getApellido());
            callableStatement.setInt(4, cliente.getTelefonoUno());
            callableStatement.setInt(5, cliente.getTelefonoDos());
            callableStatement.setString(6, cliente.getSexo());
            callableStatement.setString(7, cliente.getNit());
            callableStatement.setString(8,accion);

            callableStatement.execute();
            Util.Exito("Operacion","Realizado con exito:");

            callableStatement.close();
            conexion.con.close();

        } catch (SQLException throwables) {
            // throwables.printStackTrace();
            if(accion.equals("delete")) {
                Util.Error("View/Cliente", "No se puede eliminar por que esta relacionado aotro registro:" + throwables);
            }
        }


    }
}
