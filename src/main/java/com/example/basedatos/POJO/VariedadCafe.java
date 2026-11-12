package com.example.basedatos.POJO;

public class VariedadCafe {
    private Integer IDVariedad;
    private String Descripcion;

    public VariedadCafe(Integer IDVariedad, String descripcion) {
        this.IDVariedad = IDVariedad;
        this.Descripcion = descripcion;
    }

    public Integer getIDVariedad() {
        return IDVariedad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setIDVariedad(Integer IDVariedad) {
        this.IDVariedad = IDVariedad;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", IDVariedad, Descripcion);
    }
}
