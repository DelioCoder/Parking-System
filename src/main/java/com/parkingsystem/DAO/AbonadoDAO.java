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

    public ArrayList<Abonado> listarAbonados(String filter, ArrayList<String> data) {
        ArrayList<Abonado> list = new ArrayList<>();
        Abonado abonado;
        
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "{CALL ListarAbonados(?, ?)}";
                cst = connection.prepareCall(sql);

                // Configurar parámetros del procedimiento almacenado
                cst.setString(1, filter); // El filtro siempre es el primer parámetro
                if (data != null && !data.isEmpty()) {
                    cst.setString(2, data.get(0)); // Segundo parámetro opcional
                } else {
                    cst.setNull(2, java.sql.Types.NVARCHAR); // Pasar NULL si no hay datos
                }

                rs = cst.executeQuery();

                // Procesar resultados
                while (rs.next()) {
                    abonado = new Abonado();

                    abonado.setId_abo(rs.getInt("id_abo"));
                    abonado.setFecha_inicio_abo(rs.getString("fecha_inicio_abo"));
                    abonado.setFecha_fin_abo(rs.getString("fecha_fin_abo"));
                    abonado.setId_vehiculo(rs.getInt("id_vehiculo"));
                    abonado.setId_tipo_abonado(rs.getInt("id_tipo_abonado"));

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
