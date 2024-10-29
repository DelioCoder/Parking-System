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

    public Ticket_Estacionamiento() { }
    
    public Ticket_Estacionamiento(int id_ticket, String fecha_entrada, String hora_entrada, String estado_ticket, int id_veh, int id_zona_est) {
        this.id_ticket = id_ticket;
        this.fecha_entrada = fecha_entrada;
        this.hora_entrada = hora_entrada;
        this.estado_ticket = estado_ticket;
        this.id_veh = id_veh;
        this.id_zona_est = id_zona_est;
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

    @Override
    public String toString() {
        return "Ticket_Estacionamiento{" + "id_ticket=" + id_ticket + ", hora_entrada=" + hora_entrada + ", estado_ticket=" + estado_ticket + ", id_veh=" + id_veh + ", id_zona_est=" + id_zona_est + '}';
    }
    
}
