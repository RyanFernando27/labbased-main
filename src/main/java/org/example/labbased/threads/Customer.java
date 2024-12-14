package org.example.labbased.threads;
import org.example.labbased.core.AbstractTicketHandler;
import org.example.labbased.logging.Logger;
import org.example.labbased.core.TicketPool;

public class Customer extends AbstractTicketHandler implements Runnable {
    public Customer(TicketPool ticketPool) {
        super(ticketPool);
    }
    @Override
    public void run() {
        while (true) {
            String ticket = ticketPool.removeTicket();
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