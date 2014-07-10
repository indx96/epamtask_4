package com.epam.busstops.route;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ivan on 7/11/14.
 */
public class Route {
    private ArrayList<BusStop> stops;

    private Route(RouteBuilder builder) {
        stops = builder;
    }

    public Iterable<BusStop> getStops() {
        return Collections.unmodifiableList(stops);
    }

    public static class RouteBuilder extends ArrayList<BusStop> {
        public Route build() {
            return new Route(this);
        }
    }
}
