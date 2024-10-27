package com.parkingsystem.controller;

import com.parkingsystem.DAO.AbonadoDAO;
import com.parkingsystem.DAO.Tarjeta_AbonadoDAO;
import com.parkingsystem.model.Abonado;
import com.parkingsystem.model.Tarjeta_Abonado;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class TarjetaAbonadoController
{
    private AbonadoDAO abonadoDao;

    public TarjetaAbonadoController(AbonadoDAO abonadoDao) {
        this.abonadoDao = abonadoDao;
    }
    
    public ArrayList<Abonado> listarAbonados(String filter, ArrayList<String> data)
    {
        return this.abonadoDao.listarAbonados(filter, data);
    }
    
    public boolean registrarAbonado(Abonado abonado)
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
