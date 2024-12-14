package org.example.labbased.core;
import org.example.labbased.logging.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class TicketPool implements TicketOperation {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    @Override
    public synchronized void addTickets(String ticket) {
        tickets.add(ticket);
        Logger.log("Ticket added: "+ticket);
    }
    @Override
    public synchronized String removeTicket() {
        if(!tickets.isEmpty()){
            String ticket = tickets.remove(0);
            Logger.log("Ticket removed: "+ticket);
            return ticket;
        }
        return null;
    }
    public int getTicketCount() {
        return tickets.size();
    }
    public List<String> getTickets() {
        return tickets;
    }
}
