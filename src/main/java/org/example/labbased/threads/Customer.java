package org.example.labbased.threads;
import org.example.labbased.core.*;
import org.example.labbased.logging.Logger;

public class Customer extends AbstractTicketHandler implements Runnable {
    TicketRetrievalStrategy ticketRetrievalStrategy;

    public Customer(TicketPool ticketPool, TicketRetrievalStrategy ticketRetrievalStrategy) {
        super(ticketPool);
        this.ticketRetrievalStrategy =ticketRetrievalStrategy;
    }

    @Override
    public void run() {
        while (true) {
            String ticket;
            if(this.ticketRetrievalStrategy instanceof PriorityRetrieval){
                ticket= ticketRetrievalStrategy.retrievalByPriority(ticketPool);
            } else if (this.ticketRetrievalStrategy instanceof IDRetrieval) {
                ticket=ticketRetrievalStrategy.retrievalByID(ticketPool , "Ticket-1");
            }
            else{
                ticket = null;
                System.out.println("Invalid retrieval strategy.");
            }
            if (ticket != null) {
                Logger.log("Customer retrieved: " + ticket);
            } else {
                Logger.log("Customer found no tickets available.");
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Customer interrupted.");
            }
        }
    }
    @Override
    public void handleTickets() {
        run();
    }
}