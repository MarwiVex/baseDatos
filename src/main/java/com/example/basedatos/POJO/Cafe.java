package com.example.basedatos.POJO;

public class Cafe {
    private Integer idcafe;
    private String productor;
    private Integer VariedadCafe;
    private Float cantidad;

    public Cafe(Integer IDCafe, String productor, Integer variedadCafe, Float cantidad) {
        this.idcafe = IDCafe;
        this.productor = productor;
        this.cantidad = cantidad;
        VariedadCafe = variedadCafe;
    }

    public Integer getIdcafe() {
        return idcafe;
    }

    public void setIdcafe(Integer idcafe) {
        this.idcafe = idcafe;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getVariedadCafe() {
        return VariedadCafe;
    }

    public void setVariedadCafe(Integer variedadCafe) {
        VariedadCafe = variedadCafe;
    }

    @Override
    public String toString() {
        return String.format("%d - %s - %d - %.2f", idcafe, productor, VariedadCafe, cantidad);
    }
}
