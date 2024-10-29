package com.parkingsystem.controller;

import com.parkingsystem.DAO.AbonadoDAO;
import com.parkingsystem.model.Abonado;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class AbonadoController {
    private AbonadoDAO abonadoDao;

    public AbonadoController(AbonadoDAO abonadoDao) {
        this.abonadoDao = abonadoDao;
    }
    
    public ArrayList<Abonado> listarAbonados(String filter, ArrayList<String> data)
    {
        return this.abonadoDao.listarAbonados(filter, data);
    }
    
    public boolean agregarAbonado(Abonado abonado)
    {
        return this.abonadoDao.agregarAbonado(abonado);
    }
    
    public boolean actualizarAbonado(Abonado abonado)
    {
        return this.abonadoDao.actualizarAbonado(abonado);
    }
    
    public boolean eliminarAbonado(int id)
    {
        return this.abonadoDao.eliminarAbonado(id);
    }
}
