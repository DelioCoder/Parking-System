package com.parkingsystem.controller;

/**
 *
 * @author david
 */
/*
public class VehiculoController {

package com.parkingsystem.controller;

public class VehiculoController {
    private VehiculoForm vista;
    private VehiculoDAO vehiculoDAO;

    public VehiculoController(VehiculoForm vista, VehiculoDAO vehiculoDAO) {
        this.vista = vista;
        this.vehiculoDAO = vehiculoDAO;

        // listeners de botones de la vista
        this.vista.addGuardarListener(new GuardarListener());
        this.vista.addActualizarListener(new ActualizarListener());
        this.vista.addEliminarListener(new EliminarListener());
        this.vista.addListarListener(new ListarListener());
    }

    // Listener para el botón de guardar
    class GuardarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca_veh(vista.getPlaca());
            vehiculo.setColor_veh(vista.getColor());
            vehiculo.setMarca_veh(vista.getMarca());
            vehiculo.setAño_veh(vista.getAño());
            vehiculo.setId_conductor(vista.getIdConductor());

            // Llamada al DAO para guardar el vehículo
            boolean success = vehiculoDAO.agregarVehiculo(vehiculo);
            if (success) {
                vista.mostrarMensaje("Vehículo guardado con éxito");
            } else {
                vista.mostrarMensaje("Error al guardar el vehículo");
            }
        }
    }

    // Listener para el botón de actualizar
    class ActualizarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setId(vista.getIdVehiculo());
            vehiculo.setPlaca_veh(vista.getPlaca());
            vehiculo.setColor_veh(vista.getColor());
            vehiculo.setMarca_veh(vista.getMarca());
            vehiculo.setAño_veh(vista.getAño());
            vehiculo.setId_conductor(vista.getIdConductor());

            // Llamada al DAO para actualizar el vehículo
            boolean success = vehiculoDAO.actualizarVehiculo(vehiculo);
            if (success) {
                vista.mostrarMensaje("Vehículo actualizado con éxito");
            } else {
                vista.mostrarMensaje("Error al actualizar el vehículo");
            }
        }
    }

    // Listener para el botón de eliminar
    class EliminarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = vista.getIdVehiculo();
            boolean success = vehiculoDAO.eliminarVehiculo(id);
            if (success) {
                vista.mostrarMensaje("Vehículo eliminado con éxito");
            } else {
                vista.mostrarMensaje("Error al eliminar el vehículo");
            }
        }
    }

    // Listener para el botón de listar
    class ListarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String filtro = vista.getFiltro();
            int id = vista.getIdVehiculo();
            ArrayList<Vehiculo> vehiculos = vehiculoDAO.listarVehiculos(filtro, id);
            
            // Mostrar la lista de vehículos en la vista
            vista.mostrarVehiculos(vehiculos);
        }
    }
}

    
}

*/