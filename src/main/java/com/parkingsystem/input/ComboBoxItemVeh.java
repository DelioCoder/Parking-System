package com.parkingsystem.input;

/**
 *
 * @author david
 */
public class ComboBoxItemVeh {
 
    private int id;
    private String placa;

    public ComboBoxItemVeh(int id, String placa) {
        this.id = id;
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.placa;
    }
    
}
