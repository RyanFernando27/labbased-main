package org.example.labbased.core;

public interface TicketRetrievalStrategy {
//    PriorityRetrieval and IDRetrieval
    String retrievalByPriority(TicketPool ticketPool);
    String retrievalByID(TicketPool ticketPool , String id);

}
