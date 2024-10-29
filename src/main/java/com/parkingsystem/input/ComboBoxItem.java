package com.parkingsystem.input;

/**
 *
 * @author david
 */
public class ComboBoxItem {
  
    private int id;
    private String name;

    public ComboBoxItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name; // Esto mostrará el nombre en el JComboBox
    }
    
}
