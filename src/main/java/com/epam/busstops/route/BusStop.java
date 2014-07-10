package com.epam.busstops.route;

import com.epam.busstops.transport.Bus;
import org.apache.log4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * Created by ivan on 7/10/14.
 */
public class BusStop {
    private Semaphore semaphore;
    private String name;
    private static Logger log = Logger.getLogger(BusStop.class);

    public BusStop(int maxBus, String name) {
        semaphore = new Semaphore(maxBus);
        this.name = name;
    }

    public void busRideIn(Bus bus)
            throws BrokenBarrierException, InterruptedException {
        semaphore.acquire();

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

        semaphore.release();
    }

}
