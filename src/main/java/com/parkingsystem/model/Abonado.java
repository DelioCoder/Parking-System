package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Abonado {

    private int id_abo;
    private String fecha_inicio_abo;
    private String fecha_fin_abo;
    private int id_vehiculo;
    private int id_tipo_abonado;

    public Abonado() { }

    public Abonado(int id_abo, String fecha_inicio_abo, String fecha_fin_abo, String tipo_abo, Float monto_abo, int id_vehiculo, int id_tipo_abonado) {
        this.id_abo = id_abo;
        this.fecha_inicio_abo = fecha_inicio_abo;
        this.fecha_fin_abo = fecha_fin_abo;
        this.id_vehiculo = id_vehiculo;
        this.id_tipo_abonado = id_tipo_abonado;
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

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public int getId_tipo_abonado() {
        return id_tipo_abonado;
    }

    public void setId_tipo_abonado(int id_tipo_abonado) {
        this.id_tipo_abonado = id_tipo_abonado;
    }

    @Override
    public String toString() {
        return "Abonado{" + "id_abo=" + id_abo + ", fecha_inicio_abo=" + fecha_inicio_abo + ", fecha_fin_abo=" + fecha_fin_abo + ", id_vehiculo=" + id_vehiculo + ", id_tipo_abonado=" + id_tipo_abonado + '}';
    }

   
    
}
