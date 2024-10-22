package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Piso_estacionamiento {

    private int id_piso_est;
    private int numero_piso;
    private int capacidad;

    public Piso_estacionamiento() { }

    public Piso_estacionamiento(int id_piso_est, int numero_piso, int capacidad) {
        this.id_piso_est = id_piso_est;
        this.numero_piso = numero_piso;
        this.capacidad = capacidad;
    }

    public int getId_piso_est() {
        return id_piso_est;
    }

    public void setId_piso_est(int id_piso_est) {
        this.id_piso_est = id_piso_est;
    }

    public int getNumero_piso() {
        return numero_piso;
    }

    public void setNumero_piso(int numero_piso) {
        this.numero_piso = numero_piso;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Piso_estacionamiento{" + "id_piso_est=" + id_piso_est + ", numero_piso=" + numero_piso + ", capacidad=" + capacidad + '}';
    }
    
}
