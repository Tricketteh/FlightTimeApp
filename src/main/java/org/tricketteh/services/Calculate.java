package org.tricketteh.services;

import org.tricketteh.insoirs.Ticket;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class Calculate {
    /*
        FLight time calculation
     */
    public static long getTimeOfFlight(Ticket ticket) {

        long time;
        time = ticket.getArrivalDateTime().toEpochSecond(ZoneOffset.of(ticket.getDestination().getTimezone())) -
                ticket.getDepartureDateTime().toEpochSecond(ZoneOffset.of(ticket.getOrigin().getTimezone()));
        if (time < 0)
            throw new RuntimeException("Flight time can't be negative");
        return time;
    }


    /*
        Average flight time calculation
     */
    public static long averageTimeOfFlight(List<Ticket> tickets) {
        long average = 0;
        for (Ticket ticket : tickets) {
            average += getTimeOfFlight(ticket) / tickets.size();
        }
        return average;
    }


    /*
        Flight Time Percentile Calculation
     */
    public static long percentileTimeOfFlight(List<Ticket> tickets, double k) {
        List<Long> times = new ArrayList<>();
        for (Ticket ticket : tickets) {
            times.add(getTimeOfFlight(ticket));
        }
        times.sort(Long::compareTo);
        int countTimes = 0;
        for (long tm : times) {
            double percentile = 1.0 * (++countTimes) / times.size();
            if (k <= percentile) {
                return tm;
            }
        }
        return 0;
    }


}
