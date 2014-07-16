package com.epam.busstops.transport;

import com.epam.busstops.exceptions.BusRidingException;
import com.epam.busstops.route.BusStop;
import com.epam.busstops.route.Route;
import org.apache.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;

/**
 * Created by ivan on 7/11/14.
 */
public class Bus extends Thread {
    private static Logger log = Logger.getLogger(Bus.class);
    private Route route;
    private int busNumber;
    private int currPassengersCount;
    private int maxPassengersCount;
    private int length;

    public Bus(Route route,
               int number,
               int maxPassengersCount,
               int length) {
        this.route = route;
        this.busNumber = number;
        this.maxPassengersCount = maxPassengersCount;
        this.length = length;
    }


    /**
     * @return how many passengers out
     */
    public int letOutSomePassengers() {
        currPassengersCount -= Math.random() * currPassengersCount;
        return currPassengersCount;
    }

    /**
     * @param newPassengerCount
     * @return 0 if all passengers fit in, reminder otherwise
     */
    public int letInSomePassengers(int newPassengerCount) {
        if (newPassengerCount > maxPassengersCount - currPassengersCount) {
            int freeSpace = maxPassengersCount - currPassengersCount;
            currPassengersCount += freeSpace;
            return newPassengerCount - freeSpace;
        } else {
            currPassengersCount += newPassengerCount;
            return 0;
        }
    }

    public int getNumber() {
        return busNumber;
    }

    public int getPassengersCount() {
        return currPassengersCount;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void run() {
        for (BusStop stop : route.getStops()) {
            try {
                stop.busRideIn(this);
            } catch (BusRidingException e) {
                log.error(e);
            }
            log.debug("bus number " + busNumber + " finished");
        }
    }
}
