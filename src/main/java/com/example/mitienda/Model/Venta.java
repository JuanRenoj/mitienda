package com.example.mitienda.Model;

public class Venta {
    int indventa;
    String fFinal,fInicial;

    public Venta() {
    }

    public Venta(int indventa, String fInicial, String fFinal) {
        this.indventa = indventa;
        this.fFinal = fFinal;
        this.fInicial = fInicial;
    }

    public int getIndventa() {
        return indventa;
    }

    public void setIndventa(int indventa) {
        this.indventa = indventa;
    }

    public String getfFinal() {
        return fFinal;
    }

    public void setfFinal(String fFinal) {
        this.fFinal = fFinal;
    }

    public String getfInicial() {
        return fInicial;
    }

    public void setfInicial(String fInicial) {
        this.fInicial = fInicial;
    }

}
