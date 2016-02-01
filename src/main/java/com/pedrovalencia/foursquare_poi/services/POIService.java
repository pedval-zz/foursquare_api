package com.pedrovalencia.foursquare_poi.services;

import com.pedrovalencia.foursquare_poi.beans.POIBean;

import java.util.List;

/**
 * Created by valenciap
 * on 01/02/2016.
 */
public interface POIService {

    public List<POIBean> getPOIs(String place);
}
