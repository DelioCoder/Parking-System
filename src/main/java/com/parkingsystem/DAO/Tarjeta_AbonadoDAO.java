package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Tarjeta_Abonado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Tarjeta_AbonadoDAO {
    
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;

    public ArrayList<Tarjeta_Abonado> listarTarjetasAbonado(String filter, ArrayList<String> data) {
        ArrayList<Tarjeta_Abonado> list = new ArrayList<>();
        Tarjeta_Abonado tarjetaAbonado;
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "";

                switch (filter) {
                    case "id_abo":
                        sql = "SELECT * FROM tarjeta_abonado WHERE id_abo = ?";
                        pst = connection.prepareCall(sql);
                        pst.setInt(1, Integer.parseInt(data.get(0)));
                        break;

                    case "id_veh":
                        sql = "SELECT * FROM tarjeta_abonado WHERE id_veh = ?";
                        pst = connection.prepareCall(sql);
                        pst.setInt(1, Integer.parseInt(data.get(0)));
                        break;

                    default:
                        sql = "SELECT * FROM tarjeta_abonado";
                        pst = connection.prepareCall(sql);
                        break;
                }

                rs = pst.executeQuery();

                while (rs.next()) {
                    tarjetaAbonado = new Tarjeta_Abonado();

                    tarjetaAbonado.setId_tarj_abo(rs.getInt("id_tarj_abo"));
                    tarjetaAbonado.setFecha_emision_tarj_abo(rs.getString("fecha_emision_tarj_abo"));
                    tarjetaAbonado.setFecha_expiracion_tarj_abo(rs.getString("fecha_expiracion_tarj_abo"));
                    tarjetaAbonado.setId_abo(rs.getInt("id_abo"));
                    tarjetaAbonado.setId_veh(rs.getInt("id_veh"));

                    list.add(tarjetaAbonado);
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

    public boolean agregarTarjetaAbonado(Tarjeta_Abonado tarjetaAbonado) {
        boolean state = false;

        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "INSERT INTO tarjeta_abonado (id_tarj_abo, fecha_emision_tarj_abo, fecha_expiracion_tarj_abo, id_abo, id_veh) VALUES (?, ?, ?, ?, ?)";

                pst = connection.prepareStatement(sql);

                pst.setInt(1, tarjetaAbonado.getId_tarj_abo());
                pst.setString(2, tarjetaAbonado.getFecha_emision_tarj_abo());
                pst.setString(3, tarjetaAbonado.getFecha_expiracion_tarj_abo());
                pst.setInt(4, tarjetaAbonado.getId_abo());
                pst.setInt(5, tarjetaAbonado.getId_veh());

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

    public boolean actualizarTarjetaAbonado(Tarjeta_Abonado tarjetaAbonado) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "UPDATE tarjeta_abonado SET fecha_emision_tarj_abo = ?, fecha_expiracion_tarj_abo = ?, id_abo = ?, id_veh = ? WHERE id_tarj_abo = ?";

                pst = connection.prepareStatement(sql);
                pst.setString(1, tarjetaAbonado.getFecha_emision_tarj_abo());
                pst.setString(2, tarjetaAbonado.getFecha_expiracion_tarj_abo());
                pst.setInt(3, tarjetaAbonado.getId_abo());
                pst.setInt(4, tarjetaAbonado.getId_veh());
                pst.setInt(5, tarjetaAbonado.getId_tarj_abo());

                int res = pst.executeUpdate();

                state = res > 0;
            } else {
                System.out.println("Error al conectar");
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

    public boolean eliminarTarjetaAbonado(int id) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "DELETE FROM tarjeta_abonado WHERE id_tarj_abo = ?";

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
