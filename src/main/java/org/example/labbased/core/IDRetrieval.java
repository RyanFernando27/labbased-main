package org.example.labbased.core;

import java.util.List;

public class IDRetrieval implements TicketRetrievalStrategy{
    @Override
    public String retrievalByPriority(TicketPool ticketPool){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public String retrievalByID(TicketPool ticketPool , String id){
        List<String> tickets = ticketPool.getTickets();
        for(String ticket : tickets){
            if(ticket.equals(id)){
                return ticket;
            }
        }
        return null;
    }

}
