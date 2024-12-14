package org.example.labbased.threads;
import org.example.labbased.core.AbstractTicketHandler;
import org.example.labbased.core.TicketPool;
import org.example.labbased.logging.Logger;

public class Vendor extends AbstractTicketHandler implements Runnable {
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        super(ticketPool);
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < ticketReleaseRate; i++) {
            int ticketNum = i+1;
            String ticket = "Ticket-" + ticketNum;
            ticketPool.addTickets(ticket);
            Logger.log("Vendor added: " + ticket);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Logger.log("Vendor interrupted.");
            }
        }
    }

    @Override
    public void handleTickets() {
        run();
    }
}