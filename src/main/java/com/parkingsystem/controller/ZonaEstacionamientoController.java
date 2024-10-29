package com.parkingsystem.controller;

import com.parkingsystem.DAO.Zona_EstacionamientoDAO;
import com.parkingsystem.model.Zona_Estacionamiento;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class ZonaEstacionamientoController
{
    private Zona_EstacionamientoDAO zonaDao;

    public ZonaEstacionamientoController(Zona_EstacionamientoDAO zonaDao) {
        this.zonaDao = zonaDao;
    }
    
    public ArrayList<Zona_Estacionamiento> listarZonasEstacionamiento()
    {
        return this.zonaDao.listarZonas();
    }
    
    public boolean registrarZonaEstacionamiento(Zona_Estacionamiento zona)
    {
        return this.zonaDao.agregarZona(zona);
    }
    
    public boolean actualizarZonaEstacionamiento(Zona_Estacionamiento zona)
    {
        return this.zonaDao.actualizarZona(zona);
    }
    
    public boolean eliminarZonaEstacionamiento(int id)
    {
        return this.zonaDao.eliminarZona(id);
    }
}
