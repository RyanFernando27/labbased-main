package org.example.labbased;
import org.example.labbased.config.Configuration;
import org.example.labbased.core.PriorityRetrieval;
import org.example.labbased.core.TicketPool;
import org.example.labbased.logging.Logger;
import org.example.labbased.threads.Customer;
import org.example.labbased.threads.FastVendor;
import org.example.labbased.threads.SlowVendor;
import org.example.labbased.threads.Vendor;
import org.example.labbased.ui.CommandLineInterface;
import org.example.labbased.ui.JavaFXInterface;

public class Main {
    public static void main(String[] args) {
        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool();
        Thread fastVendor = new Thread(new FastVendor(ticketPool, config.getTicketReleaseRate()));
        Thread slowVendor = new Thread(new SlowVendor(ticketPool, config.getTicketReleaseRate()));
        Thread customer = new Thread(new Customer(ticketPool, new PriorityRetrieval()));

//        seperate Statistics thread
        Thread statistics = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ticketPool.ticketStatistics();
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        statistics.start();
        fastVendor.start();
        slowVendor.start();
        customer.start();

        try {
            fastVendor.join();
            customer.join();
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }
        System.out.println(ticketPool.getTickets());
        Logger.log("System terminated.");
    }
}