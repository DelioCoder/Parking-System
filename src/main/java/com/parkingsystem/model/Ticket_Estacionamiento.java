package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Ticket_Estacionamiento {

    private int id_ticket;
    private String fecha_entrada;
    private String hora_entrada;
    private String estado_ticket;
    private int id_veh;
    private int id_zona_est;

    private String placa_veh;
    private String color_veh;
    private String marca_veh;
    
    private String nombre_cond;
    private String apellido_cond;
    private int dni_cond;
    private int telefono_cond;
    
    private String nombre_zona_est;
    
    private int numero_piso;
    
    public Ticket_Estacionamiento() { }

    public Ticket_Estacionamiento(int id_ticket, String fecha_entrada, String hora_entrada, String estado_ticket, int id_veh, int id_zona_est, String placa_veh, String color_veh, String marca_veh, String nombre_cond, String apellido_cond, int dni_cond, int telefono_cond, String nombre_zona_est, int numero_piso) {
        this.id_ticket = id_ticket;
        this.fecha_entrada = fecha_entrada;
        this.hora_entrada = hora_entrada;
        this.estado_ticket = estado_ticket;
        this.id_veh = id_veh;
        this.id_zona_est = id_zona_est;
        this.placa_veh = placa_veh;
        this.color_veh = color_veh;
        this.marca_veh = marca_veh;
        this.nombre_cond = nombre_cond;
        this.apellido_cond = apellido_cond;
        this.dni_cond = dni_cond;
        this.telefono_cond = telefono_cond;
        this.nombre_zona_est = nombre_zona_est;
        this.numero_piso = numero_piso;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getEstado_ticket() {
        return estado_ticket;
    }

    public void setEstado_ticket(String estado_ticket) {
        this.estado_ticket = estado_ticket;
    }

    public int getId_veh() {
        return id_veh;
    }

    public void setId_veh(int id_veh) {
        this.id_veh = id_veh;
    }

    public int getId_zona_est() {
        return id_zona_est;
    }

    public void setId_zona_est(int id_zona_est) {
        this.id_zona_est = id_zona_est;
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

    public int getDni_cond() {
        return dni_cond;
    }

    public void setDni_cond(int dni_cond) {
        this.dni_cond = dni_cond;
    }

    public int getTelefono_cond() {
        return telefono_cond;
    }

    public void setTelefono_cond(int telefono_cond) {
        this.telefono_cond = telefono_cond;
    }

    public String getNombre_zona_est() {
        return nombre_zona_est;
    }

    public void setNombre_zona_est(String nombre_zona_est) {
        this.nombre_zona_est = nombre_zona_est;
    }

    public int getNumero_piso() {
        return numero_piso;
    }

    public void setNumero_piso(int numero_piso) {
        this.numero_piso = numero_piso;
    }
    
        
}
