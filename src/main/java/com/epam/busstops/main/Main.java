package com.epam.busstops.main;

import com.epam.busstops.route.BusStop;
import com.epam.busstops.route.Route;
import com.epam.busstops.transport.Bus;
import org.apache.log4j.PropertyConfigurator;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by ivan on 7/11/14.
 */
public class Main {

    static {
        PropertyConfigurator.configure(ClassLoader
                .getSystemClassLoader().getResourceAsStream("log4j.properties"));
    }

    public static void main(String[] args) {
        BusStop[] busStops = new BusStop[6];
        busStops[0] = new BusStop(3, "kync");
        busStops[1] = new BusStop(5, "oceanos");
        busStops[2] = new BusStop(1, "drake");
        busStops[3] = new BusStop(4, "inokentyevka");
        busStops[4] = new BusStop(2, "dazdrepermavka");
        busStops[5] = new BusStop(7, "ibragimouka");

        Route.RouteBuilder routeBuilder = new Route.RouteBuilder();
        Collections.addAll(routeBuilder, busStops);


        Route absolutleyNewRoute = routeBuilder.build();
        LinkedList<Bus> buses = new LinkedList<>();
        for (int number = 0; number < 7; number++) {
            buses.add(new Bus(absolutleyNewRoute, number, 50));
        }
        buses.forEach((bus) -> bus.start());
    }
}
