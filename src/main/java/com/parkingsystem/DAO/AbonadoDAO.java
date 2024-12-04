package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Abonado;
import java.sql.CallableStatement;
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
    private CallableStatement cst = null;

    public ArrayList<Abonado> listarAbonados(String filter, int id) {
        ArrayList<Abonado> list = new ArrayList<>();
        Abonado abonado;
        
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "{CALL ListarAbonados(?, ?)}";
                
                switch (filter) {
                    case "codigo":
                        cst = connection.prepareCall(sql);
                        cst.setString(1, filter);
                        cst.setInt(2, id);
                        break;
                       
                    case "":
                        cst = connection.prepareCall(sql);
                        cst.setString(1, "");
                        cst.setInt(2, 0);
                        break;    
                        
                    default:
                        cst = connection.prepareCall(sql);
                        break;
                }

                rs = cst.executeQuery();

                // Procesar resultados
                while (rs.next()) {
                    abonado = new Abonado();

                    abonado.setId_abo(rs.getInt("Id_Abonado"));
                    abonado.setFecha_inicio_abo(rs.getString("Fecha_Inicio_Abo"));
                    abonado.setFecha_fin_abo(rs.getString("Fecha_Fin_Abo"));
                    abonado.setPlaca_veh(rs.getString("Placa_Veh"));
                    abonado.setNombre_abonado(rs.getString("Abonado"));
                    
                    list.add(abonado);
                }
            } else {
                System.out.println("Conexión fallida");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (cst != null) cst.close();
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
//                String sql = "INSERT INTO abonado (fecha_inicio_abo, fecha_fin_abo, tipo_abo, monto_abo) VALUES (?, ?, ?, ?)";
//                pst = connection.prepareStatement(sql);
                String sql = "{CALL AgregarAbonado(?, ?, ?, ?)}";
                cst = connection.prepareCall(sql);
                cst.setString(1, abonado.getFecha_inicio_abo());
                cst.setString(2, abonado.getFecha_fin_abo());
                cst.setInt(3, abonado.getId_vehiculo());
                cst.setInt(4, abonado.getId_tipo_abonado());

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

    public boolean actualizarAbonado(Abonado abonado) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
//                String sql = "UPDATE abonado SET fecha_inicio_abo = ?, fecha_fin_abo = ?, tipo_abo = ?, monto_abo = ? WHERE id_abo = ?";
//                pst = connection.prepareStatement(sql);
                String sql = "{CALL ActualizarAbonado(?, ?, ?, ?, ?)}";
                cst = connection.prepareCall(sql);
                
                cst.setInt(1, abonado.getId_abo());
                cst.setString(2, abonado.getFecha_inicio_abo());
                cst.setString(3, abonado.getFecha_fin_abo());
                cst.setInt(4, abonado.getId_vehiculo());
                cst.setInt(5, abonado.getId_tipo_abonado());
                

                int res = cst.executeUpdate();

                state = res > 0;
            } else {
                System.out.println("Error al conectar");
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

    public boolean eliminarAbonado(int id) {
        boolean state = false;

        try {
            connection = connectionManager.connect();

            if (connection != null) {
//                String sql = "DELETE FROM abonado WHERE id_abo = ?";
//                pst = connection.prepareStatement(sql);
                String sql = "{CALL EliminarAbonado(?)}";
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
