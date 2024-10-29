package com.parkingsystem.model;

/**
 *
 * @author david
 */
public class Boleta_Pago {

    private int id_boleta_pago;
    private String fecha_pago;
    private String hora_salida;
    private Float monto_pago;
    private String metodo_pago;
    private int id_ticket;

    public Boleta_Pago() { }

    public Boleta_Pago(
            int id_boleta_pago,
            String fecha_pago,
            String hora_salida,
            Float monto_pago,
            String metodo_pago,
            int id_ticket)
    {
        this.id_boleta_pago = id_boleta_pago;
        this.fecha_pago = fecha_pago;
        this.hora_salida = hora_salida;
        this.monto_pago = monto_pago;
        this.metodo_pago = metodo_pago;
        this.id_ticket = id_ticket;
    }
    
    public int getId_boleta_pago() {
        return id_boleta_pago;
    }

    public void setId_boleta_pago(int id_boleta_pago) {
        this.id_boleta_pago = id_boleta_pago;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Float getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(Float monto_pago) {
        this.monto_pago = monto_pago;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    @Override
    public String toString() {
        return "Boleta_Pago{" + "id_boleta_pago=" + id_boleta_pago + ", fecha_pago=" + fecha_pago + ", hora_salida=" + hora_salida + ", monto_pago=" + monto_pago + ", metodo_pago=" + metodo_pago + ", id_ticket=" + id_ticket + '}';
    }
    
}
