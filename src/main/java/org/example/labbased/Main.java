package org.example.labbased;
import org.example.labbased.config.Configuration;
import org.example.labbased.core.PriorityRetrieval;
import org.example.labbased.core.TicketPool;
import org.example.labbased.logging.Logger;
import org.example.labbased.threads.Customer;
import org.example.labbased.threads.Vendor;
import org.example.labbased.ui.CommandLineInterface;
import org.example.labbased.ui.JavaFXInterface;

public class Main {
    public static void main(String[] args) {
        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool();
        Thread vendor = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
        Thread customer = new Thread(new Customer(ticketPool, new PriorityRetrieval()));

        vendor.start();
        customer.start();

        try {
            vendor.join();
            customer.join();
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }
        System.out.println(ticketPool.getTickets());
        Logger.log("System terminated.");
    }
}