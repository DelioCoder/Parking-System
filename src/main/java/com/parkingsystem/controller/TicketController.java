package com.parkingsystem.controller;

import com.parkingsystem.DAO.TicketDAO;
import com.parkingsystem.model.Ticket_Estacionamiento;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class TicketController
{
    private TicketDAO ticketDao;

    public TicketController(TicketDAO ticketDao) {
        this.ticketDao = ticketDao;
    }
    
    public ArrayList<Ticket_Estacionamiento> listarTickets(String filter)
    {
        return this.ticketDao.listarTickets(filter, 0);
    }
    
    public boolean registrarTicket(Ticket_Estacionamiento ticket)
    {
        return this.ticketDao.agregarTicket(ticket);
    }
    
    public boolean actualizarTicket(Ticket_Estacionamiento ticket)
    {
        return this.ticketDao.actualizarTicket(ticket);
    }
    
    public boolean eliminarTicket(int id)
    {
        return this.ticketDao.eliminarTicket(id);
    }
}
