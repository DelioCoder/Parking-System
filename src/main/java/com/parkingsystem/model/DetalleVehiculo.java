package com.parkingsystem.model;

/**
 *
 * @author john
 */
public class DetalleVehiculo {
    
    private Conductor conductor;
    private Vehiculo vehiculo;
    private Abonado abonado;
    private Ticket_Estacionamiento ticket;
    private Zona_Estacionamiento zona;
    private Piso_estacionamiento piso;
    private Boleta_Pago boletaPago;
    private Tipo_Abonado tipoAbonado;
    
    public DetalleVehiculo() {
             
    }

    public Tipo_Abonado getTipoAbonado() {
        return tipoAbonado;
    }

    public void setTipoAbonado(Tipo_Abonado tipoAbonado) {
        this.tipoAbonado = tipoAbonado;
    }

    public Boleta_Pago getBoletaPago() {
        return boletaPago;
    }

    public void setBoletaPago(Boleta_Pago boletaPago) {
        this.boletaPago = boletaPago;
    }
        
    public Zona_Estacionamiento getZona() {
        return zona;
    }

    public void setZona(Zona_Estacionamiento zona) {
        this.zona = zona;
    }

    public Piso_estacionamiento getPiso() {
        return piso;
    }

    public void setPiso(Piso_estacionamiento piso) {
        this.piso = piso;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Abonado getAbonado() {
        return abonado;
    }

    public void setAbonado(Abonado abonado) {
        this.abonado = abonado;
    }

    public Ticket_Estacionamiento getTicket() {
        return ticket;
    }

    public void setTicket(Ticket_Estacionamiento ticket) {
        this.ticket = ticket;
    }
    


}
