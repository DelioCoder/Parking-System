package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.Ticket_Estacionamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class TicketDAO {

    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ArrayList<Ticket_Estacionamiento> listarTickets(String filter, int id) {
        ArrayList<Ticket_Estacionamiento> list = new ArrayList<>();
        Ticket_Estacionamiento ticket;
        try {
            connection = connectionManager.connect();
            if(connection != null){
                String sql = "";
                
                switch (filter) {
                    case "codigo":
                        sql = "SELECT * FROM Ticket_Estacionamiento WHERE id_ticket = ?";
                        pst = connection.prepareStatement(sql);
                        pst.setInt(1, id);
                        break;
                       
                    default:
                        sql = "SELECT * FROM Ticket_Estacionamiento";
                        pst = connection.prepareStatement(sql);
                        break;
                }
                
                rs = pst.executeQuery();
                
                while(rs.next()){
                    ticket = new Ticket_Estacionamiento();
                    
                    ticket.setId_veh(rs.getInt("id_ticket"));
                    ticket.setHora_entrada(rs.getString("hora_entrada"));
                    ticket.setEstado_ticket(rs.getString("estado_ticket"));
                    ticket.setId_veh(rs.getInt("id_veh"));
                    ticket.setId_zona_est(rs.getInt("id_zona_est"));
                    
                    list.add(ticket);

                }
                
            }else {
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
    
    public boolean agregarTicket(Ticket_Estacionamiento ticket) {
        boolean state = false;
        connection = connectionManager.connect();
        try {
            if (connection != null) {
                String sql = "INSERT INTO Ticket_Estacionamiento (fecha_entrada, hora_entrada, estado_ticket, id_veh, id_zona_est) VALUES (?, ?, ?, ?, ?)";

                pst = connection.prepareStatement(sql);

                pst.setString(1, ticket.getFecha_entrada());
                pst.setString(2, ticket.getHora_entrada());
                pst.setString(3, ticket.getEstado_ticket());
                pst.setInt(4, ticket.getId_veh());
                pst.setInt(5, ticket.getId_zona_est());

                int res = pst.executeUpdate();

                state = res > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al agregar ticket: " + e.toString());
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

    
    public boolean actualizarTicket(Ticket_Estacionamiento ticket)
    {
    
        boolean state = false;
        
        try {
            
            connection = connectionManager.connect();
            
            if(connection != null){
                
                String sql = "UPDATE Ticket_Estacionamiento SET fecha_entrada = ?, hora_entrada = ?, estado_ticket = ?, id_veh = ?, id_zona_est = ? WHERE id_ticket = ?";
                
                pst = connection.prepareStatement(sql);
                pst.setString(1, ticket.getHora_entrada());
                pst.setString(2, ticket.getFecha_entrada());
                pst.setString(3, ticket.getEstado_ticket());
                pst.setInt(4, ticket.getId_veh());
                pst.setInt(5, ticket.getId_zona_est());
                pst.setInt(6, ticket.getId_ticket());
                
                int res = pst.executeUpdate();
                
                state = res > 0;
                
            }else {
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
    
    public boolean eliminarTicket(int id)
    {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if(connection != null)
            {
                String sql = "DELETE FROM Ticket_Estacionamiento WHERE id_ticket = ?";
                
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
