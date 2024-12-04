package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Tipo_Abonado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Tipo_AbonadoDAO {
    
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;
    private CallableStatement cst = null;

    public ArrayList<Tipo_Abonado> listarTipoAbonados(){
        ArrayList<Tipo_Abonado> list = new ArrayList<>();
        Tipo_Abonado tipo_abonado;
         try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "{CALL ObtenerTipoAbonado}";
                cst = connection.prepareCall(sql);

                rs = cst.executeQuery();

                // Procesar resultados
                while (rs.next()) {
                    tipo_abonado = new Tipo_Abonado();
                    tipo_abonado.setId_tipo_abo(rs.getInt("id_tipo_abo"));
                    tipo_abonado.setNombre(rs.getString("nombre"));
                    tipo_abonado.setMonto(rs.getInt("monto"));
              
                    list.add(tipo_abonado);
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
    
}
