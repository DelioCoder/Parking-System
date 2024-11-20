package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Abonado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class AbonadoDAO {
    
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;

    public ArrayList<Abonado> listarAbonados(String filter, ArrayList<String> data) {
        ArrayList<Abonado> list = new ArrayList<>();
        Abonado abonado;
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "";

                switch (filter) {
                    case "tipo":
                        sql = "SELECT * FROM abonado WHERE tipo_abo = ?";
                        pst = connection.prepareStatement(sql);
                        pst.setString(1, data.get(0));
                        break;

                    case "monto":
                        sql = "SELECT * FROM abonado WHERE monto_abo = ?";
                        pst = connection.prepareStatement(sql);
                        pst.setFloat(1, Float.parseFloat(data.get(0)));
                        break;

                    default:
                        sql = "SELECT * FROM abonado";
                        pst = connection.prepareStatement(sql);
                        break;
                }

                rs = pst.executeQuery();

                while (rs.next()) {
                    abonado = new Abonado();

                    abonado.setId_abo(rs.getInt("id_abo"));
                    abonado.setFecha_inicio_abo(rs.getString("fecha_inicio_abo"));
                    abonado.setFecha_fin_abo(rs.getString("fecha_fin_abo"));
                    abonado.setTipo_abo(rs.getString("tipo_abo"));
                    abonado.setMonto_abo(rs.getFloat("monto_abo"));

                    list.add(abonado);
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

    public boolean agregarAbonado(Abonado abonado) {
        boolean state = false;
        connection = connectionManager.connect();
        try {
         
            if (connection != null) {
                String sql = "INSERT INTO abonado (fecha_inicio_abo, fecha_fin_abo, tipo_abo, monto_abo) VALUES (?, ?, ?, ?)";

                pst = connection.prepareStatement(sql);
                
                pst.setString(1, abonado.getFecha_inicio_abo());
                pst.setString(2, abonado.getFecha_fin_abo());
                pst.setString(3, abonado.getTipo_abo());
                pst.setFloat(4, abonado.getMonto_abo());

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

    public boolean actualizarAbonado(Abonado abonado) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "UPDATE abonado SET fecha_inicio_abo = ?, fecha_fin_abo = ?, tipo_abo = ?, monto_abo = ? WHERE id_abo = ?";

                pst = connection.prepareStatement(sql);
                pst.setString(1, abonado.getFecha_inicio_abo());
                pst.setString(2, abonado.getFecha_fin_abo());
                pst.setString(3, abonado.getTipo_abo());
                pst.setFloat(4, abonado.getMonto_abo());
                pst.setInt(5, abonado.getId_abo());

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

    public boolean eliminarAbonado(int id) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
                String sql = "DELETE FROM abonado WHERE id_abo = ?";

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
