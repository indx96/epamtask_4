package com.epam.busstops.route;

import com.epam.busstops.exceptions.BusRidingException;
import com.epam.busstops.transport.Bus;
import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Created by ivan on 7/10/14.
 */
public class BusStop {
    private Semaphore semaphore;
    private String name;
    private static Logger log = Logger.getLogger(BusStop.class);
    public final int MAX_BUS_COUNT;

    public BusStop(int maxBus, String name) {
        MAX_BUS_COUNT = maxBus;
        semaphore = new Semaphore(MAX_BUS_COUNT, true);
        this.name = name;
    }

    public void busRideIn(Bus bus) throws BusRidingException {
        try {
            semaphore.acquire(bus.getLength());

            log.debug("bus with number [" + bus.getNumber() + "] arrived to " + name +
                    ". Passengers in: " + bus.getPassengersCount() + ".");

            Thread.sleep((long) (Math.random() * 100));
            log.debug("bus number [" + bus.getNumber() + "] released "
                    + bus.letOutSomePassengers() + " passengers.");

            int passengerToLetIn = (int) (Math.random() * 30);
            log.debug("letting in " + passengerToLetIn + " passengers. " + "Bus number [" + bus.getNumber() + "].");
            Thread.sleep((long) (Math.random() * 100));

            int couldntGetIn = bus.letInSomePassengers(passengerToLetIn);
            log.debug(couldntGetIn + " passengers couldn't get in bus number [" + bus.getNumber() + "].");

        } catch (InterruptedException e) {
            throw new BusRidingException(e);
        } finally {
            semaphore.release(bus.getLength());
        }
    }

}
