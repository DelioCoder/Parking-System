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
   
    private String conductor;
    private String dni;
    private String telefono;
    private String placa;
    private String colorVehiculo;
    private String horaEntrada;
    private String zona;
    private int piso;

    public Boleta_Pago() { }

    public Boleta_Pago(
            int id_boleta_pago,
            String fecha_pago,
            String hora_salida,
            Float monto_pago,
            String metodo_pago,
            int id_ticket,
            String conductor,
            String dni,
            String telefono,
            String placa,
            String colorVehiculo,
            String horaEntrada,
            String zona,
            int piso)
    {
        this.id_boleta_pago = id_boleta_pago;
        this.fecha_pago = fecha_pago;
        this.hora_salida = hora_salida;
        this.monto_pago = monto_pago;
        this.metodo_pago = metodo_pago;
        this.id_ticket = id_ticket;
        this.conductor = conductor;
        this.dni = dni;
        this.telefono = telefono;
        this.placa = placa;
        this.colorVehiculo = colorVehiculo;
        this.horaEntrada = horaEntrada;
        this.zona = zona;
        this.piso = piso;
    }

    // Getters y Setters para todos los campos

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

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColorVehiculo() {
        return colorVehiculo;
    }

    public void setColorVehiculo(String colorVehiculo) {
        this.colorVehiculo = colorVehiculo;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    // Método toString actualizado con todos los campos
    @Override
    public String toString() {
        return "Boleta_Pago{" +
                "id_boleta_pago=" + id_boleta_pago +
                ", fecha_pago='" + fecha_pago + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", monto_pago=" + monto_pago +
                ", metodo_pago='" + metodo_pago + '\'' +
                ", id_ticket=" + id_ticket +
                ", conductor='" + conductor + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", placa='" + placa + '\'' +
                ", colorVehiculo='" + colorVehiculo + '\'' +
                ", horaEntrada='" + horaEntrada + '\'' +
                ", zona='" + zona + '\'' +
                ", piso=" + piso +
                '}';
    }
}
