package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Boleta_Pago;
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

    // Método para listar todas las boletas de pago
    public ArrayList<Boleta_Pago> listarBoletas() {
        ArrayList<Boleta_Pago> list = new ArrayList<>();
        Boleta_Pago boleta;

        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "SELECT * FROM Boleta_Pago";
                pst = connection.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    boleta = new Boleta_Pago();

                    boleta.setId_boleta_pago(rs.getInt("id_boleta_pago"));
                    boleta.setFecha_pago(rs.getString("fecha_pago"));
                    boleta.setHora_salida(rs.getString("hora_salida"));
                    boleta.setMonto_pago(rs.getFloat("monto_pago"));
                    boleta.setMetodo_pago(rs.getString("metodo_pago"));
                    boleta.setId_ticket(rs.getInt("id_ticket"));

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

    // Método para agregar una boleta de pago
    public boolean agregarBoleta(Boleta_Pago boleta) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "INSERT INTO Boleta_Pago (id_boleta_pago, fecha_pago, hora_salida, monto_pago, metodo_pago, id_ticket) VALUES (?, ?, ?, ?, ?, ?)";
                pst = connection.prepareStatement(sql);

                pst.setInt(1, boleta.getId_boleta_pago());
                pst.setString(2, boleta.getFecha_pago());
                pst.setString(3, boleta.getHora_salida());
                pst.setFloat(4, boleta.getMonto_pago());
                pst.setString(5, boleta.getMetodo_pago());
                pst.setInt(6, boleta.getId_ticket());

                int res = pst.executeUpdate();

                state = res > 0;
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

        return state;
    }

    // Método para actualizar una boleta de pago
    public boolean actualizarBoleta(Boleta_Pago boleta) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "UPDATE Boleta_Pago SET fecha_pago = ?, hora_salida = ?, monto_pago = ?, metodo_pago = ?, id_ticket = ? WHERE id_boleta_pago = ?";
                pst = connection.prepareStatement(sql);

                pst.setString(1, boleta.getFecha_pago());
                pst.setString(2, boleta.getHora_salida());
                pst.setFloat(3, boleta.getMonto_pago());
                pst.setString(4, boleta.getMetodo_pago());
                pst.setInt(5, boleta.getId_ticket());
                pst.setInt(6, boleta.getId_boleta_pago());

                int res = pst.executeUpdate();

                state = res > 0;
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

        return state;
    }

    // Método para eliminar una boleta de pago
    public boolean eliminarBoleta(int id) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "DELETE FROM Boleta_Pago WHERE id_boleta_pago = ?";
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);

                int res = pst.executeUpdate();

                state = res > 0;
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

        return state;
    }
}
