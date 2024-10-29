package com.parkingsystem.controller;

import com.parkingsystem.DAO.ConductorDAO;
import com.parkingsystem.model.Conductor;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class ConductorController
{

    private ConductorDAO conductorDAO;

    public ConductorController(ConductorDAO conductorDAO)
    {
        this.conductorDAO = conductorDAO;
    }
    
    public ArrayList<Conductor> listarConductores(String filter, ArrayList<String> data)
    {
        return this.conductorDAO.listarConductores(filter, data);
    }
    
    public boolean registrarConductor(Conductor conductor)
    {
        return this.conductorDAO.agregarConductor(conductor);
    }
    
    public boolean actualizarConductor(Conductor conductor)
    {
        return this.conductorDAO.actualizarConductor(conductor);
    }
    
    public boolean eliminarConductor(int id)
    {
        return this.conductorDAO.eliminarConductor(id);
    }
    
}
