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

    public ArrayList<Boleta_Pago> listarBoletas() {
        ArrayList<Boleta_Pago> list = new ArrayList<>();
        Boleta_Pago boleta;

        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "SELECT \n" +
"    c.nombre_cond AS Conductor,\n" +
"    c.dni_cond AS DNI,\n" +
"    c.telefono_cond AS Telefono,\n" +
"    v.placa_veh AS Placa,\n" +
"    v.color_veh AS 'Color de vehiculo',\n" +
"    t.fecha_entrada AS 'Hora de entrada',\n" +
"    b.hora_salida AS 'Hora de Salida',\n" +
"	b.monto_pago AS 'Monto',\n" +
"    z.nombre_espacio AS Zona,\n" +
"    p.numero_piso_est AS Piso\n" +
"FROM \n" +
"    Boleta_Pago b\n" +
"JOIN \n" +
"    Ticket_Estacionamiento t ON b.id_ticket = t.id_ticket\n" +
"JOIN \n" +
"    Vehiculo v ON t.id_veh = v.id_veh\n" +
"JOIN \n" +
"    Conductor c ON v.id_cond = c.id_cond\n" +
"JOIN \n" +
"    Zona_Estacionamiento z ON t.id_zona_est = z.numero_espacio\n" +
"JOIN \n" +
"    Piso_estacionamiento p ON z.id_piso_est = p.numero_piso_est;";
                pst = connection.prepareStatement(sql);
                rs = pst.executeQuery();

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
