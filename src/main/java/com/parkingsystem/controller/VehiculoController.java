package com.parkingsystem.controller;

import com.parkingsystem.DAO.VehiculoDAO;
import com.parkingsystem.model.Vehiculo;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class VehiculoController {
    
    private VehiculoDAO vehiculoDao;

    public VehiculoController(VehiculoDAO vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }
    
    public ArrayList<Vehiculo> listarVehiculos(String filter, ArrayList<String> data)
    {
        return this.vehiculoDao.listarVehiculos(filter, data);
    }
    
    public boolean registrarVehiculo(Vehiculo vehiculo)
    {
        return this.vehiculoDao.agregarVehiculo(vehiculo);
    }
    
    public boolean actualizarVehiculo(Vehiculo vehiculo)
    {
        return this.vehiculoDao.actualizarVehiculo(vehiculo);
    }
    
    public boolean eliminarVehiculo(int id)
    {
        return this.vehiculoDao.eliminarVehiculo(id);
    }
    
}
