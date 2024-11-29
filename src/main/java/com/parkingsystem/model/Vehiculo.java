package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Vehiculo extends Conductor {
    
    private int id;
    private String placa_veh;
    private String color_veh;
    private String marca_veh;
    private String modelo_veh;
    private String año_veh;
    private int id_conductor;
    
    public Vehiculo() {
             
    }
    
    public Vehiculo(int id, String placa_veh, String color_veh, String marca_veh, String modelo_veh, String año_veh, int id_conductor) {
        this.id = id;
        this.placa_veh = placa_veh;
        this.color_veh = color_veh;
        this.marca_veh = marca_veh;
        this.modelo_veh = modelo_veh;
        this.año_veh = año_veh;
        this.id_conductor = id_conductor;
    }

    @Override
    public void setNombre_cond(String nombre_cond) {
        super.setNombre_cond(nombre_cond); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getNombre_cond() {
        return super.getNombre_cond(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getDni_cond() {
        return super.getDni_cond(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setDni_cond(String dni_cond) {
        super.setDni_cond(dni_cond); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca_veh() {
        return placa_veh;
    }

    public void setPlaca_veh(String placa_veh) {
        this.placa_veh = placa_veh;
    }

    public String getColor_veh() {
        return color_veh;
    }

    public void setColor_veh(String color_veh) {
        this.color_veh = color_veh;
    }

    public String getMarca_veh() {
        return marca_veh;
    }

    public void setMarca_veh(String marca_veh) {
        this.marca_veh = marca_veh;
    }

    public String getModelo_veh() {
        return modelo_veh;
    }

    public void setModelo_veh(String modelo_veh) {
        this.modelo_veh = modelo_veh;
    }

    
    
    public String getAño_veh() {
        return año_veh;
    }

    public void setAño_veh(String año_veh) {
        this.año_veh = año_veh;
    }

    public int getId_conductor() {
        return id_conductor;
    }

    public void setId_conductor(int id_conductor) {
        this.id_conductor = id_conductor;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", placa_veh=" + placa_veh + ", color_veh=" + color_veh + ", marca_veh=" + marca_veh + ", a\u00f1o_veh=" + año_veh + ", id_conductor=" + id_conductor + '}';
    }
    
}
