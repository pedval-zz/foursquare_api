package com.pedrovalencia.foursquare_poi.exceptions;

/**
 * Created by valenciap
 * on 01/02/2016.
 */
public class POIServiceException extends RuntimeException {

    public POIServiceException(String message) {
        super(message);
    }

    public POIServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
