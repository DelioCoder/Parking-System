package com.parkingsystem.controller;

import com.parkingsystem.DAO.Piso_EstacionamientoDAO;
import com.parkingsystem.model.Piso_estacionamiento;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class PisoController
{
    private Piso_EstacionamientoDAO pisoDao;

    public PisoController(Piso_EstacionamientoDAO pisoDao) {
        this.pisoDao = pisoDao;
    }
    
    public ArrayList<Piso_estacionamiento> listarPisos()
    {
        return this.pisoDao.listarPisos();
    }
    
    public boolean registrarPisoEstacionamento(Piso_estacionamiento piso)
    {
        return this.pisoDao.agregarPiso(piso);
    }
    
    public boolean actualizarPisoEstacionamiento(Piso_estacionamiento piso)
    {
        return this.pisoDao.actualizarPiso(piso);
    }
    
    public boolean eliminarPisoEstacionamiento(int id)
    {
        return this.pisoDao.eliminarPiso(id);
    }
}
