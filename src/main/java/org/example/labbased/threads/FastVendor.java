package org.example.labbased.threads;

import org.example.labbased.core.TicketPool;

public class FastVendor extends Vendor{

    public FastVendor(TicketPool ticketPool, int ticketReleaseRate) {
        super(ticketPool,ticketReleaseRate);
    }

    @Override
    protected int getActualTicketReleaseRate() {
        return ticketReleaseRate*2;
    }
}
