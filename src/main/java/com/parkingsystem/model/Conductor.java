package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Conductor {

    private int id_conductor;
    private String nombre_cond;
    private String apellido_cond;
    private String dni_cond;
    private String telefono_cond;

    public Conductor() { }
    
    public Conductor(int id_conductor, String nombre_cond, String apellido_cond, String dni_cond, String telefono_cond) {
        this.id_conductor = id_conductor;
        this.nombre_cond = nombre_cond;
        this.apellido_cond = apellido_cond;
        this.dni_cond = dni_cond;
        this.telefono_cond = telefono_cond;
    }

    public int getId_conductor() {
        return id_conductor;
    }

    public void setId_conductor(int id_conductor) {
        this.id_conductor = id_conductor;
    }

    public String getNombre_cond() {
        return nombre_cond;
    }

    public void setNombre_cond(String nombre_cond) {
        this.nombre_cond = nombre_cond;
    }

    public String getApellido_cond() {
        return apellido_cond;
    }

    public void setApellido_cond(String apellido_cond) {
        this.apellido_cond = apellido_cond;
    }

    public String getDni_cond() {
        return dni_cond;
    }

    public void setDni_cond(String dni_cond) {
        this.dni_cond = dni_cond;
    }

    public String getTelefono_cond() {
        return telefono_cond;
    }

    public void setTelefono_cond(String telefono_cond) {
        this.telefono_cond = telefono_cond;
    }

    @Override
    public String toString() {
        return "Conductor{" + "id_conductor=" + id_conductor + ", nombre_cond=" + nombre_cond + ", apellido_cond=" + apellido_cond + ", dni_cond=" + dni_cond + ", telefono_cond=" + telefono_cond + '}';
    }
    
}
