package org.example.labbased.threads;
import org.example.labbased.core.AbstractTicketHandler;
import org.example.labbased.core.TicketPool;
import org.example.labbased.logging.Logger;

public abstract class Vendor extends AbstractTicketHandler implements Runnable {
    protected final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        super(ticketPool);
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < getActualTicketReleaseRate(); i++) {
            int ticketNum = i+1;
            String ticket = "Ticket-" + System.nanoTime();
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

    protected abstract int getActualTicketReleaseRate();
}