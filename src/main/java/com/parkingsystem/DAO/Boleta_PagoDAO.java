package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Boleta_Pago;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Boleta_PagoDAO
{
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;
    private CallableStatement cst = null;

    public ArrayList<Boleta_Pago> listarBoletas() {
        ArrayList<Boleta_Pago> list = new ArrayList<>();
        Boleta_Pago boleta;

        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "{CALL ListarBoletas}";
                cst = connection.prepareCall(sql);
                rs = cst.executeQuery();

                while (rs.next()) {
                    boleta = new Boleta_Pago();

                    boleta.setConductor(rs.getString("Conductor"));
                    boleta.setDni(rs.getString("DNI"));
                    boleta.setTelefono(rs.getString("Telefono"));
                    boleta.setPlaca(rs.getString("Placa"));
                    boleta.setColorVehiculo(rs.getString("Color de vehiculo"));
                    boleta.setHoraEntrada(rs.getString("Hora de entrada"));
                    boleta.setHora_salida(rs.getString("Hora de Salida"));
                    boleta.setMonto_pago(rs.getFloat("Monto"));
                    boleta.setZona(rs.getString("Zona"));
                    boleta.setPiso(rs.getInt("Piso"));

                    list.add(boleta);
                }

            } else {
                System.out.println("Conexión fallida");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        return list;
    }

    public boolean agregarBoleta(Boleta_Pago boleta) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "{CALL AgregarBoleta(?, ?, ?, ?, ?)}";
                cst = connection.prepareCall(sql);

                cst.setString(1, boleta.getFecha_pago());
                cst.setString(2, boleta.getHora_salida());
                cst.setFloat(3, boleta.getMonto_pago());
                cst.setString(4, boleta.getMetodo_pago());
                cst.setInt(5, boleta.getId_ticket());

                int res = cst.executeUpdate();

                state = res > 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (cst != null) cst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }

        return state;
    }

    public boolean actualizarBoleta(Boleta_Pago boleta) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "{CALL ActualizarBoleta(?, ?, ?, ?, ?, ?)}";
                cst = connection.prepareCall(sql);

                cst.setString(1, boleta.getFecha_pago());
                cst.setString(2, boleta.getHora_salida());
                cst.setFloat(3, boleta.getMonto_pago());
                cst.setString(4, boleta.getMetodo_pago());
                cst.setInt(5, boleta.getId_ticket());
                cst.setInt(6, boleta.getId_boleta_pago());

                int res = cst.executeUpdate();

                state = res > 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (cst != null) cst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }

        return state;
    }

    public boolean eliminarBoleta(int id) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "{CALL EliminarBoleta(?)}";
                cst = connection.prepareCall(sql);
                cst.setInt(1, id);

                int res = cst.executeUpdate();

                state = res > 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (cst != null) cst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }

        return state;
    }
}
