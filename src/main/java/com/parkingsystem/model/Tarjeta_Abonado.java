package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Tarjeta_Abonado {

    private int id_tarj_abo;
    private String fecha_emision_tarj_abo;
    private String fecha_expiracion_tarj_abo;
    private int id_abo;
    private int id_veh;

    public Tarjeta_Abonado() { }
    
    public Tarjeta_Abonado(
            int id_tarj_abo, String fecha_emision_tarj_abo,
            String fecha_expiracion_tarj_abo, int id_abo, int id_veh)
    {
        this.id_tarj_abo = id_tarj_abo;
        this.fecha_emision_tarj_abo = fecha_emision_tarj_abo;
        this.fecha_expiracion_tarj_abo = fecha_expiracion_tarj_abo;
        this.id_abo = id_abo;
        this.id_veh = id_veh;
    }

    public int getId_tarj_abo() {
        return id_tarj_abo;
    }

    public void setId_tarj_abo(int id_tarj_abo) {
        this.id_tarj_abo = id_tarj_abo;
    }

    public String getFecha_emision_tarj_abo() {
        return fecha_emision_tarj_abo;
    }

    public void setFecha_emision_tarj_abo(String fecha_emision_tarj_abo) {
        this.fecha_emision_tarj_abo = fecha_emision_tarj_abo;
    }

    public String getFecha_expiracion_tarj_abo() {
        return fecha_expiracion_tarj_abo;
    }

    public void setFecha_expiracion_tarj_abo(String fecha_expiracion_tarj_abo) {
        this.fecha_expiracion_tarj_abo = fecha_expiracion_tarj_abo;
    }

    public int getId_abo() {
        return id_abo;
    }

    public void setId_abo(int id_abo) {
        this.id_abo = id_abo;
    }

    public int getId_veh() {
        return id_veh;
    }

    public void setId_veh(int id_veh) {
        this.id_veh = id_veh;
    }

    @Override
    public String toString() {
        return "Tarjeta_Abonado{" + "id_tarj_abo=" + id_tarj_abo + ", fecha_emision_tarj_abo=" + fecha_emision_tarj_abo + ", fecha_expiracion_tarj_abo=" + fecha_expiracion_tarj_abo + ", id_abo=" + id_abo + ", id_veh=" + id_veh + '}';
    }
    
}
