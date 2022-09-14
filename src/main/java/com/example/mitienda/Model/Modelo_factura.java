package com.example.mitienda.Model;

public class Modelo_factura {
    int cantidad;
    String descripcion, idProducto;
    float subtotal;
    float precio;

    public  Modelo_factura(){ }

    public  Modelo_factura(int cantidad,String descripcion,float precio, float subtotal){
        this.cantidad=cantidad;
        this.descripcion=descripcion;
        this.precio=precio;
        this.subtotal=subtotal;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

}
