package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.Ticket_Estacionamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author david
 */
public class TicketDAO {

    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    
    public boolean agregarTicket(Ticket_Estacionamiento ticket){
        boolean state = false;
        
        try {
            if(connection != null){
                String sql = "INSERT INTO ticket (id_ticket, hora_entrada, estado_ticket, id_veh, id_zona_est) VALUES (?,?,?,?,?)";
                
                pst = connection.prepareStatement(sql);
                
                pst.setInt(1, ticket.getId_ticket());
                pst.setString(2, ticket.getHora_entrada());
                pst.setString(3, ticket.getEstado_ticket());
                pst.setInt(4, ticket.getId_veh());
                pst.setInt(5, ticket.getId_zona_est());
                
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
