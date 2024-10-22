package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Abonado {

    private int id_abo;
    private String fecha_inicio_abo;
    private String fecha_fin_abo;
    private String tipo_abo;
    private Float monto_abo;

    public Abonado() { }

    public Abonado(int id_abo, String fecha_inicio_abo, String fecha_fin_abo, String tipo_abo, Float monto_abo) {
        this.id_abo = id_abo;
        this.fecha_inicio_abo = fecha_inicio_abo;
        this.fecha_fin_abo = fecha_fin_abo;
        this.tipo_abo = tipo_abo;
        this.monto_abo = monto_abo;
    }

    public int getId_abo() {
        return id_abo;
    }

    public void setId_abo(int id_abo) {
        this.id_abo = id_abo;
    }

    public String getFecha_inicio_abo() {
        return fecha_inicio_abo;
    }

    public void setFecha_inicio_abo(String fecha_inicio_abo) {
        this.fecha_inicio_abo = fecha_inicio_abo;
    }

    public String getFecha_fin_abo() {
        return fecha_fin_abo;
    }

    public void setFecha_fin_abo(String fecha_fin_abo) {
        this.fecha_fin_abo = fecha_fin_abo;
    }

    public String getTipo_abo() {
        return tipo_abo;
    }

    public void setTipo_abo(String tipo_abo) {
        this.tipo_abo = tipo_abo;
    }

    public Float getMonto_abo() {
        return monto_abo;
    }

    public void setMonto_abo(Float monto_abo) {
        this.monto_abo = monto_abo;
    }

    @Override
    public String toString() {
        return "Abonado{" + "id_abo=" + id_abo + ", fecha_inicio_abo=" + fecha_inicio_abo + ", fecha_fin_abo=" + fecha_fin_abo + ", tipo_abo=" + tipo_abo + ", monto_abo=" + monto_abo + '}';
    }
    
}
