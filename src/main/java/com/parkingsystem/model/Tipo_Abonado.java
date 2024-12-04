package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Tipo_Abonado {

    private int id_tipo_abo;
    private String nombre;
    private float monto;

    public Tipo_Abonado() { }

    public Tipo_Abonado(int id_tipo_abo, String nombre, float monto) {
        this.id_tipo_abo = id_tipo_abo;
        this.nombre = nombre;
        this.monto = monto;
    }

    public int getId_tipo_abo() {
        return id_tipo_abo;
    }

    public void setId_tipo_abo(int id_tipo_abo) {
        this.id_tipo_abo = id_tipo_abo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Tipo_Abonado{" + "id_tipo_abo=" + id_tipo_abo + ", nombre=" + nombre + ", monto=" + monto + '}';
    }
    
}
