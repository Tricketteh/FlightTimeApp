package org.tricketteh;

import org.tricketteh.insoirs.Ticket;
import org.tricketteh.services.Calculate;
import org.tricketteh.services.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightTimeApplication {

    public static void main(String[] args) {

        // json parser
        List<Ticket> tickets = new ArrayList<>(
                TicketService.getTicketsJson("tickets.json"));

        // Average flight time calculation
        long averageTimeFlight = Calculate.averageTimeOfFlight(tickets);
        System.out.println("Average flight time: " + averageTimeFlight / 60 / 60 + " hours " + (averageTimeFlight / 60) % 60 + " mins.");

        // Percentile calculation
        long percentile = Calculate.percentileTimeOfFlight(tickets, 0.90);
        System.out.println( "90th percentile of flight time between Vladivostok and Tel Aviv = "
                + percentile / 60 / 60 + " hours " + (percentile / 60) % 60 + " mins.");
    }

}
