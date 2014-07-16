package com.epam.busstops.exceptions;

/**
 * Created by ivan on 7/11/14.
 */
public class BusRidingException extends Exception {

    public BusRidingException() {
        super();
    }

    public BusRidingException(String message) {
        super(message);
    }

    public BusRidingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusRidingException(Throwable cause) {
        super(cause);
    }
}
