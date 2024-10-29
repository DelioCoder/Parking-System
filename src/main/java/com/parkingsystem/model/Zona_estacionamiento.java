package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Zona_Estacionamiento {

    private int id_zona_est;
    private String nombre_zona_est;
    private String estado_espacio;
    private int id_piso_est;

    public Zona_Estacionamiento() { }

    public Zona_Estacionamiento(int id_zona_est, String nombre_zona_est, String estado_espacio, int id_piso_est) {
        this.id_zona_est = id_zona_est;
        this.nombre_zona_est = nombre_zona_est;
        this.estado_espacio = estado_espacio;
        this.id_piso_est = id_piso_est;
    }

    public int getId_zona_est() {
        return id_zona_est;
    }

    public void setId_zona_est(int id_zona_est) {
        this.id_zona_est = id_zona_est;
    }

    public String getNombre_zona_est() {
        return nombre_zona_est;
    }

    public void setNombre_zona_est(String nombre_zona_est) {
        this.nombre_zona_est = nombre_zona_est;
    }

    
    
    public String getEstado_espacio() {
        return estado_espacio;
    }

    public void setEstado_espacio(String estado_espacio) {
        this.estado_espacio = estado_espacio;
    }

    public int getId_piso_est() {
        return id_piso_est;
    }

    public void setId_piso_est(int id_piso_est) {
        this.id_piso_est = id_piso_est;
    }

    @Override
    public String toString() {
        return "Zona_estacionamiento{" + "id_zona_est=" + id_zona_est + ", estado_espacio=" + estado_espacio + ", id_piso_est=" + id_piso_est + '}';
    }
    
    
    
}
