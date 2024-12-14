package org.example.labbased.core;

public class PriorityRetrieval implements TicketRetrievalStrategy{
    @Override
    public String retrievalByPriority(TicketPool ticketPool){
      return ticketPool.removeTicket();
    }

    @Override
    public String retrievalByID(TicketPool ticketPool, String id) {
       throw new UnsupportedOperationException("Not supported yet.");
    }



}
