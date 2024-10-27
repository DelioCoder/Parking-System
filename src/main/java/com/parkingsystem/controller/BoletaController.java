package com.parkingsystem.controller;

import com.parkingsystem.DAO.Boleta_PagoDAO;
import com.parkingsystem.model.Boleta_Pago;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class BoletaController
{
    private Boleta_PagoDAO boletaDao;

    public BoletaController(Boleta_PagoDAO boletaDao) {
        this.boletaDao = boletaDao;
    }
    
    public ArrayList<Boleta_Pago> listarBoletas(String filter)
    {
        return this.boletaDao.listarBoletas();
    }
    
    public boolean registrarBoleta(Boleta_Pago boleta)
    {
        return this.boletaDao.agregarBoleta(boleta);
    }
    
    public boolean actualizarBoleta(Boleta_Pago boleta)
    {
        return this.boletaDao.actualizarBoleta(boleta);
    }
    
    public boolean eliminarBoleta(int id)
    {
        return this.boletaDao.eliminarBoleta(id);
    }
}
