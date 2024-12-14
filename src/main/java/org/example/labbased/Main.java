package org.example.labbased;
import org.example.labbased.config.Configuration;
import org.example.labbased.core.TicketPool;
import org.example.labbased.logging.Logger;
import org.example.labbased.threads.Customer;
import org.example.labbased.threads.Vendor;
import org.example.labbased.ui.CommandLineInterface;
public class Main {
    public static void main(String[] args) {
        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool();
        Thread vendor = new Thread(new Vendor(ticketPool,
                config.getTicketReleaseRate()));
        Thread customer = new Thread(new Customer(ticketPool));
        vendor.start();
        customer.start();
        try {
            vendor.join();
            customer.join();
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }
        Logger.log("System terminated.");
    }
}