package com.pedrovalencia.foursquare_poi.beans;

/**
 * Created by valenciap
 * on 01/02/2016.
 * Bean with the information about the  Point of Interest that will be sent
 * to the UI
 */
public class POIBean {

    private String name;
    private String address;
    private String postalCode;
    private double lat;
    private double lng;


    public POIBean(String name, String address, String postalCode, double lat, double lng) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
